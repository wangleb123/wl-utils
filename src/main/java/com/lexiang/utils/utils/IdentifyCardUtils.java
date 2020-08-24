package com.lexiang.utils.utils;

import org.apache.commons.lang3.StringUtils;

public class IdentifyCardUtils {

    public static String getSex(String idno) {
        if (StringUtils.isBlank(idno)) return "1";
        return idno.charAt(16) % 2 == 0 ? "0" : "1";
    }

    public static String getBirthdayByIdCard(String idCard) {
        String idCardNumber = idCard.trim();
        int idCardLength = idCardNumber.length();
        String birthday = null;
        if (idCardNumber == null || "".equals(idCardNumber)) {
            return null;
        }
        if (idCardLength == 18) {
            birthday = idCardNumber.substring(6, 14);
        }
        if (idCardLength == 15) {
            birthday = "19" + idCardNumber.substring(6, 12);
        }
        return birthday;
    }


}
