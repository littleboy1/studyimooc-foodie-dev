package com.lzq.service;

import com.lzq.bo.AddressBO;
import com.lzq.pojo.UserAddress;

import java.util.List;

public interface AddressService {

    List<UserAddress> queryAll(String userId);

    void addNewUserAddress(AddressBO addressBO);

    void updateUserAddress(AddressBO addressBO);

    void deleteUserAddress(String userId, String addressId);

    void updateUserAddressToBeDefault(String userId, String addressId);

}
