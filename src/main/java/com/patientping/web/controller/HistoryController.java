package com.patientping.web.controller;

import com.patientping.domain.Event;
import com.patientping.web.service.TicketExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Jacob Eid on 11/23/2016.
 */
@RestController
@RequestMapping("/history")
public class HistoryController {


    @Autowired
    private TicketExchangeService ticketExchangeService;

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,List<? extends Object>> getHistoryForUser(@PathVariable Integer userId,HttpServletResponse httpServletResponse){
        if(userId<=0){
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        Map<String,List<? extends Object>> map = ticketExchangeService.getOrderHistory(userId);
        return map;
    }

}
