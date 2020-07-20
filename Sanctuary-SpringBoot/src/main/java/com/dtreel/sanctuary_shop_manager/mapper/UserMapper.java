package com.dtreel.sanctuary_shop_manager.mapper;



import com.dtreel.sanctuary_shop_manager.domain.eneity.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author DtreeL
 */
@Mapper
public interface UserMapper {
    //根据用户名查出用户信息
    UserDO getUserByUsername(String username);

    //根据参数UserDO中的信息修改数据库中相应的数据
    int updateUser(UserDO user);

    //查询用户
    List<UserDO> listUser(String keyword);

    //添加用户
    int insertUser(UserDO user);

    //删除用户
    int deleteUser(Integer id);

}
