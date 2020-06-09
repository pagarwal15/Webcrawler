package com.core;

import com.util.WebCrawlerUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class responsible for core functionality logic of the web crawler such as
 * --extracts and download all the web pages found for the search term.
 * --Fetch the Js links
 * --Store Js name in map
 */
public class WebCrawlerCore {

    /**
     * Constants to hold the user agent for calling the google search API
     */
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

    /**
     * Constants to hold the google search API
     */
    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";


    /**
     * This method is used to print 5 top Javascript library used by
     * --Searching the search term
     * --Downloading the pages matching the search term & fetching the links
     * --Downloading the web pages of the each links
     * --Fetch the JS links
     *
     * @param searchTerm
     * @param num        - number of result to be searched for the search term
     * @throws IOException
     * @return
     */
    public LinkedHashMap<String, Integer> fetchJSUsedInSitesBySearchText(String searchTerm, int num) throws IOException {
        //Fetch the web page for the search term
        String searchURL = GOOGLE_SEARCH_URL + "?q=" + searchTerm + "&num=" + num;
        Document htmlDocument = Jsoup.connect(searchURL).userAgent(USER_AGENT).get();
        Elements results = htmlDocument.select("div.r");


        //For each result of the search term page, download the page and fetch the Javascript used
        Map<String, Integer> map = new HashMap<String, Integer>();
        results.parallelStream().forEach(result -> setJsUsedInSearchedSites(result, map));

        return new WebCrawlerUtil().getSortedMapOfJsNames(map);

    }

    /**
     * Method to fetch the each webpage for the result of search term and store it in map
     *
     * @param result
     * @param map
     */
    public void setJsUsedInSearchedSites(Element result, Map<String, Integer> map) {
        try {
            Element links = result.select("a[href]").first();
            String pageUrl = links.absUrl("href");
            Document pageDocument = Jsoup.connect(pageUrl).userAgent(USER_AGENT).get();
            //fetch and store the JS file name in map
            storeJsTypesInMap(map, pageDocument.select("script[src$=.js]"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to store the javascript names in Map object and increase the count in case of duplicate javascript library
     *
     * @param map
     * @param javaScriptElements
     */
    public void storeJsTypesInMap(Map<String, Integer> map, Elements javaScriptElements) {

        //Iterate over each javascript element and fetch  & store the javascript in map
        for (Element element : javaScriptElements) {
            String jsName = new WebCrawlerUtil().getJsName(element.attr("src"));
            if (map.containsKey(jsName)) {
                map.put(jsName, map.get(jsName) + 1);
            } else {
                map.put(jsName, 1);
            }
        }
    }
}
