package com.cy.store.mapper;

import com.cy.store.entity.District;

import java.util.List;

public interface DistrictMapper {
    /**
     * Find districts by parent code
     * @param parent
     * @return
     */
    List<District> findByParent(String parent);

    String findNameByCode(String code);
}
