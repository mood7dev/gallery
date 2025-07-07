package kr.co.wikibook.gallery.account.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class AccountLoginReq {
    private String loginId;
    private String loginPw;
}
