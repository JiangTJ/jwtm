package com.jtj.jwtm.common;

import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/31.
 */
@Slf4j
public class SecureRandomUtils {

    private static SecureRandom random;

    public static SecureRandom getRandom() {
        if (random == null) {
            try {
                random = SecureRandom.getInstanceStrong();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return random;
    }


    /**
     * if num = 5000
     * return 0000 ~ 5000
     */
    public static int getNumber(int bound){
        return getRandom().nextInt(bound);
    }

    /**
     * id num = 4
     * return 0000 ~ 9999
     */
    public static String getStringNumber(int num){
        if (num < 0 || num > 9) {
            throw new RuntimeException("请输入1-9位数");
        }
        int bound = (int) (Math.pow(10, num) - 1);
        int randomNum = getNumber(bound);
        return String.format("%0"+num+"d", randomNum);
    }

}
