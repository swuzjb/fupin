package com.ty.fuping.service;

import com.ty.fuping.entity.Removal;
import com.ty.fuping.repository.RemovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ty on 2017/12/16.
 */
@Service
public class RemovalService {

    @Autowired
    private RemovalRepository removalRepository;

    //添加异地搬迁记录
    @Transactional
    public Removal addRemoval(Removal removal) {
        return removalRepository.save(removal);
    }

    //通过家庭Id获取搬迁记录
    public List<Removal> getRemovalByFamilyId(Integer familyId) {
        return removalRepository.findAllByFamily_FamilyId(familyId);
    }

    //删除搬迁记录
    public void deleteremovalById(Integer removalId){
        removalRepository.delete(removalId);
    }
}
