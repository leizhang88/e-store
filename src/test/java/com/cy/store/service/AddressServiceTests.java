package com.cy.store.service;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setPhone("8004001234");
        address.setName("Work");
        addressService.addNewAddress(11, "admin", address);
    }

    @Test
    public void setDefault() {
        addressService.setDefault(5,11, "gavin");
    }

    @Test
    public void delete() {
        addressService.delete(9, 11, "admin");
    }
}
