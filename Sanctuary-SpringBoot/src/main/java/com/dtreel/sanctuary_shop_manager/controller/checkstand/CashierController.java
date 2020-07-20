package com.dtreel.sanctuary_shop_manager.controller.checkstand;


import com.dtreel.sanctuary_shop_manager.domain.ResponseVO;
import com.dtreel.sanctuary_shop_manager.domain.eneity.BillDO;
import com.dtreel.sanctuary_shop_manager.service.BillService;
import com.dtreel.sanctuary_shop_manager.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DtreeL
 */
@RestController
@RequestMapping("/home/checkstand/cashier/api/")
public class CashierController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BillService billService;

    //通过商品id获得商品信息
    @GetMapping("/goods/{goodsId}")
    public ResponseVO getGoodsById(@PathVariable Integer goodsId){
        return goodsService.getGoodsById(goodsId);
    }

    //得到下一张账单号
    @GetMapping("/billId")
    public ResponseVO getNextBillId(){
        return billService.getNextBillId();
    }

    //生成账单
    @PostMapping("/bill")
    public ResponseVO insertBill(@RequestBody BillDO bill){
        return billService.insertBill(bill);
    }
}
