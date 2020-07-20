package com.dtreel.sanctuary_shop_manager.service;

import com.dtreel.sanctuary_shop_manager.domain.ResponseVO;
import com.dtreel.sanctuary_shop_manager.domain.StatusCode;
import com.dtreel.sanctuary_shop_manager.domain.eneity.SortDO;
import com.dtreel.sanctuary_shop_manager.mapper.SortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author DtreeL
 * @Date 2020/5/5
 **/
@Service
public class SortService {
    @Autowired
    private SortMapper sortMapper;


    public ResponseVO listSort() {
        if (sortMapper.listSort() != null){
            return ResponseVO.success(StatusCode.SUCCESS,"查询所有商品类别成功！",sortMapper.listSort());
        }
        return ResponseVO.error(StatusCode.ERROR,"查询所有商品类别失败！");
    }

    public ResponseVO insertSort(SortDO sort) {
        if (sortMapper.insertSort(sort) == 1){
            return ResponseVO.success(StatusCode.SUCCESS,"添加商品类别成功！");
        }
        return ResponseVO.error(StatusCode.ERROR,"添加商品类别失败！");
    }

    public ResponseVO updateSort(SortDO sort) {
        if (sortMapper.updateSort(sort) == 1){
            return ResponseVO.success(StatusCode.SUCCESS,"修改商品类别成功！");
        }
        return ResponseVO.error(StatusCode.ERROR,"修改商品类别失败！");
    }

    public ResponseVO deleteSortById(Integer id) {
        if (sortMapper.deleteSortById(id) == 1){
            return ResponseVO.success(StatusCode.SUCCESS,"删除商品类别成功！");
        }
        return ResponseVO.error(StatusCode.ERROR,"删除商品类别失败！");
    }

    public ResponseVO deleteSortByIds(Integer[] ids) {
        if (sortMapper.deleteSortByIds(ids) == ids.length){
            return ResponseVO.success(StatusCode.SUCCESS,"批量删除商品类别成功！");
        }
        return ResponseVO.error(StatusCode.ERROR,"批量删除类别失败！");
    }
}
