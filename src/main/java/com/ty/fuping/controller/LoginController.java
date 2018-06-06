package com.ty.fuping.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.Assessmenter;
import com.ty.fuping.entity.User;
import com.ty.fuping.repository.AssessmentObjectRepository;
import com.ty.fuping.repository.AssessmenterRepository;
import com.ty.fuping.repository.UserRepository;
import com.ty.fuping.service.AssessmentObjectService;
import com.ty.fuping.service.AssessmenterService;
import com.ty.fuping.service.UserService;
import com.ty.fuping.util.MD5Util;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ty on 2018/1/19.
 * 登录、修改密码
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private AssessmenterService assessmenterService;
    @Autowired
    private AssessmentObjectService assessmentObjectService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssessmentObjectRepository assessmentObjectRepository;
    @Autowired
    private AssessmenterRepository assessmenterRepository;


    @RequestMapping("/login")
    public JSONObject login(HttpSession session,
                            @RequestParam("userName") String userName,
                            @RequestParam("passWord") String passWord) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        JSONObject jsonObject = new JSONObject();

        //如果在user中找到了
        if (userService.findUserByUserName(userName) != null) {
            User user = userService.findUserByUserName(userName);
            //如果密码正确
            if (user.getPassWord().equals(MD5Util.EncoderPwdByMd5(passWord))) {
                jsonObject.put("userName", user.getUserName());
                jsonObject.put("password", passWord);
                jsonObject.put("name", user.getName());
                jsonObject.put("userType", user.getUserType());
                jsonObject.put("mobilePhone", user.getMobilePhone());
                jsonObject.put("telephone", user.getTelephone());
                jsonObject.put("workUnit", user.getWorkUnit());
                jsonObject.put("position", user.getPosition());
                jsonObject.put("remark", user.getRemark());
                jsonObject.put("districtId", user.getDistrict() != null ? user.getDistrict().getDistrictId() : null);
                jsonObject.put("districtName", user.getDistrict() != null ? user.getDistrict().getDistrictName() : null);
                jsonObject.put("townId", user.getTown() != null ? user.getTown().getTownId() : null);
                jsonObject.put("townName", user.getTown() != null ? user.getTown().getTownName() : null);

                session.setAttribute("userName", userName);
            } else { //如果密码不正确
                jsonObject.put("errorId", 1);
                jsonObject.put("errorMessage", "密码不正确");
            }
            //如果在考核者中找到了
        } else if (assessmenterService.findByUserName(userName) != null) {
            Assessmenter assessmenter = assessmenterService.findByUserName(userName);
            //如果密码正确
            if (assessmenter.getPassword().equals(passWord)) {
                jsonObject.put("userName", assessmenter.getAccount());
                jsonObject.put("password", passWord);
                jsonObject.put("name", assessmenter.getName());
                jsonObject.put("userType", "帮扶队长");
                jsonObject.put("mobilePhone", assessmenter.getPhoneNumber());
                jsonObject.put("telephone", assessmenter.getPhoneNumber());
                jsonObject.put("workUnit", assessmenter.getDanwei());
                jsonObject.put("position", assessmenter.getZhiwu());
                jsonObject.put("remark", null);
                jsonObject.put("districtId", assessmenter.getTown().getDistrict().getDistrictId());
                jsonObject.put("districtName", assessmenter.getTown().getDistrict().getDistrictName());
                jsonObject.put("townId", assessmenter.getTown().getTownId());
                jsonObject.put("townName", assessmenter.getTown().getTownName());

                session.setAttribute("userName", userName);
            } else {//如果密码不正确
                jsonObject.put("errorId", 1);
                jsonObject.put("errorMessage", "密码不正确");
            }
        } else if (assessmentObjectService.findByUserName(userName) != null) {
            AssessmentObject assessmentObject = assessmentObjectService.findByUserName(userName);
            //如果密码正确
            if (assessmentObject.getPassword().equals(passWord)) {
                jsonObject.put("userName", assessmentObject.getAccount());
                jsonObject.put("password", passWord);
                jsonObject.put("name", assessmentObject.getName());
                jsonObject.put("userType", "科技特派员");
                jsonObject.put("mobilePhone", assessmentObject.getPhoneNumber());
                jsonObject.put("telephone", assessmentObject.getPhoneNumber());
                jsonObject.put("workUnit", assessmentObject.getDanwei());
                jsonObject.put("position", assessmentObject.getZhiwu());
                jsonObject.put("remark", null);
                jsonObject.put("districtId", assessmentObject.getTown().getDistrict().getDistrictId());
                jsonObject.put("districtName", assessmentObject.getTown().getDistrict().getDistrictName());
                jsonObject.put("townId", assessmentObject.getTown().getTownId());

                session.setAttribute("userName", userName);
            } else {//如果密码不正确
                jsonObject.put("errorId", 1);
                jsonObject.put("errorMessage", "密码不正确");
            }
        } else {//没有用户名匹配
            jsonObject.put("errorId", 2);
            jsonObject.put("errorMessage", "用户民不存在");
        }
        return jsonObject;
    }

    @RequestMapping("/changepassword")
    public JSONObject changePassWord(@RequestParam("userName") String userName,
                                     @RequestParam("oldPassWord") String oldPassWord,
                                     @RequestParam("newPassWord") String newPassWord) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        JSONObject jsonObject = new JSONObject();

        //如果在user中找到了
        if (userService.findUserByUserName(userName) != null) {
            User user = userService.findUserByUserName(userName);
            //如果密码正确
            if (user.getPassWord().equals(MD5Util.EncoderPwdByMd5(oldPassWord))) {
                user.setPassWord(MD5Util.EncoderPwdByMd5(newPassWord));
                userRepository.save(user);
                jsonObject.put("message", "修改成功");
            } else { //如果密码不正确
                jsonObject.put("errorId", 1);
                jsonObject.put("errorMessage", "密码不正确");
            }
            //如果在考核者中找到了
        } else if (assessmenterService.findByUserName(userName) != null) {
            Assessmenter assessmenter = assessmenterService.findByUserName(userName);
            //如果密码正确
            if (assessmenter.getPassword().equals(oldPassWord)) {
                assessmenter.setPassword(newPassWord);
                assessmenterRepository.save(assessmenter);
                jsonObject.put("message", "修改成功");
            } else {//如果密码不正确
                jsonObject.put("errorId", 1);
                jsonObject.put("errorMessage", "密码不正确");
            }
        } else if (assessmentObjectService.findByUserName(userName) != null) {
            AssessmentObject assessmentObject = assessmentObjectService.findByUserName(userName);
            //如果密码正确
            if (assessmentObject.getPassword().equals(oldPassWord)) {
                assessmentObject.setPassword(newPassWord);
                assessmentObjectRepository.save(assessmentObject);
                jsonObject.put("message", "修改成功");
            } else {//如果密码不正确
                jsonObject.put("errorId", 1);
                jsonObject.put("errorMessage", "密码不正确");
            }
        } else {//没有用户名匹配
            jsonObject.put("errorId", 2);
            jsonObject.put("errorMessage", "用户民不存在");
        }
        return jsonObject;

    }
}
