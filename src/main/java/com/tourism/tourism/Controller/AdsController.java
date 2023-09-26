package com.tourism.tourism.Controller;

import com.tourism.tourism.Payloads.AdsDto;
import com.tourism.tourism.Payloads.TouristDto;
import com.tourism.tourism.Services.AdsServices;
import com.tourism.tourism.Services.FileService;
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
@RequestMapping("/home/admin/ads")
public class AdsController {
    @Autowired
    private AdsServices adsServices;
    @Autowired
    private FileService fileService;
    @Value("${project.images}")
    private String path;
    @PostMapping("/new")
    public ResponseEntity<AdsDto> newAds(@RequestBody AdsDto adsDto)
    {
        AdsDto adsDto1=this.adsServices.addAds(adsDto);
        return new ResponseEntity<>(adsDto1, HttpStatus.OK);
    }
    @DeleteMapping("/adId/{adId}/del")
    public ResponseEntity<ApiResponse> deleteAd(@PathVariable("adId") Long adId)
    {
        this.adsServices.deleteAds(adId);
        return new ResponseEntity<>(new ApiResponse("Ad Deleted SuccessFully",true,HttpStatus.OK),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<AdsDto>> all()
    {
        List<AdsDto> allAdd=this.adsServices.allAds();
        return new ResponseEntity<>(allAdd,HttpStatus.FOUND);
    }

    @GetMapping("/all/name/{name}")
    public ResponseEntity<List<AdsDto>> allByName(@PathVariable("name") String name) {
        List<AdsDto> allAdd=this.adsServices.allAds();
        return new ResponseEntity<>(allAdd,HttpStatus.FOUND);
    }
    @PostMapping("/adId/{adId}/image/upload")
    public ResponseEntity<AdsDto> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable("adId") Long adId)throws IOException
    {
        String fileName=this.fileService.uploadImageSite(path,file);
        AdsDto adsDto=this.adsServices.adById(adId);
        adsDto.setImageName(fileName);
        AdsDto update=this.adsServices.updateImage(adsDto,adId);
        return new ResponseEntity<>(update,HttpStatus.ACCEPTED);
    }
    @SneakyThrows
    @GetMapping(value = "/adId/{imgAds}",produces = MediaType.IMAGE_JPEG_VALUE)
    public Void downloadImage(@PathVariable("imgAds") String imgName, HttpServletResponse response)
    {
        InputStream resource=this.fileService.getImageSite(path,imgName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
        return null;
    }

}
