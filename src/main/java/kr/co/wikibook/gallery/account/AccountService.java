package kr.co.wikibook.gallery.account;

import kr.co.wikibook.gallery.account.model.AccountJoinReq;

import kr.co.wikibook.gallery.account.model.AccountLoginReq;
import kr.co.wikibook.gallery.account.model.AccountLoginRes;
import kr.co.wikibook.gallery.common.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountMapper accountMapper;

    public int join (AccountJoinReq p) {
        String hashedPw = BCrypt.hashpw(p.getLoginPw(), BCrypt.gensalt());
        AccountJoinReq changeReq = new AccountJoinReq(p.getName(), p.getLoginId(), hashedPw);
        return accountMapper.save(changeReq);
    }

    public AccountLoginRes login(AccountLoginReq p){
        AccountLoginRes res = accountMapper.findByLoginId(p);
        //아이디가 없거나 비밀번호가 다르다면
        if(res == null || !BCrypt.checkpw(p.getLoginPw(), res.getLoginPw())) {
                return null; // return null; 처리
        }
     return res; // 로그인 성공
    }
}