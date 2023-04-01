package org.meows.models.page;

import lombok.Data;
import org.meows.entities.OwnerEntity;
import org.meows.models.OwnerResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public @Data class OwnerPageResponse {

    private Integer currentPage;
    private Integer totalPages;
    private Long totalElements;

    private List<OwnerResponse> data;

    protected OwnerPageResponse() {
    }

    public static OwnerPageResponse toModel(Page<OwnerEntity> page) {
        var response = new OwnerPageResponse();
        response.setCurrentPage(page.getNumber() + 1);
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        response.setData(page.getContent().stream().map(OwnerResponse::toModel).toList());

        return response;
    }
}
