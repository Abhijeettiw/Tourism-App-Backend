package com.tourism.tourism.Services.Impl;

import com.tourism.tourism.Entities.Ads;
import com.tourism.tourism.Exception.ResourceNotFoundException;
import com.tourism.tourism.Payloads.AdsDto;
import com.tourism.tourism.Repositories.AdsRepo;
import com.tourism.tourism.Services.AdsServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
@AllArgsConstructor
@NoArgsConstructor

public class AdsServiceImpl implements AdsServices {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AdsRepo adsRepo;
    @Override
    public AdsDto addAds(AdsDto adsDto) {
        Ads ads=this.modelMapper.map(adsDto,Ads.class);
        this.adsRepo.save(ads);
        return this.modelMapper.map(ads,AdsDto.class);
    }
    @Override
    public AdsDto updateImage(AdsDto adsDto, Long adId) {
        Ads ads=this.adsRepo.findById(adId).orElseThrow(()->new ResourceNotFoundException("name",adId));
        ads.setImageName(adsDto.getImageName());
        return this.modelMapper.map(ads,AdsDto.class);
    }

    @Override
    public AdsDto adById(Long adId) {
        Ads ads=this.adsRepo.findById(adId).orElseThrow(()->new ResourceNotFoundException("name",adId));
        return this.modelMapper.map(ads,AdsDto.class);
    }

    @Override
    public Void deleteAds(Long adId) {
        Ads ads=this.adsRepo.findById(adId).orElseThrow(()->new ResourceNotFoundException("name",adId));
        this.adsRepo.delete(ads);
        return null;
    }

    @Override
    public List<AdsDto> allAds() {
        List<Ads> all=this.adsRepo.findAll();
        List<AdsDto> allDto=all.stream().map((ad)->this.modelMapper.map(ad,AdsDto.class)).collect(Collectors.toList());
        return allDto;
    }

    @Override
    public List<AdsDto> adsByName(String name) {
        List<Ads> all=this.adsRepo.findByNameIs(name);
        List<AdsDto> allDto=all.stream().map((ad)->this.modelMapper.map(ad,AdsDto.class)).collect(Collectors.toList());
        return allDto;
    }
}
