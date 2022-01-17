package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AddressMapper {
    Integer insert(Address address);

    Integer countByUid(Integer uid);

    List<Address> findByUid(Integer uid);

    Address findByAid(Integer aid);

    /**
     * Set addresses of user referenced by uid as non-default
     * @param uid
     * @return The number of records updated
     */
    Integer updateNonDefault(Integer uid);

    /**
     * Set the selected address of givien aid as default
     * @param aid
     * @return
     */
    Integer updateDefaultByAid(
            @Param("aid") Integer aid,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
}
