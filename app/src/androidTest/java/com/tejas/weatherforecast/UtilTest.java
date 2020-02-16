package com.tejas.weatherforecast;

import android.support.test.runner.AndroidJUnit4;

import com.tejas.weatherforecast.utils.AppUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class UtilTest {
    @Test
    public void util_Internet_Test() {
        assertEquals(AppUtils.isInternetAvailable(), true);
    }
}
