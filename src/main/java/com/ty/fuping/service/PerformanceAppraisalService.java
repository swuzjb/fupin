package com.ty.fuping.service;


import com.ty.fuping.entity.*;
import com.ty.fuping.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2017/11/2.
 * 绩效考核service
 */
@Service
public class PerformanceAppraisalService {

    @Autowired
    private AssessmentPlanRepository assessmentPlanRepository;
    @Autowired
    private AssessmentandObjectRepository assessmentandObjectRepository;
    @Autowired
    private AssessmentandAssessmenterRepository assessmentandAssessmenterRepository;
    @Autowired
    private AssessmentIndexRepository assessmentIndexRepository;


}
