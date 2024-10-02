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

@Component
@RequiredArgsConstructor
public class CategoryAdaptorImpl implements CategoryAdaptor {
    private final RestTemplate restTemplate;

    @Value("${gateway.api.url}")
    String gatewayDomain;

    public HttpHeaders makeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

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
