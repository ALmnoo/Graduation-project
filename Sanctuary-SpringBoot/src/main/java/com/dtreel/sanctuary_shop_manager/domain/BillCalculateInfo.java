package com.dtreel.sanctuary_shop_manager.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author DtreeL
 */
@Data
@NoArgsConstructor
public class BillCalculateInfo {
    private BigDecimal price;
    private BigDecimal profit;
    private BigInteger sales;
    private BigInteger totalBill;
}
