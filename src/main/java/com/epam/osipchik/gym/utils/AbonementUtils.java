package com.epam.osipchik.gym.utils;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;

//todo Create a SingleTon

public class AbonementUtils {



    public Date getSqlDate() {
        return new Date(new java.util.Date().getTime());
    }

    public Date calculateFinishDate(java.util.Date startDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.MONTH, 1);
        return new Date((cal.getTime()).getTime());
//        String dateFormat = "YYMonDD";
//        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
//        sdf.format(new Date());
    }

    public BigDecimal calculateTotalPrice(int price, int count) {
        if (count > 9) {
            return BigDecimal.valueOf(price - (price * 0.1));
        } else if (count > 4) {
            return BigDecimal.valueOf(price - (price * 0.05));
        } else {
            return BigDecimal.valueOf(price);
        }
    }
}
