package com.dtreel.sanctuary_shop_manager.mapper;

import com.dtreel.sanctuary_shop_manager.domain.eneity.GoodsDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author DtreeL
 */
public interface GoodsMapper {
    //根据关键字、当前页、页规格得到Goods数据
    List<GoodsDO> getGoodsPageData(
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("keyword") String keyword);

    //根据关键字得到总记录数
    Long getGoodsTotalByKeyword(String keyword);

    //添加参数goods的信息到数据库中
    int insertGoods(GoodsDO goods);

    //根据参数goods信息修改在数据库中修改对应的记录
    int updateGoods(GoodsDO goods);

    int deleteGoodsById(Integer id);

    int deleteGoodsByIds(@Param("ids") Integer[] ids);

    GoodsDO getGoodsById(Integer goodsId);

    int updateGoodsInventory(GoodsDO goods);
}
