package com.ty.fuping.service;

import com.ty.fuping.entity.WorkingAndLivingCondition;
import com.ty.fuping.repository.WorkingAndLivingConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ty on 2017/12/16.
 */
@Service
public class WorkingAndLivingConditionService {

    @Autowired
    private WorkingAndLivingConditionRepository workingAndLivingConditionRepository;

    //添加一个生产生活条件
    @Transactional
    public WorkingAndLivingCondition addWALCondition(WorkingAndLivingCondition workingAndLivingCondition) {
        return workingAndLivingConditionRepository.save(workingAndLivingCondition);
    }

    //通过家庭Id获取生产生活条件
    public WorkingAndLivingCondition getWALConditionByFamilyId(Integer familyId){
        return workingAndLivingConditionRepository.getByFamily_FamilyId(familyId);
    }

    //删除生产生活条件
    public void deleteWALConditionById(Integer WALCId){
        workingAndLivingConditionRepository.delete(WALCId);
    }
}
