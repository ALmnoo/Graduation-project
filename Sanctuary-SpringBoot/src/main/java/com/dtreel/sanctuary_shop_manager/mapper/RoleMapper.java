package com.dtreel.sanctuary_shop_manager.mapper;


import com.dtreel.sanctuary_shop_manager.domain.eneity.RoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author DtreeL
 */
public interface RoleMapper {
    //根据用户id查出具有的用户的职位
    List<RoleDO> listRolesById(Integer id);

    //查出所有职位
    List<RoleDO> listAllRole();
}
