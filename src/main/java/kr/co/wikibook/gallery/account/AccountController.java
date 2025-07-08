package kr.co.wikibook.gallery.account;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.wikibook.gallery.account.etc.AccountConstants;
import kr.co.wikibook.gallery.account.model.AccountJoinReq;
import kr.co.wikibook.gallery.account.model.AccountLoginReq;
import kr.co.wikibook.gallery.account.model.AccountLoginRes;
import kr.co.wikibook.gallery.common.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import static kr.co.wikibook.gallery.common.util.HttpUtils.removeSessionValue;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody AccountJoinReq p) {
        // 유효성 검사: name, loginId, loginPw 모두 값이 있어야 함
        if (!StringUtils.hasLength(p.getName())
                || !StringUtils.hasLength(p.getLoginId())
                || !StringUtils.hasLength(p.getLoginPw())) {
            return ResponseEntity.badRequest().body("입력값 누락");
        }

        int result = accountService.join(p);
        return ResponseEntity.ok("회원가입 성공: " + result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest httpReq, @RequestBody AccountLoginReq p) {
        AccountLoginRes result = accountService.login(p);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        //세션처리 : 로그인 중에 로그인 정보 임시 저장소
        HttpUtils.setSession(httpReq,AccountConstants .MEMBER_ID_NAME, result.getId());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/check")
    public ResponseEntity<?> check(HttpServletRequest httpReq) {
        Integer id = (Integer)HttpUtils.getSessionValue(httpReq, AccountConstants .MEMBER_ID_NAME);
        log.info("id: {}", id);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest httpReq) {
        HttpUtils.removeSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        return ResponseEntity.ok(1);
    }
}
