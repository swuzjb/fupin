package com.ty.fuping.service;

import com.ty.fuping.controller.VisitRecordController;
import com.ty.fuping.entity.Assistance;
import com.ty.fuping.entity.AssistanceMeasure.FinancialAssistance;
import com.ty.fuping.entity.Family;
import com.ty.fuping.entity.VisitRecord;
import com.ty.fuping.repository.*;
import com.ty.fuping.repository.AssistanceMeasureRepository.*;
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
 * Created by ty on 2017/12/12.
 */
@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;
    @Autowired
    private FamilyMemberRepository familyMemberRepository;
    @Autowired
    private WorkingAndLivingConditionRepository workingAndLivingConditionRepository;
    @Autowired
    private RemovalRepository removalRepository;
    @Autowired
    private AssistanceRepository assistanceRepository;
    @Autowired
    private DrinkingSafetyRepository drinkingSafetyRepository;
    @Autowired
    private FinancialAssistanceRepository financialAssistanceRepository;
    @Autowired
    private HouseRenovationRepository houseRenovationRepository;
    @Autowired
    private LaborTrainingRepository laborTrainingRepository;
    @Autowired
    private ProsperousIndustryRepository prosperousIndustryRepository;
    @Autowired
    private SocialAssistanceRepository socialAssistanceRepository;
    @Autowired
    private SpecialPovertyAlleviationRepository specialPovertyAlleviationRepository;
    @Autowired
    private AssistanceEffectRepository assistanceEffectRepository;
    @Autowired
    private VisitRecordService visitRecordService;

    //增加贫困户
    public Family addFamily(Family family) {
        return familyRepository.save(family);
    }

    //修改贫困户
    public Family updateFamily(Family family) {
        return familyRepository.save(family);
    }

    //删除贫困户
    @Transactional
    public void deleteFamily(Integer familyId) {
        familyMemberRepository.deleteAllByFamily_FamilyId(familyId);//删除此贫困户成员
        workingAndLivingConditionRepository.deleteAllByFamily_FamilyId(familyId);//删除此贫困户生产生活条件
        removalRepository.deleteAllByFamily_FamilyId(familyId);//删除此贫困户易地搬迁记录
        assistanceRepository.deleteAllByFamily_FamilyId(familyId);//删除此贫困户帮扶责任人记录
        drinkingSafetyRepository.deleteAllByFamily_FamilyId(familyId);//删除此贫困户饮水安全记录
        financialAssistanceRepository.deleteAllByFamily_FamilyId(familyId);//删除此贫困户金融扶贫记录
        houseRenovationRepository.deleteAllByFamily_FamilyId(familyId);//删除此贫困户的危房改造记录
        laborTrainingRepository.deleteAllByFamily_FamilyId(familyId);//删除此贫困户劳动力培训记录
        prosperousIndustryRepository.deleteAllByFamily_FamilyId(familyId);//删除此贫困户富民产业记录
        socialAssistanceRepository.deleteAllByFamily_FamilyId(familyId);//删除此贫困户社会援助记录
        specialPovertyAlleviationRepository.deleteAllByFamily_FamilyId(familyId);//删除此贫困户的专项扶贫记录
        assistanceEffectRepository.deleteAllByFamily_FamilyId(familyId);//删除此贫困户的帮扶成效记录
        //删除走访记录
        List<VisitRecord> visitRecords = visitRecordService.getVisitRecordByFamilyId(familyId);
        Iterator<VisitRecord> it = visitRecords.iterator();
        while (it.hasNext()) {
            visitRecordService.deleteVisitRecordById(it.next().getVisitRecordId());
        }
        familyRepository.deleteAllByFamilyId(familyId);//删除此贫困户记录
    }

    //通过多条件模糊查询户主
    public List<Family> searchFamily(Family family) {

        Assert.assertNotNull(family);
        int a;
        List<Family> familyList = familyRepository.findAll(new Specification<Family>() {
            @Override
            public Predicate toPredicate(Root<Family> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                //Id
                if (family.getFamilyId() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("familyId"), family.getFamilyId()));
                }
                //地址
                if (family.getVillage() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("village").get("villageId"), family.getVillage().getVillageId()));
                }
                //贫困户属性
                if (family.getPovertyGrade() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("povertyGrade"), family.getPovertyGrade()));
                }
                /**
                 * 主要致贫原因
                 */
                //因病
                if (family.getOfIll() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("ofIll"), family.getOfIll()));
                }
                //因学
                if (family.getOfSchool() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("ofSchool"), family.getOfSchool()));
                }
                //因灾
                if (family.getOfDisaster() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("ofDisaster"), family.getOfDisaster()));
                }
                //因残
                if (family.getOfDisability() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("ofDisability"), family.getOfDisability()));
                }
                //缺土
                if (family.getLackSoil() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("lackSoil"), family.getLackSoil()));
                }
                //缺水
                if (family.getLackWater() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("lackWater"), family.getLackWater()));
                }
                //缺技术
                if (family.getLackTechnology() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("lackTechnology"), family.getLackTechnology()));
                }
                //缺劳动力
                if (family.getLackLabor() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("lackLabor"), family.getLackLabor()));
                }
                //缺资金
                if (family.getLackMoney() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("lackMoney"), family.getLackMoney()));
                }
                //交通条件落后
                if (family.getLackTraffic() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("lackTraffic"), family.getLackTraffic()));
                }
                //自然发展动力不足
                if (family.getLackNatureDevelopment() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("lackNatureDevelopment"), family.getLackNatureDevelopment()));
                }
                //其他
                if (family.getOthers() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("others"), family.getOthers()));
                }
                //户主姓名
                if (family.getFamilyOwner().getName() != null && !family.getFamilyOwner().getName().equals("")) {
                    predicates.add(criteriaBuilder.like(root.get("familyOwner").get("name"), "%" + family.getFamilyOwner().getName() + "%"));
                }
                //户主身份证号
                if (family.getFamilyOwner().getIDCard() != null && !family.getFamilyOwner().getIDCard().equals("")) {
                    predicates.add(criteriaBuilder.like(root.get("familyOwner").get("IDCard"), "%" + family.getFamilyOwner().getIDCard() + "%"));
                }


/**
 * 户成员姓名生份证号待做
 */
                //民族
                if (family.getFamilyOwner().getEthnicity() != null && !family.getFamilyOwner().getEthnicity().equals("") && !family.getFamilyOwner().getEthnicity().equals(" ")) {
                    predicates.add(criteriaBuilder.like(root.get("familyOwner").get("ethnicity"), "%" + family.getFamilyOwner().getEthnicity() + "%"));
                }
                //健康状况
                if (family.getFamilyOwner().getHealthStatus() != null) {
                    predicates.add(criteriaBuilder.like(root.get("familyOwner").get("healthStatus"), "%" + family.getFamilyOwner().getHealthStatus() + "%"));
                }
//走访时间 待做


                /**
                 * 帮扶政策
                 */
                //产业发展
                if (family.getIndustryDevelopment() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("industryDevelopment"), family.getIndustryDevelopment()));
                }
                //转移就业
                if (family.getChangeJob() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("changeJob"), family.getChangeJob()));
                }
                //助学帮置
                if (family.getHelpSchool() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("helpSchool"), family.getHelpSchool()));
                }
                //社会保障
                if (family.getSocialInsurance() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("socialInsurance"), family.getSocialInsurance()));
                }
                //易地搬迁
                if (family.getRelocation() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("relocation"), family.getRelocation()));
                }
                //特殊救助
                if (family.getSpecialHelp() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("specialHelp"), family.getSpecialHelp()));
                }

                //贫困状态
                if (family.getPovertyState() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("povertyState"), family.getPovertyState()));
                }
                //是否返贫
                if (family.getBackToPoverty() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("isBackToPoverty"), family.getBackToPoverty()));
                }
                //年份
                if (family.getEnterTime() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("enterTime"), family.getEnterTime()));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });

        return familyList;
    }

    //通过村Id获取贫困户
    public List<Family> getFamilyByVillageId(Integer villageId) {
        return familyRepository.findAllByVillage_VillageId(villageId);
    }

    //通过Id获取贫困户
    public Family getFamilyById(Integer familyId) {
        return familyRepository.findByFamilyId(familyId);
    }

}
