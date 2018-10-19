package com.genesisfin.backend.web.viewmodel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PagedResult<T> {
    private Long total;

    private List<T> items;

}

