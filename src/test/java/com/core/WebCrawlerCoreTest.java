package com.core;


import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.LinkedHashMap;

public class WebCrawlerCoreTest {

    WebCrawlerCore core;

    @Test
    public void test_fetchJSUsedInSitesBySearchText() throws IOException {
        core = new WebCrawlerCore();

        LinkedHashMap<String, Integer> result =  core.fetchJSUsedInSitesBySearchText("kafka",20);
        Assert.assertNotNull(result);
        Assert.assertEquals(5,result.size());
    }

    @Test
    public void test_fetchJSUsedInSitesBySearchText_for_zero_search_result() throws IOException {
        core = new WebCrawlerCore();

        LinkedHashMap<String, Integer> result =  core.fetchJSUsedInSitesBySearchText("kafka",0);
        Assert.assertNotNull(result);
        Assert.assertEquals(0,result.size());
    }

    @Test
    public void test_fetchJSUsedInSitesBySearchText_for_no_search_term_passed_exception() throws IOException {
        core = new WebCrawlerCore();

        LinkedHashMap<String, Integer> result =  core.fetchJSUsedInSitesBySearchText("",20);
        Assert.assertNotNull(result);
        Assert.assertEquals(0,result.size());
    }
}