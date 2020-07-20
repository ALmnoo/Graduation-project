package com.dtreel.sanctuary_shop_manager.mapper;

import com.dtreel.sanctuary_shop_manager.domain.eneity.RoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author DtreeL
 */
public interface UserAndRoleMapper {
    //添加职位
    int insertUserRole(@Param("userId") int userId, @Param("roles") List<RoleDO> roles);

    //删除职位
    int deleteRoleByUserIdAndRoleId(@Param("userId") int id, @Param("deleteRoleIds") Integer[] deleteRoleIds);

    //增加职位
    int insertUserRoleByUserIdAndRoleId(@Param("userId") int id, @Param("addRoleIds") Integer[] addRoleIds);

    //删除用户所有职位
    int deleteRoleByUserId(Integer id);
}
