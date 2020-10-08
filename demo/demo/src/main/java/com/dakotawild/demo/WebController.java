package com.dakotawild.demo;

import java.io.IOException;
import java.math.RoundingMode;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// import jdk.internal.net.http.common.Utils;
import org.apache.commons.io.FilenameUtils; 

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.*;
import com.google.common.base.Objects;

import org.joda.money.*;

import org.apache.commons.math3.random.MersenneTwister;

@RequestMapping
@RestController
public class WebController {

    @GetMapping("/jsoup")
    public String jsoup() throws IOException{
        String url = "https://www.cpp.edu/index.shtml";
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        
        return "On " + url + " there are " + links.size() + " different hyperlinks.";
        
    }
    
    @GetMapping("/apache")
    public String apache(){
        //File file = FileUtils.getFile(getClass().getClassLoader().getResrouce("pom.xml").getPath()); 
        return ""; 
    }
    
	@GetMapping("/guava")
    public String guava() {
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(4);
        numbers.add(3);
        numbers.add(2);
        numbers.add(1);

        Ordering ordering = Ordering.natural();
        List<Integer> sortedNumbers = numbers;

        String result = "Original list of numbers: ";
        for(int i = 0; i < numbers.size(); ++i) {
            result += numbers.get(i) + " ";
        }

        Collections.sort(numbers, ordering);
        result += "| Sorted list: ";
        for(int i = 0; i < numbers.size(); ++i) {
            result += numbers.get(i) + " ";
        }

        return result;
    }

    @GetMapping("/jodaMoney")
    public String jodaMoney() {
        Money money = Money.parse("USD 23.87");
        Money money2 = Money.parse("USD 224.27");
        Money money3 = Money.parse("USD 1213.87");
  
  
        CurrencyUnit usd = CurrencyUnit.of("USD");
        money2 = money.minusMajor(2);
        money3 = money.multipliedBy(3.5d, RoundingMode.DOWN);

        String result = money.toString();

        return result;
    }
	
    @GetMapping("/math")
    public String randomScore() {
	MersenneTwister rng = new MersenneTwister();
	int num = rng.nextInt() % 100;
	String result = "Your random number from (-100 to 100) is: " + num;
	return result;
    }
}
