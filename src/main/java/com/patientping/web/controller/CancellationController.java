package com.patientping.web.controller;

import com.patientping.domain.Cancellation;
import com.patientping.domain.Exchange;
import com.patientping.web.service.TicketExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jacob Eid on 11/23/2016.
 */
@RestController
@RequestMapping("/cancellation")
public class CancellationController {

    @Autowired
    private TicketExchangeService ticketExchangeService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public Cancellation createCancellation(@RequestParam(value = "userName") String userName, @RequestParam(value = "numTickets") Integer numTickets, @RequestParam(value="eventId") Integer eventId, HttpServletResponse response){
        Cancellation cancellation = ticketExchangeService.createCancellation(userName,eventId,numTickets);
        if(cancellation==null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return cancellation;
    }
}
