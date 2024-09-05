package com.xblog.front.category.service;

import com.xblog.front.category.dto.GetCategoryDto;

import java.util.List;

public interface CategoryService {
    List<GetCategoryDto> getCategoryList(Long partyId);
}
