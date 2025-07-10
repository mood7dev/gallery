package kr.co.wikibook.gallery.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class OrderPostReq {
    private String name;
    private String address;
    private String payment;
    private String cardNumber;

    @JsonProperty("itemIds")
    private List<Integer> itemIds;
}