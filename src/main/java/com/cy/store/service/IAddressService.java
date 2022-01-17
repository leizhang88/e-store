package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);

    List<Address> getByUid(Integer uid);

    /**
     * Set user's address as default
     * @param aid The aid of address
     * @param uid The user the address belongs to
     * @param username The user who modifies
     */
    void setDefault(Integer aid, Integer uid, String username);
}
