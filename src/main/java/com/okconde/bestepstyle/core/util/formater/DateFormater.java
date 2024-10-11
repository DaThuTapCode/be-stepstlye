package com.okconde.bestepstyle.core.util.formater;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Trong Phu on 08/10/2024 20:23
 * Util chuyển đổi dữ liệu thời gian
 * @author Trong Phu
 */

public class DateFormater {

    //Chuyển Date 00:00:00 dd-MM-yyyy -> 23:59:59 dd-MM-yyyy
    public static Date setEndDate(Date endDate){
        if(endDate != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endDate);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            endDate = calendar.getTime();
            return endDate;
        }
        return null;
    }
}
