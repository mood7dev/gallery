package kr.co.wikibook.gallery.cart;

import kr.co.wikibook.gallery.cart.model.CartDeleteReq;
import kr.co.wikibook.gallery.cart.model.CartGetRes;
import kr.co.wikibook.gallery.cart.model.CartPostReq;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CartMapper {
    // 회원과 아이템 아이디로 중복 체크
    int countByMemberIdAndItemId(int memberId, int itemId);

    // 특정 회원의 장바구니 아이템 목록 조회
    List<CartGetRes> findAllWithItemByMemberId(int memberId);

    // 장바구니에 저장
    int save(CartPostReq p);

    // 장바구니 삭제
    int deleteByMemberIdAndItemId(CartDeleteReq p);
}