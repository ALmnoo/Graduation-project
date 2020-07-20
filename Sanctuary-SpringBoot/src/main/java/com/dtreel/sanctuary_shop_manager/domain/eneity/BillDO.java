package com.dtreel.sanctuary_shop_manager.domain.eneity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * @author DtreeL
 */
@Data
@NoArgsConstructor
public class BillDO {
    private BigInteger id;
    private Integer totalSales;
    private BigDecimal totalPrice;
    private BigDecimal totalProfit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date createTime;
    private UserDO user;
    private String note;
    private Boolean work;
    private List<BillGoodsInfoDO> billGoodsInfos;

}
