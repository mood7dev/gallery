package kr.co.wikibook.gallery.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AccountJoinReq {
    //회원가입 때 받아야 하는 데이터
    private String name;
    private String loginId;
    private String loginPw;

}
