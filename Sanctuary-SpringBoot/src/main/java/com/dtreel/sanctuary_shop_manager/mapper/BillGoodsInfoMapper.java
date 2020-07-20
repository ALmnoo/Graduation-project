package com.dtreel.sanctuary_shop_manager.mapper;


import com.dtreel.sanctuary_shop_manager.domain.eneity.BillGoodsInfoDO;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author DtreeL
 */
public interface BillGoodsInfoMapper {
    BillGoodsInfoDO getGoodsInfo(@Param("goodsId") Integer goodsId, @Param("goodsNumber") Integer goodsNumber);

    int insertBillInfo(@Param("billId") BigInteger id, @Param("billGoodsInfos") List<BillGoodsInfoDO> billGoodsInfos);
}
