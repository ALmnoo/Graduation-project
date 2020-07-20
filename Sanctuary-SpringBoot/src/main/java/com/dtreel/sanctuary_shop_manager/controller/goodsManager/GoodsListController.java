package com.dtreel.sanctuary_shop_manager.controller.goodsManager;


import com.dtreel.sanctuary_shop_manager.domain.ResponseVO;
import com.dtreel.sanctuary_shop_manager.domain.eneity.GoodsDO;
import com.dtreel.sanctuary_shop_manager.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DtreeL
 */
@RestController
@RequestMapping("/home/goodsManager/goodsList/api/goods")
public class GoodsListController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/")
    public ResponseVO getPageData(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            String keyword){
        return goodsService.getGoodsPageData(page,size,keyword);
    }

    @PostMapping("/")
    public ResponseVO addGoods(@RequestBody GoodsDO goods){
        return goodsService.insertGoods(goods);
    }

    @PutMapping("/")
    public ResponseVO updateGoods(@RequestBody GoodsDO goods){
        return goodsService.updateGoods(goods);
    }

    @DeleteMapping("/{id}")
    public ResponseVO deleteGoodsById(@PathVariable Integer id){
        return goodsService.deleteGoodsById(id);
    }

    @DeleteMapping("/")
    public ResponseVO deleteGoodsByIds(@PathVariable Integer[] ids){
        return goodsService.deleteGoodsByIds(ids);
    }
}
