package com.jtj.jwtm.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/31.
 */
@Slf4j
public class SecureRandomUtilsTest {

    @Test
    public void getStringNumber() {
        IntStream.range(1,10).forEach(index -> {
            String numberCode = SecureRandomUtils.getStringNumber(index);
            assertEquals(numberCode.length(),index);
        });
    }
}