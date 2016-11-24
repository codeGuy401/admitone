package com.patientping.web.service;

import com.patientping.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jacob Eid on 11/23/2016.
 */
@Service
public class TicketExchangeService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private CancellationRepository cancellationRepository;

    private enum orderTypes{
        cancellation,purchase,exchange
    }

    @Transactional
    public Purchase createPurchase(String userName,Integer eventId,Integer numTickets){


        User user = userRepository.findByUserName(userName);
        Event event = eventRepository.findOne(eventId);

        if(user==null || event ==null || numTickets<=0){
            return null;
        }

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setEvent(event);
        purchase.setNumberOfTickets(numTickets);

        purchase = purchaseRepository.save(purchase);

        UserOrder userOrder = userOrderRepository.findByEventIdAndUserId(eventId,user.getId());
        if(userOrder==null){
            userOrder=new UserOrder(numTickets,user,event);
        }else{
            userOrder.setNumberOfTickets(userOrder.getNumberOfTickets()+numTickets);
        }

        userOrderRepository.save(userOrder);
        return purchase;
    }

    @Transactional
    public Exchange createExchange(String userName, Integer eventIdFrom, Integer eventIdTo, Integer numTickets){
        User user = userRepository.findByUserName(userName);
        Event eventFrom = eventRepository.findOne(eventIdFrom);
        Event eventTo = eventRepository.findOne(eventIdTo);
        UserOrder userOrderFrom = userOrderRepository.findByEventIdAndUserId(eventIdFrom,user.getId());

        if(user==null || eventFrom==null || eventTo == null || userOrderFrom==null || numTickets<=0 || userOrderFrom.getNumberOfTickets()<numTickets){
            return null;
        }
        UserOrder userOrderTo = userOrderRepository.findByEventIdAndUserId(eventIdTo,user.getId());
        if(userOrderTo==null){
            userOrderTo = new UserOrder(numTickets,user,eventTo);
        }else{
            userOrderTo.setNumberOfTickets(userOrderTo.getNumberOfTickets()+numTickets);
        }
        userOrderFrom.setNumberOfTickets(userOrderFrom.getNumberOfTickets()-numTickets);
        Exchange exchange = new Exchange();
        exchange.setUser(user);
        exchange.setEventFrom(eventFrom);
        exchange.setEventTo(eventTo);
        exchange.setNumberOfTickets(numTickets);
        userOrderRepository.save(userOrderTo);
        userOrderRepository.save(userOrderFrom);
        exchange = exchangeRepository.save(exchange);
        return exchange;
    }

    @Transactional
    public Cancellation createCancellation(String userName,Integer eventId,Integer numTickets){
        User user = userRepository.findByUserName(userName);
        Event event = eventRepository.findOne(eventId);
        UserOrder userOrder = userOrderRepository.findByEventIdAndUserId(eventId,user.getId());

        if(user==null || event ==null || numTickets<=0 || userOrder ==null || userOrder.getNumberOfTickets()<numTickets){
            return null;
        }

        Cancellation cancellation = new Cancellation();
        cancellation.setUser(user);
        cancellation.setEvent(event);
        cancellation.setNumberOfTickets(numTickets);

        cancellation = cancellationRepository.save(cancellation);

        userOrder.setNumberOfTickets(userOrder.getNumberOfTickets()-numTickets);
        userOrderRepository.save(userOrder);
        return cancellation;
    }

    public List<Event> getAllEventsBetween(Integer eventIdStart,Integer eventIdEnd){
        return eventRepository.findByIdBetween(eventIdStart,eventIdEnd);
    }

    public Map<String,List<? extends Object>> getOrderHistory(Integer userId){
        List<Purchase> purchases = purchaseRepository.findByUserId(userId);
        List<Cancellation> cancellations = cancellationRepository.findByUserId(userId);
        List<Exchange> exchanges = exchangeRepository.findByUserId(userId);
        Map<String,List<? extends Object>> map = new HashMap<>();
        map.put(orderTypes.purchase.name(),purchases);
        map.put(orderTypes.cancellation.name(),cancellations);
        map.put(orderTypes.exchange.name(),exchanges);
        return map;
    }
}
