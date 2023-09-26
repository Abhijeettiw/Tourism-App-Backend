package com.tourism.tourism.Controller;

import com.tourism.tourism.Payloads.ResortDto;
import com.tourism.tourism.Services.FileService;
import com.tourism.tourism.Services.ResortServices;
import com.tourism.tourism.Utilities.ApiResponse;
import com.tourism.tourism.Utilities.AppConstants;
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
@RequestMapping("/resort")
public class ResortController {
    @Autowired
    private FileService fileService;
    @Value("${project.images}")
    private String path;
    @Autowired
    private ResortServices resortServices;
    @PostMapping("/site/touId/{touristId}/newResort")
    public ResponseEntity<ResortDto> addResorts(@RequestBody ResortDto resortDto, @PathVariable("touristId") Long touristId)
    {
        ResortDto resortDto1=this.resortServices.addResort(resortDto,touristId);
        return new ResponseEntity<>(resortDto1, HttpStatus.ACCEPTED);
    }
    @PutMapping("/site/touId/{touristId}/resId/{resortId}/update")
    public ResponseEntity<ResortDto> updateResort(@RequestBody ResortDto resortDto,@PathVariable("resortId") Long resortId)
    {
        ResortDto resortDto1=this.resortServices.updateResort(resortDto,resortId);
        return new ResponseEntity<>(resortDto1,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/site/touId/{touristId}/resId/{resortId}/del")
    public ResponseEntity<ApiResponse> deleteResort(@PathVariable("touristId") Long touristId,@PathVariable("resortId") Long resortId)
    {
        this.resortServices.deleteResort(touristId,resortId);
        return new ResponseEntity<>(new ApiResponse("Resort Deleted Successfully",true,HttpStatus.FOUND),HttpStatus.OK);
    }
    @GetMapping("/site/touId/{touristId}/resId/{resortId}/del")
    public ResponseEntity<ResortDto> resortById(@PathVariable("resortId") Long resortId)
    {
        ResortDto resortDto=this.resortServices.resortById(resortId);
        return new ResponseEntity<>(resortDto,HttpStatus.FOUND);
    }
    @GetMapping("/allResorts")
    public ResponseEntity<List<ResortDto>> allResorts(@RequestParam(value = "pageNo",defaultValue = AppConstants.PAGE_NO,required = false) Integer pageNo,
                                                      @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize)
    {
        List<ResortDto> resortDtoList=this.resortServices.allResort(pageNo,pageSize);
        return new ResponseEntity<>(resortDtoList,HttpStatus.FOUND);
    }
    @GetMapping("/site/touId/{touristId}")
    public ResponseEntity<List<ResortDto>> resortBySite(@PathVariable("touristId") Long touristId)
    {
        List<ResortDto> siteResorts=this.resortServices.resortByPlace(touristId);
        return new ResponseEntity<>(siteResorts,HttpStatus.FOUND);
    }
    @GetMapping("/touId/{touristId}/featured")
    public ResponseEntity<List<ResortDto>> featured(@PathVariable("touristId") Long touristId)
    {
        List<ResortDto> featuredResorts=this.resortServices.featuredResorts(touristId);
        return new ResponseEntity<>(featuredResorts,HttpStatus.FOUND);
    }
    @GetMapping("/name")
    public ResponseEntity<List<ResortDto>> resByName(@RequestParam String name)
    {
        List<ResortDto> resName=this.resortServices.resortByName(name);
        return new ResponseEntity<>(resName,HttpStatus.FOUND);
    }
    @GetMapping("/touId/{touristId}/resId/{resortId}/feature")
    public ResponseEntity<ResortDto> featuring(@RequestBody ResortDto resortDto,@PathVariable("touristId") Long touristId, @PathVariable("resortId") Long resortId)
    {
        ResortDto resortDto1=this.resortServices.feature(resortDto,touristId,resortId);
        return new ResponseEntity<>(resortDto1,HttpStatus.ACCEPTED);
    }
    @PostMapping("/site/resId/{resortId}/image/upload")
    public ResponseEntity<ResortDto> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable("resortId") Long resortId)throws IOException
    {
        String fileName=this.fileService.uploadImageSite(path,file);
        ResortDto resortDto= this.resortServices.resortById(resortId);
        resortDto.setImageName(fileName);
        ResortDto resortDto1=this.resortServices.updateResort(resortDto,resortId);
        return new ResponseEntity<>(resortDto1,HttpStatus.ACCEPTED);
    }
    @SneakyThrows
    @GetMapping(value = "/resort/image/{imgName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public Void downloadImage(@PathVariable("imgName") String imgName, HttpServletResponse response)
    {
        InputStream resource=this.fileService.getImageSite(path,imgName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
        return null;
    }
}
