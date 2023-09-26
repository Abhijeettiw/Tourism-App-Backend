package com.tourism.tourism.Controller;

import com.tourism.tourism.Payloads.TouristDto;
import com.tourism.tourism.Services.FileService;
import com.tourism.tourism.Services.TouristPlaceServices;
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
@RequestMapping("/home/admin/tourist-sites")
public class TouristPlaceController {
    @Autowired
    private FileService fileService;
    @Autowired
    private TouristPlaceServices touristPlaceServices;
    @Value("${project.images}")
    private String path;
    @PostMapping("/new-site")
    public ResponseEntity<TouristDto> addSite(@RequestBody TouristDto touristDto)
    {
        TouristDto newSite=this.touristPlaceServices.addPlace(touristDto);
        return new ResponseEntity<>(newSite, HttpStatus.ACCEPTED);
    }
    @PutMapping("/site/touId/{touristId}")
    public ResponseEntity<TouristDto> updateSite(@RequestBody TouristDto touristDto,@PathVariable("touristId") Long touristId)
    {
        TouristDto updateSite=this.touristPlaceServices.updatePlace(touristDto,touristId);
        return new ResponseEntity<>(updateSite,HttpStatus.FOUND);
    }
    @DeleteMapping("/site/touId/{touristId}/del")
    public ResponseEntity<ApiResponse> deleteSite(@PathVariable("touristId") Long touristId)
    {
        this.touristPlaceServices.deletePlace(touristId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Site Deleted Successfully",true,HttpStatus.OK),HttpStatus.OK);
    }
    @GetMapping("/site/all")
    public ResponseEntity<List<TouristDto>> allSite(@RequestParam(value = "pageNo",defaultValue = "1",required = false) Integer pageNo,
                                                    @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize)
    {
        List<TouristDto> all= this.touristPlaceServices.allPlace(pageNo,pageSize);
        return new ResponseEntity<>(all,HttpStatus.FOUND);
    }
    @GetMapping("/site/touId/{touristId}")
    public ResponseEntity<TouristDto> siteById(@PathVariable("touristId") Long touristId)
    {
        TouristDto touristDto=this.touristPlaceServices.placeById(touristId);
        return new ResponseEntity<>(touristDto,HttpStatus.FOUND);
    }
    @GetMapping("site/{name}")
    public ResponseEntity<List<TouristDto>> touristByName(@PathVariable("name") String name)
    {
        List<TouristDto> touristDto=this.touristPlaceServices.findByName(name);
        return new ResponseEntity<>(touristDto,HttpStatus.FOUND);

    }

    @PostMapping("/site/touId/{touristId}/image/upload")
    public ResponseEntity<TouristDto> uploadImage(@RequestParam("image") MultipartFile file,@PathVariable("touristId") Long touristId)throws IOException
    {
        String fileName=this.fileService.uploadImageSite(path,file);
        TouristDto touristDto=this.touristPlaceServices.placeById(touristId);
        touristDto.setImageName(fileName);
        TouristDto updatedPlace=this.touristPlaceServices.updatePlace(touristDto,touristId);
        return new ResponseEntity<>(updatedPlace,HttpStatus.ACCEPTED);
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
