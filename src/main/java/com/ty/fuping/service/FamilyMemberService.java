package com.ty.fuping.service;

import com.ty.fuping.entity.FamilyMember;
import com.ty.fuping.repository.FamilyMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by ty on 2017/12/16.
 */
@Service
public class FamilyMemberService {

    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    //添加家庭成员
    public FamilyMember addFamilyMember(FamilyMember familyMember){
        return familyMemberRepository.save(familyMember);
    }

    //删除家庭成员
    public void deleteFamilyMemberById(Integer familyMemberId){
        familyMemberRepository.delete(familyMemberId);
    }

    //通过家庭Id获取家庭成员们
    public List<FamilyMember> getFamilyMemberByFamilyId(Integer familyId){
        return familyMemberRepository.findAllByFamily_FamilyId(familyId);
    }
}
