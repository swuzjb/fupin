package com.ty.fuping.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ty on 2017/7/30.
 */
public class GetImageNameUtil {

    public static String getImageNameByTime(String imageName){
        Calendar now = Calendar.getInstance();
        String[] splits=imageName.split("\\.");
        String suffix=splits[splits.length-1];
        return new Date().getTime()+"."+suffix;
    }
}
