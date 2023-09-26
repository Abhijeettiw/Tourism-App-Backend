package com.tourism.tourism.Services.Impl;

import com.tourism.tourism.Entities.TouristPlaces;
import com.tourism.tourism.Exception.ResourceNotFoundException;
import com.tourism.tourism.Payloads.TouristDto;
import com.tourism.tourism.Repositories.TouristRepo;
import com.tourism.tourism.Services.TouristPlaceServices;
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
public class TouristPlaceServiceImpl implements TouristPlaceServices {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TouristRepo touristRepo;
    @Override
    public TouristDto addPlace(TouristDto touristDto) {
        TouristPlaces touristPlaces=this.modelMapper.map(touristDto,TouristPlaces.class);
        this.touristRepo.save(touristPlaces);
        return this.modelMapper.map(touristPlaces,TouristDto.class);
    }

    @Override
    public TouristDto updatePlace(TouristDto touristDto, Long touristId) {
        TouristPlaces touristPlaces=this.touristRepo.findById(touristId).orElseThrow(()-> new ResourceNotFoundException("Tourist Place",touristId));
        touristPlaces.setName(touristDto.getName());
        touristPlaces.setImageName(touristDto.getImageName());
        this.touristRepo.save(touristPlaces);
        return this.modelMapper.map(touristPlaces,TouristDto.class);
    }

    @Override
    public Void deletePlace(Long touristId) {
        TouristPlaces touristPlaces=this.touristRepo.findById(touristId).orElseThrow(()-> new ResourceNotFoundException("Tourist Place",touristId));
        this.touristRepo.delete(touristPlaces);
        return null;
    }

    @Override
    public TouristDto placeById(Long touristId) {
        TouristPlaces touristPlaces=this.touristRepo.findById(touristId).orElseThrow(()-> new ResourceNotFoundException("Tourist Place",touristId));
        return this.modelMapper.map(touristPlaces,TouristDto.class);
    }

    @Override
    public List<TouristDto> allPlace(Integer pageNo,Integer pageSize) {
        Pageable p= PageRequest.of(pageNo,pageSize);
        Page<TouristPlaces> userPage=this.touristRepo.findAll(p);
        List<TouristPlaces> all=userPage.getContent();
        List<TouristDto> allPlaces=all.stream().map((ap)->this.modelMapper.map(ap,TouristDto.class)).collect(Collectors.toList());
        return allPlaces;
    }

    @Override
    public List<TouristDto> findByName(String name) {
        List<TouristPlaces> touristPlaces=this.touristRepo.siteByName("%"+name+"%");
        List<TouristDto> siteByName=touristPlaces.stream().map((tr)->this.modelMapper.map(tr,TouristDto.class)).collect(Collectors.toList());
        return siteByName;
    }
}
