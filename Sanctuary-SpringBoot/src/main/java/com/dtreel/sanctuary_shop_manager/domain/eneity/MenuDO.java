package com.dtreel.sanctuary_shop_manager.domain.eneity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author DtreeL
 */
@Data
@NoArgsConstructor
public class MenuDO {
    private Integer id;
    private String name;
    private List<MainMenuDO> children;
}
