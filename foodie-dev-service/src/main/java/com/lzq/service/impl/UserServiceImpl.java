package com.lzq.service.impl;

import com.lzq.bo.UserBo;
import com.lzq.enums.Sex;
import com.lzq.mapper.UsersMapper;
import com.lzq.pojo.Users;
import com.lzq.service.UserService;
import com.lzq.utils.DateUtil;
import com.lzq.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UsersMapper userMapper;

    @Autowired
    private Sid sid;

    public static final String USER_FACE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574951061&di=1af3d6325e5b484906a1a68183b41d2e&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.mp.sohu.com%2Fupload%2F20170711%2F6b2b536e83b74c5282191d2986d0c285_th.png";

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUserNameIsExist(String username) {
        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username",username);
        Users result = userMapper.selectOneByExample(userExample);
        return result == null ? false : true;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBo userBo) {
        Users user = new Users();
        String userId = sid.nextShort();
        user.setId(userId);
        user.setUsername(userBo.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setNickname(userBo.getUsername());
        user.setFace(USER_FACE);
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        user.setSex(Sex.secret.getType());
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        userMapper.insert(user);
        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username",username);
        criteria.andEqualTo("password", password);
        Users result = userMapper.selectOneByExample(userExample);
        return result;
    }
}
