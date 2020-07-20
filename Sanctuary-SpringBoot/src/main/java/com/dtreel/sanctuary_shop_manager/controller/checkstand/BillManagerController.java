package com.dtreel.sanctuary_shop_manager.controller.checkstand;


import com.dtreel.sanctuary_shop_manager.domain.ResponseVO;
import com.dtreel.sanctuary_shop_manager.domain.eneity.BillDO;
import com.dtreel.sanctuary_shop_manager.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DtreeL
 */
@RestController
@RequestMapping("/home/checkstand/billManager/api")
public class BillManagerController {
    @Autowired
    private BillService billService;

    //账单展示接口
    @GetMapping("/bill")
    public ResponseVO listBillByDate(String[] dates, String keyword){
        return billService.listBillByDate(dates,keyword);
    }
    //账单统计接口
    @GetMapping("/billCalculateInfo")
    public ResponseVO getBillCalculateInfo(String[] dates){
        return billService.getBillCalculateInfo(dates);
    }
    //修改账单是否生效等信息接口
    @PutMapping("/bill")
    public ResponseVO updateBill(@RequestBody BillDO bill){
        return billService.updateBill(bill);
    }
}
