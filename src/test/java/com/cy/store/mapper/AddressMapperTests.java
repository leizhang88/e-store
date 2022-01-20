package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(11);
        address.setPhone("8004008888");
        address.setName("Gavin");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid() {
       System.err.println(addressMapper.countByUid(11));
    }

    @Test
    public void findByUid() {
        List<Address> list = addressMapper.findByUid(11);
        System.out.println(list);
    }

    @Test
    public void findByAid() {
        System.err.println(addressMapper.findByAid(4));
    }

    @Test
    public void updateNonDefault() {
        addressMapper.updateNonDefault(11);
    }

    @Test
    public void updateDefaultByAid() {
        addressMapper.updateDefaultByAid(4, "admin", new Date());
    }

    @Test
    public void deleteByAid() {
        addressMapper.deleteByAid(6);
    }

    @Test
    public void findLastModified() {
        System.err.println(addressMapper.findLastModified(11));
    }
}
