package com.xblog.front.category.service.impl;

import com.xblog.front.category.adaptor.CategoryAdaptor;
import com.xblog.front.category.dto.GetCategoryDto;
import com.xblog.front.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * category 관련 service
 * @author jihyeon
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryAdaptor categoryAdaptor;

    /**
     * 그룹에 속한 카테고리들을 조회하는 method
     * @param partyId 그룹 아이디
     * @return categoryId, categoryName, partyId로 구성된 dto 리스트
     */
    @Override
    public List<GetCategoryDto> getCategoryList(Long partyId) {
        return categoryAdaptor.getCategories(partyId);
    }

    /**
     * 특정 카테고리를 조회하는 method
     * @param categoryId 카테고리 아이디
     * @return categoryId, categoryName, partyId로 구성된 dto
     */
    @Override
    public GetCategoryDto getCategory(Long categoryId) {
        return categoryAdaptor.getCategory(categoryId);
    }
}
