package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    AddressMapper addressMapper;

    @Value("${user.address.max-count}") // Read constant from property file
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        // check if address of user exceeds limit
        Integer count = addressMapper.countByUid(uid);

        if(count >= maxCount) {
            throw new AddressCountLimitException("Number of user address exceeds limit");
        }

        address.setUid(uid);
        address.setIsDefault(count == 0 ? 1 : 0);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());

        Integer rows = addressMapper.insert(address);
        if(rows != 1) {
            throw new InsertException("Error occurred when inserting new address");
        }
    }
}
