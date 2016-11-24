package com.patientping.web.controller;

import com.patientping.domain.Exchange;
import com.patientping.domain.Purchase;
import com.patientping.web.service.TicketExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jacob Eid on 11/23/2016.
 */
@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private TicketExchangeService ticketExchangeService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public Exchange createExchange(@RequestParam(value = "userName") String userName, @RequestParam(value = "numTickets") int numTickets, @RequestParam(value="eventIdFrom") Integer eventIdFrom,
                                   @RequestParam(value="eventIdTo") Integer eventIdTo, HttpServletResponse response){
        Exchange exchange = ticketExchangeService.createExchange(userName,eventIdFrom,eventIdTo,numTickets);
        if(exchange==null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return exchange;
    }
}
