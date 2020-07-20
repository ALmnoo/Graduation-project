package com.dtreel.sanctuary_shop_manager.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author DtreeL
 */
@Data
@NoArgsConstructor
public class ResponsePageData {
    private Long total;
    private List<?> data;
}
