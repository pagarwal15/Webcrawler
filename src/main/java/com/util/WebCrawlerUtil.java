package com.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WebCrawlerUtil {

    /**
     * Method to fetch top five , javascript library name used by the search term
     *
     * @param map
     * @return
     */
    public LinkedHashMap<String, Integer> getSortedMapOfJsNames(Map<String, Integer> map) {
        return map.entrySet().parallelStream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(5)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> (Integer) e2, LinkedHashMap::new));
    }

    /**
     * Method to fetch the Js name from the absolute URL
     *
     * @param jsUrl
     * @return
     */
    public String getJsName(String jsUrl) {
        return Arrays.stream(jsUrl.split("/")).filter(s -> s.contains(".js")).findAny().orElse(null);
    }
}
