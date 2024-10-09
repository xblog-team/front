package com.xblog.front.category.adaptor.impl;

import com.xblog.front.category.adaptor.CategoryAdaptor;
import com.xblog.front.category.dto.GetCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * category 관련 adaptor
 * @author jihyeon
 */
@Component
@RequiredArgsConstructor
public class CategoryAdaptorImpl implements CategoryAdaptor {
    private final RestTemplate restTemplate;

    @Value("${gateway.api.url}")
    String gatewayDomain;

    /**
     * APPLICATION_JSON으로 contentType과 accept 설정한 HttpHeaders를 만드는 method
     * @return HttpHeaders
     */
    public HttpHeaders makeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    /**
     * 특정 그룹에 속한 모든 카테고리들을 조회하는 요청을 gateway에 전달
     * @param partyId 그룹 아이디
     * @return categoryId, categoryName, partyId로 구성된 dto 리스트
     */
    @Override
    public List<GetCategoryDto> getCategories(Long partyId) {
        HttpHeaders headers = makeHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<GetCategoryDto>> exchange = restTemplate.exchange(
                gatewayDomain + "/api/categories/category-parties/" + partyId,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    /**
     * 특정 카테고리를 조회하는 요청을 gateway에 전달
     * @param categoryId 카테고리 아이디
     * @return categoryId, categoryName, partyId로 구성된 dto 리스트
     */
    @Override
    public GetCategoryDto getCategory(Long categoryId) {
        HttpHeaders headers = makeHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<GetCategoryDto> exchange = restTemplate.exchange(
                gatewayDomain + "/api/categories/" + categoryId,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }
}
