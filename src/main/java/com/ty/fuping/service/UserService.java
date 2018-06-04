package com.ty.fuping.service;

import com.ty.fuping.entity.District;
import com.ty.fuping.entity.Town;
import com.ty.fuping.entity.User;
import com.ty.fuping.repository.UserRepository;
import com.ty.fuping.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ty on 2018/1/19.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //添加User
    @Transactional
    public User addUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        user.setPassWord(MD5Util.EncoderPwdByMd5(user.getPassWord()));
        return userRepository.save(user);
    }

    //修改User
    @Transactional
    public User updateUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (user.getPassWord() != null)
            user.setPassWord(MD5Util.EncoderPwdByMd5(user.getPassWord()));
        else
            user.setPassWord(userRepository.findOne(user.getUserId()).getPassWord());
        return userRepository.save(user);
    }

    //删除user
    public void deletUser(Integer userId) {
        userRepository.delete(userId);
    }

    public List<User> searchUser(Integer userId, String userType, String userName, String name, Integer districtId, Integer townId) {

        List<User> userList = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (userId != null)
                    predicates.add(criteriaBuilder.equal(root.get("userId"), userId));
                if (userType != null)
                    predicates.add(criteriaBuilder.like(root.get("userType"), userType));
                if (userName != null)
                    predicates.add(criteriaBuilder.like(root.get("userName"), userName));
                if (name != null)
                    predicates.add(criteriaBuilder.like(root.get("name"), name));
                if (districtId != null)
                    predicates.add(criteriaBuilder.equal(root.get("district").get("districtId"), districtId));
                if (townId != null)
                    predicates.add(criteriaBuilder.equal(root.get("town").get("townId"), townId));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
        return userList;
    }

    //通过userName查找user
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
