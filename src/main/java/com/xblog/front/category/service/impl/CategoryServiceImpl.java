package com.xblog.front.category.service.impl;

import com.xblog.front.category.adaptor.CategoryAdaptor;
import com.xblog.front.category.dto.GetCategoryDto;
import com.xblog.front.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryAdaptor categoryAdaptor;

    @Override
    public List<GetCategoryDto> getCategoryList(Long partyId) {
        return categoryAdaptor.getCategories(partyId);
    }
}
