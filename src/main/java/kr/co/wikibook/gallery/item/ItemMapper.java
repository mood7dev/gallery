package kr.co.wikibook.gallery.item;

import kr.co.wikibook.gallery.item.model.ItemGetRes;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ItemMapper {

    // 여러 아이디로 상품을 조회
    List<ItemGetRes> findAllByIdIn(List<Integer> ids);
}