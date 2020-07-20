package com.dtreel.sanctuary_shop_manager.domain.eneity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author DtreeL
 */
@Data
@NoArgsConstructor
public class MainMenuDO {
    private Integer id;
    private String url;
    private String path;
    private String component;
    private String name;
    private String icon;
    private Boolean keepAlive;
    private Boolean requireAuth;
    private Boolean enable;
    private List<SubMenuDO> children;
}
