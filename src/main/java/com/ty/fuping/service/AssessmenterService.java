package com.ty.fuping.service;

import com.ty.fuping.entity.AssessmentandAssessmenter;
import com.ty.fuping.entity.Assessmenter;
import com.ty.fuping.entity.Town;
import com.ty.fuping.repository.AssessmentandAssessmenterRepository;
import com.ty.fuping.repository.AssessmenterRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2017/11/2.
 */
@Service
public class AssessmenterService {

    @Autowired
    private AssessmenterRepository assessmenterRepository;
    @Autowired
    private AssessmentandAssessmenterRepository assessmentandAssessmenterRepository;

    @Transactional
    //添加考核者
    public Assessmenter addAssessmenter(Assessmenter assessmenter) {
        return assessmenterRepository.save(assessmenter);
    }

    //获取所有考核者
    public List<Assessmenter> getAssessmenterAll() {
        return assessmenterRepository.findAll();
    }

    @Transactional
    //通过镇获取考核者
    public List<Assessmenter> getAssessmenterByTown(Integer townId) {
        Town town = new Town();
        town.setTownId(townId);
        return assessmenterRepository.getAllByTown(town);
    }

    @Transactional
    //通过考核计划Id获取考核者
    public List<Assessmenter> getAssessmenterByPlanId(Integer planId) {
        List<AssessmentandAssessmenter> assessmentandAssessmenters = assessmentandAssessmenterRepository.getAllByAssessmentPlan_AssessmentPlanId(planId);
        List<Assessmenter> assessmenterList = new ArrayList<>();
        Iterator<AssessmentandAssessmenter> it = assessmentandAssessmenters.iterator();
        while (it.hasNext()) {
            assessmenterList.add(it.next().getAssessmenter());
        }
        return assessmenterList;
    }

    //通过Id获取考核者
    public Assessmenter getAssessmenterById(Integer id) {
        return assessmenterRepository.findOne(id);
    }

    //通过多条件模糊查询科技特派员
    public List<Assessmenter> searchAssessmenters(Assessmenter assessmenter) {

        Assert.assertNotNull(assessmenter);

        List<Assessmenter> assessmenterList = assessmenterRepository.findAll(new Specification<Assessmenter>() {
            @Override
            public Predicate toPredicate(Root<Assessmenter> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (assessmenter.getName() != null && !assessmenter.getName().equals("")) {
                    predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + assessmenter.getName() + "%"));
                }
                if (assessmenter.getTown() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("town").get("townId"), assessmenter.getTown().getTownId()));
                }
                if (assessmenter.getDanweiaddress() != null && !assessmenter.getDanweiaddress().equals("")) {
                    predicates.add(criteriaBuilder.like(root.get("danweiaddress"), "%" + assessmenter.getDanweiaddress() + "%"));
                }
                if (assessmenter.getPhoneNumber() != null && !assessmenter.getPhoneNumber().equals("")) {
                    predicates.add(criteriaBuilder.like(root.get("phoneNumber"), "%" + assessmenter.getPhoneNumber() + "%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });

        return assessmenterList;
    }

    @Transactional
    //删除考核者
    public void deleteAssessmenterById(Integer assessmenterId) {
        assessmentandAssessmenterRepository.deleteAllByAssessmenter_AssessmenterId(assessmenterId);
        assessmenterRepository.deleteAssessmenterByAssessmenterId(assessmenterId);
    }

    //通过用户名获取考核者
    public Assessmenter findByUserName(String userName) {
        return assessmenterRepository.findByAccount(userName);
    }
}
