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
import net.fortuna.ical4j.model.Date; 

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
	    calendar.set(java.util.Calendar.Month, java.util.Calendar.JANUARY); 
	    calendar.set(java.util.Calendar.DAY_OF_MONTH, 1); 
	
	    VEvent newYears = new VEvent(new Date(calendar.getTime()), "New Years"); 
	    UidGenerator ug = new UidGeneartor("1"); 
	    newYears.getProperties().add(ug.generatedUid()); 
	    
	    Calendar cal = new Calendar(); 
	    cal.getComponents.add(newYears); 
	    
	    return cal; 
    }
}
