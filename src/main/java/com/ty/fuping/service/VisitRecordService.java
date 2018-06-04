package com.ty.fuping.service;

import com.ty.fuping.entity.VisitRecord;
import com.ty.fuping.repository.VisitRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

/**
 * Created by ty on 2018/1/12.
 * 走访记录
 */
@Service
public class VisitRecordService {

    @Autowired
    private VisitRecordRepository visitRecordRepository;

    @Value("${web.upload-path}")
    private String path;

    //通过家庭Id获取走访记录
    public List<VisitRecord> getVisitRecordByFamilyId(Integer familyId) {
        return visitRecordRepository.findAllByFamily_FamilyId(familyId);
    }

    //通过Id删除走访记录
    @Transactional
    public void deleteVisitRecordById(Integer visitRecordId) {
        //删除图片
        String oldPath = visitRecordRepository.findOne(visitRecordId).getPhotoPath();
        File file = new File(path + "/" + oldPath);
        if (file.exists()) {
            file.delete();
        }
        //删除走访记录
        visitRecordRepository.delete(visitRecordId);
    }
}
