package com.util;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class WebCrawlerUtilTest {
    Map<String, Integer> map = new HashMap<>();

    @Before
    public void setUp() {
        map.put("jquery",2);
        map.put("angular",3);
        map.put("react",1);
        map.put("min",4);
    }

    @Test
    public void test_getSortedMapOfJsNames() {
        WebCrawlerUtil util = new WebCrawlerUtil();
        LinkedHashMap<String, Integer> result = util.getSortedMapOfJsNames(map);
        assertEquals(4,result.values().toArray()[0]);
    }

    @Test
    public void getJsName() {
        WebCrawlerUtil util = new WebCrawlerUtil();
        String jsName =util.getJsName("https://xyz/jquery.js");
        assertEquals("jquery.js",jsName);
    }
}