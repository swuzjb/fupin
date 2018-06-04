package com.ty.fuping.controller;

import com.ty.fuping.entity.Family;
import com.ty.fuping.entity.VisitRecord;
import com.ty.fuping.repository.VisitRecordRepository;
import com.ty.fuping.service.VisitRecordService;
import com.ty.fuping.util.GetImageNameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by ty on 2018/1/7.
 * 走访记录
 */
@RestController
public class VisitRecordController {

    @Autowired
    private VisitRecordRepository visitRecordRepository;
    @Autowired
    private VisitRecordService visitRecordService;

    @Value("${web.upload-path}")
    private String path;

    //新增走访记录
    @Transactional
    @RequestMapping("/addvisitrecord")
    public VisitRecord addVisitRecord(VisitRecord visitRecord,
                                      @RequestParam(value = "familyId") Integer familyId,
                                      @RequestParam(value = "visitImage", required = false) MultipartFile visitImage,
                                      HttpServletRequest request) throws IOException {
        //添加家庭
        Family family = new Family();
        family.setFamilyId(familyId);
        visitRecord.setFamily(family);
        //添加图片以及图片路径
        String URL = request.getRequestURL().toString();
        String URL_path = URL.substring(0, URL.lastIndexOf("/") + 1);
        String imageNmae = null;
        if (visitImage != null) {
            imageNmae = GetImageNameUtil.getImageNameByTime(visitImage.getOriginalFilename());
            visitRecord.setPhotoPath("visitImages/" + imageNmae);
            File file = new File(path + "/visitImages/" + imageNmae);
            //如果路径不存在创造路径
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            visitImage.transferTo(file);
        }
        return visitRecordRepository.save(visitRecord);
    }

    //修改走访记录
    @Transactional
    @RequestMapping("/updatevisitrecord")
    public VisitRecord updateVisitRecord(VisitRecord visitRecord,
                                         @RequestParam(value = "familyId") Integer familyId,
                                         @RequestParam(value = "visitImage", required = false) MultipartFile visitImage,
                                         HttpServletRequest request) throws IOException {
        String oldPath = visitRecordRepository.findOne(visitRecord.getVisitRecordId()).getPhotoPath();
        File file;
        //上传新的就删除原来的图片
        if(visitImage!=null){
            file = new File(path + "/" + oldPath);
            if (file.exists()) {
                file.delete();
            }
            //添加家庭
            Family family = new Family();
            family.setFamilyId(familyId);
            visitRecord.setFamily(family);
            //添加图片以及图片路径
            String URL = request.getRequestURL().toString();
            String URL_path = URL.substring(0, URL.lastIndexOf("/") + 1);
            String imageNmae = null;
            if (visitImage != null) {
                imageNmae = GetImageNameUtil.getImageNameByTime(visitImage.getOriginalFilename());
                visitRecord.setPhotoPath("visitImages/" + imageNmae);
                file = new File(path + "/visitImages/" + imageNmae);
                //如果路径不存在创造路径
                File fileParent = file.getParentFile();
                if (!fileParent.exists()) {
                    fileParent.mkdirs();
                }
                visitImage.transferTo(file);
            }
        }else {
            //添加家庭
            Family family = new Family();
            family.setFamilyId(familyId);
            visitRecord.setFamily(family);
            //图片路径还是原来的图片路径
            visitRecord.setPhotoPath(visitRecordRepository.findOne(visitRecord.getVisitRecordId()).getPhotoPath());
        }
        return visitRecordRepository.save(visitRecord);
    }

    //通过家庭Id获取走访记录
    @RequestMapping("/getvisitrecordbyfamilyid")
    public List<VisitRecord> getVisitRecordByFamilyId(@RequestParam("familyId") Integer familyId) {
        return visitRecordService.getVisitRecordByFamilyId(familyId);
    }

    //删除走访记录
    @Transactional
    @RequestMapping("/deledvisitrecordbyid")
    public void deleteVisitRecordById(@RequestParam("visitRecordId") Integer visitRecordId) {
        visitRecordService.deleteVisitRecordById(visitRecordId);
    }
}
