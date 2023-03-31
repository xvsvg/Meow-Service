package org.meows.models.page;

import lombok.Data;
import org.meows.entities.CatEntity;
import org.meows.models.CatResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public @Data class CatPageResponse {

    private Integer currentPage;
    private Integer totalPages;
    private Long totalElements;

    private List<CatResponse> data;

    protected CatPageResponse() {}

    public static CatPageResponse toModel(Page<CatEntity> page){
        var response = new CatPageResponse();
        response.setCurrentPage(page.getNumber() + 1);
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        response.setData(page.getContent().stream().map(CatResponse::toModel).toList());

        return response;
    }
}
