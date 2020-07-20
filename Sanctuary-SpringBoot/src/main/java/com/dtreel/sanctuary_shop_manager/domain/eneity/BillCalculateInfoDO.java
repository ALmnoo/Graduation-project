package com.dtreel.sanctuary_shop_manager.domain.eneity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author DtreeL
 */
@Data
@NoArgsConstructor
public class BillCalculateInfoDO {
    private BigDecimal price;
    private BigDecimal profit;
    private BigInteger sales;
    private BigInteger totalBill;
}
