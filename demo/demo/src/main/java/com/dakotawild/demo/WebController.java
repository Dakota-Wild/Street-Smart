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
import net.fortuna.ical4j.model.property.PropId; 
import net.fortuna.ical4j.model.property.Version; 
import net.fortuna.ical4j.model.property.CalScale; 
import net.fortuna.ical4j.model.property.Location; 
import net.fortuna.ical4j.model.property.Duration; 
import net.fortuna.ical4j.model.TimeZone; 
import net.fortuna.ical4j.model.DateTime; 
import java.util.GregorianCalendar; 

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
	    TimeZone tz = Utils.getTimezone("America/New_York"); 
	    java.util.Calendar startDate = new GregorianCalendar(); 
	    startDate.setTimeZone(tz);
	    startDate.add(java.util.Calendar.DAY_OF_MONTH, 9); 
	    startDate.set(java.util.Calendar.MINUTE, 0); 
	    startDate.set(java.util.Calendar.SECOND, 0); 
	    
	    String eventName = "CS4800"; 
	    DateTime start = new DateTime(startDate.getTime()); 
	    start.setTimeZone(tz); 
	    
	    Calendar calendar = new Calendar(); 
	    calendar.getProperties().add(new ProdId("-//Celine Tran//iCal4j 1.0//EN")); 
	    calendar.getProperties().add(Version.VERSION_2_0); 
	    calendar.getProperties().add(CalScale.GREGORIAN); 
	    VEvent vEvent = new VEvent(start, eventName); 
	    vEvent.getProperties().add(new Uid("CS4800"));
	    vEvent.getProperties().add(new Duration(null, "1H15M")); 
	    vEvent.getProperties().add(new Location("Zoom")); 
	    calendar.getComponents().add(vEvent); 

	    return calendar; 
    }
}
