package com.tourism.tourism.Services.Impl;

import com.tourism.tourism.Entities.ResortFeatures;
import com.tourism.tourism.Entities.Resorts;
import com.tourism.tourism.Exception.ResourceNotFoundException;
import com.tourism.tourism.Payloads.ResortDto;
import com.tourism.tourism.Payloads.ResortFeaturesDto;
import com.tourism.tourism.Repositories.ResortFeaturesRepo;
import com.tourism.tourism.Repositories.ResortRepo;
import com.tourism.tourism.Repositories.TouristRepo;
import com.tourism.tourism.Services.ResortFeatureServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResortFeatureServicesImpl implements ResortFeatureServices {
    @Autowired
    private TouristRepo touristRepo;
    @Autowired
    private ResortRepo resortRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ResortFeaturesRepo resortFeaturesRepo;
    @Override
    public ResortFeaturesDto addFeature(ResortFeaturesDto resortFeaturesDto, Long touristId, Long resortId) {
        this.touristRepo.findById(touristId).orElseThrow(()->new ResourceNotFoundException("name",touristId));
        Resorts resorts=this.resortRepo.findById(resortId).orElseThrow(()->new ResourceNotFoundException("name",resortId));
        ResortFeatures resortFeatures=this.modelMapper.map(resortFeaturesDto,ResortFeatures.class);
        resortFeatures.setResorts(resorts);
        resortFeatures.setFeatures(resortFeaturesDto.getFeatures());
        this.resortFeaturesRepo.save(resortFeatures);
        return this.modelMapper.map(resortFeatures,ResortFeaturesDto.class);
    }

    @Override
    public ResortFeaturesDto updateFeature(ResortFeaturesDto resortFeaturesDto, Long touristId, Long resortId, Long nbPlaceId) {
        this.touristRepo.findById(touristId).orElseThrow(()->new ResourceNotFoundException("name",touristId));
        this.resortRepo.findById(resortId).orElseThrow(()->new ResourceNotFoundException("name",resortId));
        ResortFeatures resortFeatures=this.resortFeaturesRepo.findById(nbPlaceId).orElseThrow(()->new ResourceNotFoundException("name",nbPlaceId));
        resortFeatures.setFeatures(resortFeaturesDto.getFeatures());
        this.resortFeaturesRepo.save(resortFeatures);
        return this.modelMapper.map(resortFeatures,ResortFeaturesDto.class);
    }

    @Override
    public Void deleteFeature(Long touristId, Long resortId, Long nbPlaceId) {
        this.touristRepo.findById(touristId).orElseThrow(()->new ResourceNotFoundException("name",touristId));
        this.resortRepo.findById(resortId).orElseThrow(()->new ResourceNotFoundException("name",resortId));
        ResortFeatures resortFeatures=this.resortFeaturesRepo.findById(nbPlaceId).orElseThrow(()->new ResourceNotFoundException("name",nbPlaceId));
        this.resortFeaturesRepo.delete(resortFeatures);
        return null;
    }

    @Override
    public List<ResortFeaturesDto> allResortFeatures(Long touristId, Long resortId) {
        this.touristRepo.findById(touristId).orElseThrow(()->new ResourceNotFoundException("name",touristId));
        this.resortRepo.findById(resortId).orElseThrow(()->new ResourceNotFoundException("name",resortId));
        List<ResortFeatures> features=this.resortFeaturesRepo.findAll();
        List<ResortFeaturesDto> featuresDto=features.stream().map((f)->this.modelMapper.map(f,ResortFeaturesDto.class)).collect(Collectors.toList());
        return featuresDto;
    }
}
