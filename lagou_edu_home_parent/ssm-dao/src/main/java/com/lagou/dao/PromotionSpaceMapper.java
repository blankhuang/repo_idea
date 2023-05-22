package com.lagou.dao;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {

    public List<PromotionSpace> findAllPromotionSpace();

    public void savePromotionSpace(PromotionSpace promotionSpace);

    void updatePromotionSpace(PromotionSpace promotionSpace);

    public PromotionSpace findPromotionSpaceById(Integer id);


}
