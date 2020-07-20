package com.dtreel.sanctuary_shop_manager.domain.eneity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author DtreeL
 */
@Data
@NoArgsConstructor
public class SubMenuDO {
    private Integer id;
    private String url;
    private String path;
    private String component;
    private String name;
    private String icon;
    private Boolean keepAlive;
    private Boolean requireAuth;
    private Integer parentId;
    private Integer enable;
    private List<RoleDO> roles;
}
