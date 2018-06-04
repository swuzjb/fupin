package com.ty.fuping.service;

import com.ty.fuping.entity.Assistance;
import com.ty.fuping.repository.AssistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ty on 2017/12/17.
 */
@Service
public class AssistanceService {

    @Autowired
    private AssistanceRepository assistanceRepository;

    //添加帮扶责任人
    public Assistance addAssistance(Assistance assistance){
        return assistanceRepository.save(assistance);
    }

    //通过家庭Id获取帮扶责任人
    public List<Assistance> getAssistanceByFamilyId(Integer familyId){
        return assistanceRepository.findAllByFamily_FamilyId(familyId);
    }

    //删除搬迁记录
    public void deleteAssistanceById( Integer assistanceId){
        assistanceRepository.delete(assistanceId);
    }
}
