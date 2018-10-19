package com.genesisfin.backend.web.viewmodel;

import org.springframework.data.domain.Page;

public class PagedResultHelper{
    public static <T> PagedResult<T> from(Page<T> source) {
        if (source == null) return null;

        PagedResult<T> result = new PagedResult<>();
        result.setTotal(source.getTotalElements());
        result.setItems(source.getContent());
        return result;
    }
}
