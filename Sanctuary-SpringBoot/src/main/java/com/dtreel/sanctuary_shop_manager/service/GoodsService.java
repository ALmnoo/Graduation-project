package com.dtreel.sanctuary_shop_manager.service;

import com.dtreel.sanctuary_shop_manager.domain.ResponsePageData;
import com.dtreel.sanctuary_shop_manager.domain.ResponseVO;
import com.dtreel.sanctuary_shop_manager.domain.StatusCode;
import com.dtreel.sanctuary_shop_manager.domain.eneity.GoodsDO;
import com.dtreel.sanctuary_shop_manager.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description TODO
 * @Author DtreeL
 * @Date 2020/5/4
 **/
@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public ResponseVO getGoodsPageData(Integer page, Integer size, String keyword) {
        //SQL中进行分页的计算
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        //得到搜索后的分页数据
        List<GoodsDO> goods = goodsMapper.getGoodsPageData(page, size, keyword);
        //得到搜索后的总记录数
        Long total = goodsMapper.getGoodsTotalByKeyword(keyword);
        ResponsePageData responsePage = new ResponsePageData();
        //把结果填充到responsePage对象中
        responsePage.setTotal(total);
        responsePage.setData(goods);
        if (goods != null && total != null) {
            return ResponseVO.success(StatusCode.SUCCESS, "查询分页商品数据成功！", responsePage);
        }
        return ResponseVO.error(StatusCode.ERROR, "查询分页商品数据失败！");
    }

    public ResponseVO insertGoods(GoodsDO goods) {
        //计算利润
        BigDecimal profit = goods.getPrice().subtract(goods.getCost());
        //设置利润
        goods.setProfit(profit);

        //商品添加操作
        if (goodsMapper.insertGoods(goods) == 1) {
            return ResponseVO.success(StatusCode.SUCCESS, "添加商品成功！");
        }
        return ResponseVO.error(StatusCode.ERROR, "添加商品失败！");
    }

    public ResponseVO updateGoods(GoodsDO goods) {
        BigDecimal profit = goods.getPrice().subtract(goods.getCost());
        goods.setProfit(profit);
        if (goodsMapper.updateGoods(goods) == 1) {
            return ResponseVO.success(StatusCode.SUCCESS, "更新商品成功！");
        }
        return ResponseVO.error(StatusCode.ERROR, "更新商品失败！");
    }

    public ResponseVO deleteGoodsById(Integer id) {
        if (goodsMapper.deleteGoodsById(id) == 1) {
            return ResponseVO.success(StatusCode.SUCCESS, "删除商品成功！");
        }
        return ResponseVO.error(StatusCode.ERROR, "删除商品失败！");
    }

    @Transactional
    public ResponseVO deleteGoodsByIds(Integer[] ids) {
        if (goodsMapper.deleteGoodsByIds(ids) == ids.length) {
            return ResponseVO.success(StatusCode.SUCCESS, "批量删除商品成功！");
        }
        return ResponseVO.error(StatusCode.ERROR, "批量删除商品失败！");
    }

    public ResponseVO getGoodsById(Integer goodsId) {
        if (goodsMapper.getGoodsById(goodsId) != null){
            return ResponseVO.success(StatusCode.SUCCESS,"查询商品数据成功！", goodsMapper.getGoodsById(goodsId));
        }
        return ResponseVO.error(StatusCode.ERROR,"无此商品！");
    }
}
