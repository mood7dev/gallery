package kr.co.wikibook.gallery.account;

import kr.co.wikibook.gallery.account.model.AccountJoinReq;
import kr.co.wikibook.gallery.account.model.AccountLoginReq;
import kr.co.wikibook.gallery.account.model.AccountLoginRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    // 로그인 ID와 비밀번호로 계정 조회
    AccountLoginRes findByLoginId(AccountLoginReq p);

    // 회원가입 요청을 DB에 저장
    int save(AccountJoinReq p);
}
