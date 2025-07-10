package kr.co.wikibook.gallery.cart;

import kr.co.wikibook.gallery.cart.model.CartDeleteReq;
import kr.co.wikibook.gallery.cart.model.CartGetRes;
import kr.co.wikibook.gallery.cart.model.CartPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartMapper cartMapper;

    //전부 (특정 회원의 특정 상품)

    //장바구니 데이터 저장
    public int save(CartPostReq p) {
        return cartMapper.save(p);
    }

    //장바구니 상품 데이터 삭제
    public int remove(CartDeleteReq p){
        return cartMapper.deleteByCartIdAndMemberId(p);
    }

    // 장바구니 전체 삭제
    public int removeAll(int memberId) {
        return cartMapper.deleteAllCart(memberId);
    }

    //장바구니 상품 데이터 목록 조회
    public List<CartGetRes> findAll(int memberId){
        return cartMapper.findAllWithItemByMemberId(memberId);
    }

    //장바구니 상품 데이터 조회



}

