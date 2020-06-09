package com.task;

import com.core.WebCrawlerCore;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * Class responsible for web crawler functionality. It takes a search term and number of result for the search term.
 * This class provide 5 top Javascript library used in search term pages
 */
public class JSWebCrawlerMain {

    public static void main(String[] args) throws IOException {
        //Taking search term input from console
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the search term.");
        String searchTerm = scanner.nextLine();

        //Takes the number of times the search term need to be searched
        System.out.println("Please enter the number of results to be searched");
        int num = scanner.nextInt();
        scanner.close();

        //Call the core to fetch the top 5 most javascript library used
        WebCrawlerCore core = new WebCrawlerCore();
        Map<String, Integer> finalResult = core.fetchJSUsedInSitesBySearchText(searchTerm, num);

        System.out.println("Top 5 most used Javascript library:");
        finalResult.forEach((key,value) -> System.out.println(key));
    }
}