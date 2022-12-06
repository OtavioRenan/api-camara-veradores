package br.gov.application.camaramunicipal.utils;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class PageableUtil {
    public <T> Page<T> pageable(List<T> list, int offSet, int pageSize) {
        int size = list.size();
        
        int max = ( pageSize * ( offSet + 1 ) > size ) ? size : pageSize * ( offSet + 1 );

        return new PageImpl<>(list.subList(offSet * pageSize, max), PageRequest.of(offSet, pageSize), size);
    }
}