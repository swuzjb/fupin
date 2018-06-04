package com.ty.fuping.util;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.service.AssessmentObjectService;
import com.ty.fuping.service.AssessmenterService;
import com.ty.fuping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ty on 2018/1/20.
 * 检查用户名是否已经被是用了
 */
public class CheckUserNameUtil {

    @Autowired
    private UserService userService;
    @Autowired
    private AssessmentObjectService assessmentObjectService;
    @Autowired
    private AssessmenterService assessmenterService;

    public boolean canUseUserName(String userName) {
        if (userService.findUserByUserName(userName) == null
                && assessmenterService.findByUserName(userName) == null
                && assessmentObjectService.findByUserName(userName) == null)
            return true;
        else
            return false;
    }
}
