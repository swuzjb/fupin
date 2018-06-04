package com.ty.fuping.controller;

import com.ty.fuping.entity.Family;
import com.ty.fuping.entity.FamilyMember;
import com.ty.fuping.service.FamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ty on 2017/12/16.
 * 家庭成员
 */
@RestController
public class FamilyMemberController {

    @Autowired
    private FamilyMemberService familyMemberService;

    //添加家庭成员
    @RequestMapping("/addfamilymember")
    public FamilyMember addFamilyMember(FamilyMember familyMember, @RequestParam("familyId") Integer familyId){
        Family family=new Family();
        family.setFamilyId(familyId);
        familyMember.setFamily(family);
        return familyMemberService.addFamilyMember(familyMember);
    }

    //删除家庭成员
    @RequestMapping("/deletefamilymember")
    public void deleteFamilyMember(@RequestParam("familyMemberId") Integer familyMemberId){
        familyMemberService.deleteFamilyMemberById(familyMemberId);
    }

    //通过家庭Id获取家庭成员们
    @RequestMapping("/getmemberbyfamilyid")
    public List<FamilyMember> getFamilyMemberByFamilyId(@RequestParam("familyId") Integer familyId){
        return familyMemberService.getFamilyMemberByFamilyId(familyId);
    }

    //修改家庭成员
    @RequestMapping("/updatefamilymember")
    public FamilyMember updateFamilyMember(FamilyMember familyMember, @RequestParam("familyId") Integer familyId){
        Family family=new Family();
        family.setFamilyId(familyId);
        familyMember.setFamily(family);
        return familyMemberService.addFamilyMember(familyMember);
    }

}
