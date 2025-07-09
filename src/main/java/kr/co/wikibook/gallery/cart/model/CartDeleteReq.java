package kr.co.wikibook.gallery.cart.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartDeleteReq {
    private int memberId;
    private int itemId;
}
