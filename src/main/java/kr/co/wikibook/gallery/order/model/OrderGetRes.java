package kr.co.wikibook.gallery.order.model;

import kr.co.wikibook.gallery.item.model.ItemGetRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderGetRes {
    private int id;
    private String name;
    private String payment;
    private long amount;
    private String created;
    private List<ItemGetRes> items;
}
