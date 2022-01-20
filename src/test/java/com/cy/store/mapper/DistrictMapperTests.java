package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {
    @Autowired
    DistrictMapper districtMapper;

    @Test
    public void findByParent() {
        List<District> result = districtMapper.findByParent("210100");
        for(District district : result)
            System.out.println(district);
    }

    @Test
    public void findByCode() {
        String name = districtMapper.findNameByCode("210100");
        System.out.println(name);
    }
}
