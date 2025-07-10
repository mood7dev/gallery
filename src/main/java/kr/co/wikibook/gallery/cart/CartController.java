package kr.co.wikibook.gallery.cart;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.wikibook.gallery.account.etc.AccountConstants;
import kr.co.wikibook.gallery.cart.model.CartDeleteReq;
import kr.co.wikibook.gallery.cart.model.CartGetRes;
import kr.co.wikibook.gallery.cart.model.CartPostReq;
import kr.co.wikibook.gallery.common.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    @PostMapping
    public ResponseEntity<?> save(HttpServletRequest httpReq, @RequestBody CartPostReq p) {
        Integer loggedInMemberIdObj = (Integer) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        if (loggedInMemberIdObj == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }
        int loggedInMemberId = loggedInMemberIdObj.intValue();
        p.setMemberId(loggedInMemberId);

        // 중복 체크
        int count = cartMapper.countByMemberIdAndItemId(p.getMemberId(), p.getItemId());
        if (count > 0) {
            return ResponseEntity.status(409).body("이미 장바구니에 있는 상품입니다.");
        }

        // 저장
        cartService.save(p);
        return ResponseEntity.ok("장바구니 추가 완료");
    }

    @GetMapping
    public ResponseEntity<?> getCart(HttpServletRequest httpReq) {
        int loggedInMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        List<CartGetRes> result = cartService.findAll(loggedInMemberId);
        return ResponseEntity.ok(result);
    }

    //부분 삭제
    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> delete(HttpServletRequest httpReq, @PathVariable int cartId) {
        int loggedInMemberId = (int)HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
       CartDeleteReq p = new CartDeleteReq(cartId, loggedInMemberId);
        int result = cartService.remove(p);
        return ResponseEntity.ok(result);
    }

    //전체 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteAll(HttpServletRequest httpReq, @ModelAttribute CartDeleteReq p) {
        int loggedInMemberId = (int)HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        int result = cartService.removeAll(loggedInMemberId);
        return ResponseEntity.ok(result);
    }
}
