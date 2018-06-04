package com.ty.fuping.repository;

import com.ty.fuping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by ty on 2018/1/19.
 * 用户、权限管理
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    //通过用户名查找
    User findByUserName(String userName);
}
