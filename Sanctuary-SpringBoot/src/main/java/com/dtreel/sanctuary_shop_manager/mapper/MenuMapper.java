package com.dtreel.sanctuary_shop_manager.mapper;



import com.dtreel.sanctuary_shop_manager.domain.eneity.MainMenuDO;
import com.dtreel.sanctuary_shop_manager.domain.eneity.MenuDO;
import com.dtreel.sanctuary_shop_manager.domain.eneity.SubMenuDO;

import java.util.List;

/**
 * @author DtreeL
 */
public interface MenuMapper {

    List<SubMenuDO> listSubMenuWithRole();

    List<MainMenuDO> listMainMenuByUserId(Integer id);
}
