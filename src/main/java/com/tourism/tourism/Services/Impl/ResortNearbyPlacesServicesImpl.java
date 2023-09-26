package com.tourism.tourism.Services.Impl;

import com.tourism.tourism.Entities.ResortNearbyPlaces;
import com.tourism.tourism.Entities.Resorts;
import com.tourism.tourism.Exception.ResourceNotFoundException;
import com.tourism.tourism.Payloads.ResortNearbyPlacesDto;
import com.tourism.tourism.Repositories.ResortNearbyPlacesRepo;
import com.tourism.tourism.Repositories.ResortRepo;
import com.tourism.tourism.Repositories.TouristRepo;
import com.tourism.tourism.Services.ResortNearbyPlacesServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class ResortNearbyPlacesServicesImpl implements ResortNearbyPlacesServices {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TouristRepo touristRepo;
    @Autowired
    private ResortRepo resortRepo;
    @Autowired
    private ResortNearbyPlacesRepo resortNearbyPlacesRepo;
    @Override
    public ResortNearbyPlacesDto addPlace(ResortNearbyPlacesDto resortNearbyPlacesDto, Long touristId, Long resortId) {
        this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        Resorts resorts=this.resortRepo.findById(resortId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        ResortNearbyPlaces resortNearbyPlaces=this.modelMapper.map(resortNearbyPlacesDto,ResortNearbyPlaces.class);
        resortNearbyPlaces.setResorts(resorts);
        resortNearbyPlaces.setName(resortNearbyPlacesDto.getName());
        resortNearbyPlaces.setImageName("Default.png");
        resortNearbyPlaces.setDescription(resortNearbyPlacesDto.getDescription());
        this.resortNearbyPlacesRepo.save(resortNearbyPlaces);
        return this.modelMapper.map(resortNearbyPlaces,ResortNearbyPlacesDto.class);
    }

    @Override
    public ResortNearbyPlacesDto updatePlace(ResortNearbyPlacesDto resortNearbyPlacesDto, Long touristId, Long resortId, Long nbPlaceId) {
        this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        this.resortRepo.findById(resortId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        ResortNearbyPlaces resortNearbyPlaces=this.resortNearbyPlacesRepo.findById(nbPlaceId).orElseThrow(() -> new ResourceNotFoundException("name", nbPlaceId));
        resortNearbyPlaces.setName(resortNearbyPlacesDto.getName());
        this.resortNearbyPlacesRepo.save(resortNearbyPlaces);
        return this.modelMapper.map(resortNearbyPlaces,ResortNearbyPlacesDto.class);
    }

    @Override
    public Void deletePlace(Long touristId, Long resortId, Long nbPlaceId) {
        this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        this.resortRepo.findById(resortId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        ResortNearbyPlaces resortNearbyPlaces=this.resortNearbyPlacesRepo.findById(nbPlaceId).orElseThrow(() -> new ResourceNotFoundException("name", nbPlaceId));
        this.resortNearbyPlacesRepo.delete(resortNearbyPlaces);
        return null;
    }

    @Override
    public List<ResortNearbyPlacesDto> nbPlaceByResort(Long touristId, Long resortId) {
        this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        Resorts resorts=this.resortRepo.findById(resortId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        List<ResortNearbyPlaces> resortNearbyPlaces=this.resortNearbyPlacesRepo.findByResorts(resorts);
        List<ResortNearbyPlacesDto> resortNearbyPlacesDto=resortNearbyPlaces.stream().map((nbp)->this.modelMapper.map(nbp, ResortNearbyPlacesDto.class)).collect(Collectors.toList());
        return resortNearbyPlacesDto;
    }

    @Override
    public List<ResortNearbyPlacesDto> nbPlacesByName(String name) {
        List<ResortNearbyPlaces> resortNearbyPlaces=this.resortNearbyPlacesRepo.nbpByName("%"+name+"%");
        List<ResortNearbyPlacesDto> resortNearbyPlacesDto=resortNearbyPlaces.stream().map((reNbPl)->this.modelMapper.map(reNbPl, ResortNearbyPlacesDto.class)).collect(Collectors.toList());
        return resortNearbyPlacesDto;
    }

    @Override
    public ResortNearbyPlacesDto nbpById(Long resortId, Long nbpId) {
        Resorts resorts=this.resortRepo.findById(resortId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        ResortNearbyPlaces resortNearbyPlaces=this.resortNearbyPlacesRepo.findById(nbpId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        return this.modelMapper.map(resortNearbyPlaces,ResortNearbyPlacesDto.class);
    }
}
