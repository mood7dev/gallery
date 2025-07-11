package kr.co.wikibook.gallery.order;

import kr.co.wikibook.gallery.order.model.OrderDetailDto;
import kr.co.wikibook.gallery.order.model.OrderItemPostDto;
import kr.co.wikibook.gallery.item.model.ItemGetRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    // 주문상품 등록
    int save(OrderItemPostDto dto);
    // 주문번호로 주문상품 목록 조회
    List<OrderDetailDto> findAllByOrderId(int orderId);
}
