package com.lzq.service.impl;

import com.lzq.service.StuService;
import com.lzq.service.TestTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestTranServiceImpl implements TestTransService {

    @Autowired
    private StuService service;

    /**
     * 事务的传播机制
     * REQUIRED 使用当前的事务，如果当前没有事务，则自己新建一个事务，
     * 如果当前存在事务，则加入这个事务成为一个整体 举例：领导没饭吃，我有钱，我自己买的吃
     * 如果领导有的吃，则分配给我吃的
     * SUPPORTS 如果当前有事务则使用事务，如果当前没有事务，则不使用事务
     * 举例 领导没饭吃 我也没饭吃  领导有饭吃 我就有饭吃
     * MANDATORY  该传播属性强制必须存在一个事务，如果不存在则抛出异常
     * 举例：领导必须吃饭 不管吃没吃饭 我就不乐意 就不干了抛出异常
     * REQUIRES_NEW  如果当前有事务，则挂起该事务，并且自己创建一个新的事务给自己
     * 如果当前没有事务，则同required
     * NOT_SUPPORTED 如果当前有事务，则把事务挂起，自己不使用事务去操作运行数据库
     * NEVER 如果当前有事务存在则抛出异常
     * NESTED 如果当前有事务，则开启子事务（嵌套事务），嵌套事务是独立提交或回滚
     * 如果当前没有事务，则与required一直
     * 如果当前主事务提交，则会携带子事务一起提交
     * 如果主事务回滚则子事务会一起回滚，相反子事务异常，则父事务可以回滚也可以不回滚
     * SpringBoot已经开启了事务的自动配置
     *
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagationTrans() {

        service.saveParent();
        try{
            service.saveChildren();  //save point
        }catch (Exception e){

        }



    }
}
