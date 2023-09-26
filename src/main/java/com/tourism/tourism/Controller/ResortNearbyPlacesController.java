package com.tourism.tourism.Controller;

import com.tourism.tourism.Payloads.ResortNearbyPlacesDto;
import com.tourism.tourism.Payloads.TouristDto;
import com.tourism.tourism.Services.FileService;
import com.tourism.tourism.Services.ResortNearbyPlacesServices;
import com.tourism.tourism.Utilities.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/resNbPlaces")
public class ResortNearbyPlacesController {
    @Autowired
    private FileService fileService;
    @Value("${project.images}")
    private String path;
    @Autowired
    private ResortNearbyPlacesServices resortNearbyPlacesServices;
    @PostMapping("/touId/{touristId}/resId/{resortId}/add")
    public ResponseEntity<ResortNearbyPlacesDto> addPlaceToResort(@RequestBody ResortNearbyPlacesDto resortNearbyPlacesDto, @PathVariable("touristId") Long touristId, @PathVariable("resortId") Long resortId)
    {
        ResortNearbyPlacesDto nearbyPlacesDto=this.resortNearbyPlacesServices.addPlace(resortNearbyPlacesDto,touristId,resortId);
        return new ResponseEntity<>(resortNearbyPlacesDto, HttpStatus.ACCEPTED);
    }
    @PutMapping("/touId/{touristId}/resId/{resortId}/nbpId/{nbPlaceId}/update")
    public ResponseEntity<ResortNearbyPlacesDto> updateResPlaces(@RequestBody ResortNearbyPlacesDto resortNearbyPlacesDto, @PathVariable("touristId") Long touristId, @PathVariable("resortId") Long resortId,@PathVariable("nbpId") Long nbPlaceId)
    {
        ResortNearbyPlacesDto resortNearbyPlacesDto1=this.resortNearbyPlacesServices.updatePlace(resortNearbyPlacesDto,touristId,resortId,nbPlaceId);
        return new ResponseEntity<>(resortNearbyPlacesDto1,HttpStatus.ACCEPTED);
    }
    @GetMapping("/touId/{touristId}/resId/{resortId}/allNbpRes")
    public ResponseEntity<List<ResortNearbyPlacesDto>> getAll( @PathVariable("touristId") Long touristId, @PathVariable("resortId") Long resortId)
    {
        List<ResortNearbyPlacesDto> resortNearbyPlacesDto=this.resortNearbyPlacesServices.nbPlaceByResort(touristId,resortId);
        return new ResponseEntity<>(resortNearbyPlacesDto,HttpStatus.FOUND);
    }
    @DeleteMapping("/touId/{touristId}/resId/{resortId}/nbpId/{nbpId}")
    public ResponseEntity<ApiResponse> deleteNbpRes(@PathVariable("touristId") Long touristId, @PathVariable("resortId") Long resortId,@PathVariable("nbpId") Long nbPlaceId)
    {
        this.resortNearbyPlacesServices.deletePlace(touristId,resortId,nbPlaceId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Place Deleted Successfully",true,HttpStatus.OK),HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<List<ResortNearbyPlacesDto>> nbpByName(@PathVariable("name") String name)
    {
        List<ResortNearbyPlacesDto> resortNearbyPlacesDto=this.resortNearbyPlacesServices.nbPlacesByName(name);
        return new ResponseEntity<>(resortNearbyPlacesDto,HttpStatus.FOUND);
    }
    @PostMapping("/site/touId/{touristId}/resId/{resortId}/nbpId/{nbpId}/image/upload")
    public ResponseEntity<ResortNearbyPlacesDto> uploadImage(@RequestParam("image") MultipartFile file,@PathVariable("touristId") Long touristId,@PathVariable("resortId") Long resortId ,@PathVariable("nbpId") Long nbpId)throws IOException
    {
        String fileName=this.fileService.uploadImageSite(path,file);
        ResortNearbyPlacesDto resortNearbyPlacesDto=this.resortNearbyPlacesServices.nbpById(resortId,nbpId);
        resortNearbyPlacesDto.setImageName(fileName);
        ResortNearbyPlacesDto resortNearbyPlacesDto1=this.resortNearbyPlacesServices.updatePlace(resortNearbyPlacesDto,touristId,resortId,nbpId);
        return new ResponseEntity<>(resortNearbyPlacesDto,HttpStatus.ACCEPTED);
    }
    @SneakyThrows
    @GetMapping(value = "/site/image/{imgName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public Void downloadImage(@PathVariable("imgName") String imgName, HttpServletResponse response)
    {
        InputStream resource=this.fileService.getImageSite(path,imgName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
        return null;
    }
}

