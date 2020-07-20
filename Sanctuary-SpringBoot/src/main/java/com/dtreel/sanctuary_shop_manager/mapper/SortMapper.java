package com.dtreel.sanctuary_shop_manager.mapper;


import com.dtreel.sanctuary_shop_manager.domain.eneity.SortDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author DtreeL
 */
public interface SortMapper {
    List<SortDO> listSort();

    int insertSort(SortDO sort);

    int updateSort(SortDO sort);

    int deleteSortById(Integer id);

    int deleteSortByIds(@Param("ids") Integer[] ids);
    //     List<Sort> getAllSort();
//
//     int addSort(Sort sort);
//
//     int updateSort(Sort sort);
//
//     int deleteSort(Integer id);
//
//     int deleteSorts(@Param("ids") Integer[] ids);
 }
