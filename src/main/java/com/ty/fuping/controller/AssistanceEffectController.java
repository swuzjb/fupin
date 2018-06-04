package com.ty.fuping.controller;

import com.ty.fuping.entity.AssistanceEffect;
import com.ty.fuping.entity.Family;
import com.ty.fuping.repository.AssistanceEffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by ty on 2017/12/23.
 * 帮扶成效
 */
@RestController
public class AssistanceEffectController {
    @Autowired
    private AssistanceEffectRepository assistanceEffectRepository;

    //新增帮扶成效
    @RequestMapping("/adddassistanceeffect")
    public AssistanceEffect addAssistanceEffect(AssistanceEffect assistanceEffect,
                                              @RequestParam("familyId") Integer familyId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        assistanceEffect.setFamily(family);
        return assistanceEffectRepository.save(assistanceEffect);
    }

    //修改帮扶成效
    @Transactional
    @RequestMapping("/updateassistanceeffect")
    public AssistanceEffect updateAssistanceEffect(AssistanceEffect assistanceEffect,
                                                   @RequestParam("familyId") Integer familyId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        assistanceEffect.setFamily(family);
        return assistanceEffectRepository.save(assistanceEffect);
    }

    //通过家庭Id获取帮扶成效
    @RequestMapping("/getassistanceeffectbyfamilyid")
    public List<AssistanceEffect> getDrinkingSafetyByFamilyId(@RequestParam("familyId") Integer familyId) {
        return assistanceEffectRepository.findAllByFamily_FamilyId(familyId);
    }

    //删除帮扶成效
    @Transactional
    @RequestMapping("/deleassistanceeffectbyid")
    public void deleteAssistanceEffectById(@RequestParam("assistanceEffectId") Integer assistanceEffectId) {
        assistanceEffectRepository.delete(assistanceEffectId);
    }
}
