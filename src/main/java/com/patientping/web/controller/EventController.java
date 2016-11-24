package com.patientping.web.controller;

import com.patientping.domain.Event;
import com.patientping.domain.EventRepository;
import com.patientping.domain.Exchange;
import com.patientping.web.service.TicketExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Jacob Eid on 11/23/2016.
 */
@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketExchangeService ticketExchangeService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public Event createEvent(@RequestParam("eventName") String eventName, HttpServletResponse response){
        if(eventName.isEmpty()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return eventRepository.save(new Event(eventName));
    }

    @RequestMapping(value = "/getbetween/{eventIdFrom}/{eventIdTo}",method = RequestMethod.GET)
    @ResponseBody
    public List<Event> getEventsBetween(@PathVariable("eventIdFrom") Integer eventIdFrom, @PathVariable("eventIdTo") Integer eventIdTo, HttpServletResponse response){
        if(eventIdTo<=0 || eventIdFrom<=0 || (eventIdFrom>eventIdTo)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        List<Event> events =  ticketExchangeService.getAllEventsBetween(eventIdFrom,eventIdTo);
        return events;
    }
}
