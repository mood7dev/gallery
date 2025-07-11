package kr.co.wikibook.gallery.order;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.wikibook.gallery.account.etc.AccountConstants;
import kr.co.wikibook.gallery.cart.CartMapper;
import kr.co.wikibook.gallery.common.util.HttpUtils;
import kr.co.wikibook.gallery.item.ItemMapper;
import kr.co.wikibook.gallery.item.model.ItemGetRes;
import kr.co.wikibook.gallery.order.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

        @Slf4j
        @Service
        @RequiredArgsConstructor
        public class OrderService {
            private final OrderMapper orderMapper;
            private final ItemMapper itemMapper;
            private final OrderItemMapper orderItemMapper;
            private final CartMapper cartMapper;

            @Transactional
            public int saveOrder(OrderPostReq p, int logginedMemberId) {
                // 1. 상품 정보 조회
                List<ItemGetRes> itemList = itemMapper.findAllByIdIn(p.getItemIds());

                // 2. 총 금액 계산
                long total=0;
                for (ItemGetRes item : itemList) {
                    total += item.getPrice() - (item.getPrice() * item.getDiscountPer() / 100.0);
                    // 수량 1개로 계산
                }
                log.info("total = {}원",  total);    //총 구매가격 콘솔에 출력

                // 3. OrderPostDto 객체 생성 (빌더 패턴)
                OrderPostDto dto = OrderPostDto.builder()
                        .memberId(logginedMemberId)
                        .name(p.getName())
                        .address(p.getAddress())
                        .payment(p.getPayment())
                        .cardNumber(p.getCardNumber())
                        .amount(total)
                        .build();

                log.info("before save dto = {}", dto);

                // 4. 주문 저장
                orderMapper.save(dto);
                log.info("after save dto = {}", dto);

                // 주문 아이템 DTO 생성
                OrderItemPostDto orderItemPostDto = new OrderItemPostDto(dto.getOrderId(), p.getItemIds());

                //상품리스트 등록
                orderItemMapper.save(orderItemPostDto);

                // 주문 완료 후 장바구니 비우기
                cartMapper.deleteAllCart(logginedMemberId);
                return 1;
            }

            public List<OrderGetRes> findAllByMemberId(int memberId) {
                return orderMapper.findAllByMemberIdOrderByIdDesc(memberId);
            }

            public OrderDetailGetRes detail(OrderDetailGetReq req) {
                OrderDetailGetRes result = orderMapper.findByOrderIdAndMemberId(req);
                List<OrderDetailDto> items = orderItemMapper.findAllByOrderId(req.getOrderId());
                result.setItems(items);
                log.info("result={}", result);
                return result;
            }
        }
