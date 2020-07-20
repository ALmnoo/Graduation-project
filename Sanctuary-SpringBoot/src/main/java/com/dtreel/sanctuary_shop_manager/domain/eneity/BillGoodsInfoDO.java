package com.dtreel.sanctuary_shop_manager.domain.eneity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Description TODO
 * @Author DtreeL
 * @Date 2020/5/2
 **/
@Data
@NoArgsConstructor
public class BillGoodsInfoDO {
    private BigInteger billId;
    private GoodsDO goods;
    private Integer quantitySold;
    private BigDecimal itemSumPrice;
    private BigDecimal itemSumProfit;
}
