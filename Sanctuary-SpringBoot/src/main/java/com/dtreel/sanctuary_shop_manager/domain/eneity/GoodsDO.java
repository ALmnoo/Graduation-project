package com.dtreel.sanctuary_shop_manager.domain.eneity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author DtreeL
 */
@Data
@NoArgsConstructor
public class GoodsDO {
        private Integer id;
        private String name;
        private SortDO sort;
        private BigDecimal cost;
        private BigDecimal price;
        private Integer inventory;
        private BigDecimal profit;
        private String note;
}
