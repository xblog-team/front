package com.xblog.front.category.adaptor;

import com.xblog.front.category.dto.GetCategoryDto;

import java.util.List;

public interface CategoryAdaptor {
    List<GetCategoryDto> getCategories(Long partyId);

    GetCategoryDto getCategory(Long categoryId);
}
