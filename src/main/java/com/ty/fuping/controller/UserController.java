package com.ty.fuping.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ty.fuping.entity.District;
import com.ty.fuping.entity.Town;
import com.ty.fuping.entity.User;
import com.ty.fuping.repository.DistrictRepository;
import com.ty.fuping.repository.TownRepository;
import com.ty.fuping.service.UserService;
import com.ty.fuping.util.MD5Util;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2018/1/19.
 * 登录
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private TownRepository townRepository;

    //添加user
    @RequestMapping("/adduser")
    @Transactional
    public User addUser(User user,
                        @RequestParam(name = "districtId", required = false) Integer districtId,
                        @RequestParam(name = "townId", required = false) Integer townId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        District district = null;
        Town town = null;
        if (districtId != null) {
            district = new District();
            district.setDistrictId(districtId);
        } else {
            district = new District();
            district.setDistrictId(1);
        }
        if (townId != null) {
            town = new Town();
            town.setTownId(townId);
        } else {
            town = new Town();
            town.setTownId(1);
        }
        user.setDistrict(district);
        user.setTown(town);
        return userService.addUser(user);
    }

    //删除user
    @RequestMapping("/deleteuser")
    public void deletUser(@RequestParam("userId") Integer userId) {
        userService.deletUser(userId);
    }

    //修改user
    @Transactional
    @RequestMapping("/updateuser")
    public User updateUser(User user, @RequestParam(name = "districtId", required = false) Integer districtId,
                           @RequestParam(name = "townId", required = false) Integer townId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (user.getUserId() == null)
            return null;

        District district = null;
        Town town = null;
        if (districtId != null) {
            district = new District();
            district.setDistrictId(districtId);
        } else {
            district = new District();
            district.setDistrictId(districtRepository.findAll().iterator().next().getDistrictId());
        }
        if (townId != null) {
            town = new Town();
            town.setTownId(townId);
        } else {
            town = new Town();
            town.setTownId(townRepository.findAllByDistrict_DistrictId(district.getDistrictId()).iterator().next().getTownId());
        }
        user.setDistrict(district);
        user.setTown(town);
        return userService.updateUser(user);
    }

    //搜索User
    @RequestMapping("/searchuser")
    public JSONArray searchUser(@RequestParam(name = "userId", required = false) Integer userId,
                                @RequestParam(name = "userType", required = false) String userType,
                                @RequestParam(name = "userName", required = false) String userName,
                                @RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "districtId", required = false) Integer districtId,
                                @RequestParam(name = "townId", required = false) Integer townId) throws JsonProcessingException, ParseException {

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        ObjectMapper mapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();
        String str;
        User user;
        List<User> userList = userService.searchUser(userId, userType, userName, name, districtId, townId);
        Iterator<User> userIterator = userList.iterator();
        while (userIterator.hasNext()) {
            user = userIterator.next();
            str = mapper.writeValueAsString(user);
            jsonObject = (JSONObject) jsonParser.parse(str);
            if (user.getUserType() != null) {
                if (user.getUserType().equals("超级管理员") || user.getUserType().equals("市级管理员"))
                    jsonObject.put("managePart", "全市");
                else if (user.getUserType().equals("区县管理员"))
                    jsonObject.put("managePart", user.getDistrict().getDistrictName());
                else if (user.getUserType().equals("乡镇管理员")) {
                    jsonObject.put("managePart", user.getDistrict().getDistrictName() + "-" + user.getTown().getTownName());
                }
            } else {
                jsonObject.put("managePart", null);
            }

            jsonObject.put("districtId", user.getDistrict().getDistrictId());
            jsonObject.put("districtName", user.getDistrict().getDistrictName());
            jsonObject.put("townId", user.getTown().getTownId());
            jsonObject.put("townName", user.getTown().getTownName());
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    @RequestMapping("/install")
    public boolean install() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = new User();
        user.setUserName("admin");
        user.setPassWord(MD5Util.EncoderPwdByMd5("111111"));
        user.setName("管理员");
        user.setUserType("超级管理员");
        user = userService.addUser(user);
        if (null != user) {
            return true;
        }
        return false;
    }
}
