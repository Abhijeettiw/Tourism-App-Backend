package com.tourism.tourism.Services.Impl;

import com.tourism.tourism.Entities.Resorts;
import com.tourism.tourism.Entities.TouristPlaces;
import com.tourism.tourism.Exception.ResourceNotFoundException;
import com.tourism.tourism.Payloads.ResortDto;
import com.tourism.tourism.Repositories.ResortRepo;
import com.tourism.tourism.Repositories.TouristRepo;
import com.tourism.tourism.Services.ResortServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service

public class ResortServicesImpl implements ResortServices {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ResortRepo resortRepo;
    @Autowired
    private TouristRepo touristRepo;
    @Override
    public ResortDto addResort(ResortDto resortDto,Long touristId) {
        TouristPlaces touristPlaces=this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        Resorts resorts=this.modelMapper.map(resortDto,Resorts.class);
        resorts.setTouristPlaces(touristPlaces);
        resorts.setName(resortDto.getName());
        resorts.setAddress(resortDto.getAddress());
        resorts.setOwner(resortDto.getOwner());
        resorts.setContact(resortDto.getContact());
        resorts.setImageName("Default.png");
        this.resortRepo.save(resorts);
        return this.modelMapper.map(resorts,ResortDto.class);
    }

    @Override
    public ResortDto updateResort(ResortDto resortDto, Long resortId) {
        Resorts resorts=this.resortRepo.findById(resortId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        resorts.setName(resortDto.getName());
        resorts.setAddress(resortDto.getAddress());
        resorts.setOwner(resortDto.getOwner());
        resorts.setContact(resortDto.getContact());
        this.resortRepo.save(resorts);
        return this.modelMapper.map(resorts,ResortDto.class);
    }

    @Override
    public Void deleteResort(Long touristId, Long resortId) {
        this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        Resorts resorts=this.resortRepo.findById(resortId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        this.resortRepo.delete(resorts);
        return null;
    }

    @Override
    public ResortDto resortById(Long resortId) {
        Resorts resorts=this.resortRepo.findById(resortId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        return this.modelMapper.map(resorts,ResortDto.class);
    }

    @Override
    public List<ResortDto> allResort(Integer pageNo,Integer pageSize) {
        Pageable p= PageRequest.of(pageNo,pageSize);
        Page<Resorts> all=this.resortRepo.findAll(p);
        List<Resorts> allResorts=all.getContent();
        List<ResortDto> allResorts1=allResorts.stream().map((aR)->this.modelMapper.map(aR,ResortDto.class)).collect(Collectors.toList());
        return allResorts1;
    }

    @Override
    public List<ResortDto> resortByPlace(Long touristId) {
        TouristPlaces touristPlaces=this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        List<Resorts> allResortBySite=this.resortRepo.findByTouristPlaces(touristPlaces);
        List<ResortDto> allResortBySite1=allResortBySite.stream().map((aR)->this.modelMapper.map(aR,ResortDto.class)).collect(Collectors.toList());
        return allResortBySite1;
    }

    @Override
    public List<ResortDto> featuredResorts(Long touristId) {
        TouristPlaces touristPlaces=this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        List<Resorts> featured=this.resortRepo.findByFeaturedTrue();
        List<ResortDto> featuredDto=featured.stream().map((fR)->this.modelMapper.map(fR,ResortDto.class)).collect(Collectors.toList());
        return featuredDto;
    }

    public List<ResortDto> resortByName(String name)
    {
        List<Resorts> resName=this.resortRepo.findByNameIs("%"+name+"%");
        List<ResortDto> resNameDto=resName.stream().map((rN)->this.modelMapper.map(rN,ResortDto.class)).collect(Collectors.toList());
        return resNameDto;
    }

    @Override
    public ResortDto feature(ResortDto resortDto,Long touristId, Long resortId) {
        this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        Resorts resorts=this.resortRepo.findById(resortId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        resorts.setFeatured(resortDto.getFeatured());
        return this.modelMapper.map(resorts,ResortDto.class);
    }
}
