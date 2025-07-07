package kr.co.wikibook.gallery.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class AccountLogRes {
    private int id;
    @JsonIgnore
    private String loginPw;
}
