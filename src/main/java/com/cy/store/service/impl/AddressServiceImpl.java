package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    AddressMapper addressMapper;

    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}") // Read constant from property file
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        // check if address of user exceeds limit
        Integer count = addressMapper.countByUid(uid);

        if(count >= maxCount) {
            throw new AddressCountLimitException("Number of user address exceeds limit");
        }

        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

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

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        // Set unwanted properties as null to boost server performance
        for(Address address : list) {
            address.setAid(null);
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setZip(null);
            address.setTel(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return list;
    }
}
