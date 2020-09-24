package com.dakotawild.demo;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.fortuna.ical4j.model.Calendar; 
import net.fortuna.ical4j.model.component.VEvent; 
import net.fortuna.ical4j.model.PropertyList; 
import net.fortuna.ical4j.model.property.Uid; 

import org.joda.money;

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
    
    @GetMapping("/ical4j") 
    public Calendar myCalendar(){
	    java.util.Calendar calendar = java.util.Calendar.getInstance(); 
	    calendar.set(java.util.Calendar.MONTH, java.util.Calendar.JANUARY); 
	    calendar.set(java.util.Calendar.DAY_OF_MONTH, 1); 
	
	    VEvent newYears = new VEvent(calendar.getTime(), "New Years"); 
	    newYears.getProperties().add(Utils.generatedUid()); 
	    
	    Calendar cal = new Calendar(); 
	    cal.getComponents.add(newYears); 
	    
	    return cal; 
    }
	
	
    @GetMapping("/guava")
    public String guava() {
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(28);
        numbers.add(488);
        numbers.add(32);
        numbers.add(1);
        numbers.add(3849);

        Ordering ordering = Ordering.natural();
        List<Integer> sortedNumbers = numbers;

        String result = "Original list of numbers: ";
        for(int i = 0; i < numbers.size(); ++i) {
            result += numbers.get(i) + " ";
        }

        Collections.sort(numbers, ordering);
        result += "\nSorted list: ";
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
        money = money.plus(Money.of(usd, 12.43d));
        money2 = money.minusMajor(2);
        money3 = money.multipliedBy(3.5d, RoundingMode.DOWN);

        String result = money.toString();

        return result;
    }
}
