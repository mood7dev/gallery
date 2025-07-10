package kr.co.wikibook.gallery.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.BindParam;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CartDeleteReq {
    private int cartId;
    private int memberId;

    public CartDeleteReq(@BindParam("cart_id") int cartId) {
        this.cartId = cartId;
    }
}
