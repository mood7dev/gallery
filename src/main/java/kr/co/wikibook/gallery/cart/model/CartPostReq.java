package kr.co.wikibook.gallery.cart.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartPostReq {
    private int id;
    private int memberId;
    private int itemId;
}