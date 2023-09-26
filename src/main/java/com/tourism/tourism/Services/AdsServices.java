package com.tourism.tourism.Services;

import com.tourism.tourism.Entities.Ads;
import com.tourism.tourism.Payloads.AdsDto;

import java.util.List;

public interface AdsServices {
    AdsDto addAds(AdsDto adsDto);
    AdsDto updateImage(AdsDto adsDto,Long adId);
    AdsDto adById(Long adId);
    Void deleteAds(Long adId);
    List<AdsDto> allAds();
    List<AdsDto> adsByName(String name);
}
