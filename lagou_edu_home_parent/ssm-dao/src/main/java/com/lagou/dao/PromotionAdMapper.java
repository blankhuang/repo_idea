package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    public List<PromotionAd> findAllPromotionAdByPage();

    public void savePromotionAd(PromotionAd promotionAd);

    public void updatePromotionAd(PromotionAd promotionAd);

    public PromotionAd findPromotionAdById(int id);

    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
