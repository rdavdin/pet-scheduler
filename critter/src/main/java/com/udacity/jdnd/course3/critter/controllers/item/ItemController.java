package com.udacity.jdnd.course3.critter.controllers.item;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.entities.Bid;
import com.udacity.jdnd.course3.critter.entities.Item;
import com.udacity.jdnd.course3.critter.repositories.BidRepository;
import com.udacity.jdnd.course3.critter.repositories.ItemRepository;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    BidRepository bidRepository;

    @PostMapping
    public Item saveItem(@RequestBody Item i){
        return itemRepository.save(i);
    }

    @PostMapping("/bid")
    public Bid saveBid(@RequestBody Bid b){
        Item i = itemRepository.getReferenceById((long) 1);
        Bid bid = new Bid(b.getAmount(), i);
        System.out.println("bid.amount = "+bid.getAmount());
        bid.setItem(i);
        i.addBid(bid);
        itemRepository.save(i);
        return bid;
    }


}
