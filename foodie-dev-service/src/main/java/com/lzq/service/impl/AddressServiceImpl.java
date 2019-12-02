package com.lzq.service.impl;

import com.lzq.bo.AddressBO;
import com.lzq.enums.YesOrNo;
import com.lzq.mapper.UserAddressMapper;
import com.lzq.pojo.UserAddress;
import com.lzq.service.AddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private Sid sid;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryAll(String userId) {
       UserAddress address = new UserAddress();
       address.setUserId(userId);
        return userAddressMapper.select(address);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addNewUserAddress(AddressBO addressBO) {
        // 1. 判断当前用户是否存在地址，如果没有，则新增为‘默认地址’
        Integer isDefault = 0;
        List<UserAddress> userAddresses = queryAll(addressBO.getUserId());
        if (userAddresses == null ||userAddresses.size() ==0 || userAddresses.isEmpty()){
            isDefault = 1;
        }
        String addressId = sid.nextShort();
        UserAddress address = new UserAddress();
        BeanUtils.copyProperties(addressBO,address);
        address.setIsDefault(isDefault);
        address.setCreatedTime(new Date());
        address.setUpdatedTime(new Date());
        address.setId(addressId);
        userAddressMapper.insert(address);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserAddress(AddressBO addressBO) {
        String addressId = addressBO.getAddressId();
        UserAddress pendingAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, pendingAddress);
        pendingAddress.setId(addressId);
        pendingAddress.setUpdatedTime(new Date());
        userAddressMapper.updateByPrimaryKeySelective(pendingAddress);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUserAddress(String userId, String addressId) {
        UserAddress address = new UserAddress();
        address.setId(addressId);
        address.setUserId(userId);
        userAddressMapper.delete(address);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserAddressToBeDefault(String userId, String addressId) {
        // 1. 查找默认地址，设置为不默认
        UserAddress address = new UserAddress();
        address.setUserId(userId);
        address.setIsDefault(YesOrNo.YES.getType());
        UserAddress dbResult = userAddressMapper.selectOne(address);
        dbResult.setIsDefault(YesOrNo.NO.getType());
        userAddressMapper.updateByPrimaryKeySelective(dbResult);
        // 2. 根据地址id修改为默认的地址
        UserAddress defaultAddress = new UserAddress();
        defaultAddress.setId(addressId);
        defaultAddress.setUserId(userId);
        defaultAddress.setIsDefault(YesOrNo.YES.getType());
        userAddressMapper.updateByPrimaryKeySelective(defaultAddress);
    }
}
