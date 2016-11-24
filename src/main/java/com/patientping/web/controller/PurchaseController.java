package com.patientping.web.controller;

import com.patientping.domain.*;
import com.patientping.web.service.TicketExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

/**
 * Created by Jacob Eid on 11/23/2016.
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    TicketExchangeService ticketExchangeService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public Purchase createPurchase(@RequestParam(value = "userName") String userName, @RequestParam(value = "numTickets") int numTickets, @RequestParam(value="eventId") Integer eventId,
                                   HttpServletResponse response){
        Purchase purchase = ticketExchangeService.createPurchase(userName,eventId,numTickets);
        if(purchase==null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return purchase;
    }

}
