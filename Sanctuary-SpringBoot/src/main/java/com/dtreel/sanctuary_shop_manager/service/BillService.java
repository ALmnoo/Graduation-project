package com.dtreel.sanctuary_shop_manager.service;

import com.dtreel.sanctuary_shop_manager.domain.ResponseVO;
import com.dtreel.sanctuary_shop_manager.domain.StatusCode;
import com.dtreel.sanctuary_shop_manager.domain.eneity.BillDO;
import com.dtreel.sanctuary_shop_manager.domain.eneity.BillGoodsInfoDO;
import com.dtreel.sanctuary_shop_manager.domain.eneity.GoodsDO;
import com.dtreel.sanctuary_shop_manager.mapper.BillGoodsInfoMapper;
import com.dtreel.sanctuary_shop_manager.mapper.BillMapper;
import com.dtreel.sanctuary_shop_manager.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description TODO
 * @Author DtreeL
 * @Date 2020/5/5
 **/
@Service
public class BillService {
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private BillGoodsInfoMapper billGoodsInfoMapper;

    //账单展示
    public ResponseVO listBillByDate(String[] dates, String keyword) {
        //根据日期和搜索关键字查询账单，无关键字无日期选择默认搜索全部账单
        if (billMapper.listBillByDate(dates,keyword) != null){
            return ResponseVO.success(StatusCode.SUCCESS,"查询账单成功！",
                    billMapper.listBillByDate(dates,keyword));
        }
        return ResponseVO.success(StatusCode.SUCCESS,"无此时间段创建的账单！",
                billMapper.listBillByDate(dates,keyword));
    }
    //账单信息更改
    public ResponseVO updateBill(BillDO bill) {
        if (billMapper.updateBill(bill) == 1){
            return ResponseVO.success(StatusCode.SUCCESS,"更新账单信息成功！");
        }
        return ResponseVO.error(StatusCode.ERROR,"更新账单信息失败！");
    }

    public ResponseVO getBillCalculateInfo(String[] dates) {
        //查询销售时间段的情况数值统计
        if (billMapper.getBillCalculateInfo(dates) != null){
            return ResponseVO.success(StatusCode.SUCCESS,"查询时间段销售情况成功！",
                    billMapper.getBillCalculateInfo(dates));

        }
        return ResponseVO.error(StatusCode.ERROR,"查询时间段销售情况失败！");
    }

    public ResponseVO getNextBillId() {
        //查询即将生成的账单号
        if (billMapper.getNextBillId() > 0){
            return ResponseVO.success(StatusCode.SUCCESS,"查询即将生成的订单号成功！",
                    billMapper.getNextBillId());
        }
        return ResponseVO.error(StatusCode.ERROR,"查询即将生成的订单号失败！");
    }

    @Transactional
    public ResponseVO insertBill(BillDO bill) {
        //设置创建时间
        bill.setCreateTime(new Date());

        //对账单表和账单商品信息表操作
        int billInsertResult = billMapper.insertBill(bill);
        int billInfoResult = billGoodsInfoMapper.insertBillInfo(bill.getId(),bill.getBillGoodsInfos());

        //账单的商品依次更新库存信息
        for (BillGoodsInfoDO billGoodsInfo : bill.getBillGoodsInfos()) {
            GoodsDO goods = billGoodsInfo.getGoods();
            goods.setInventory(goods.getInventory() - billGoodsInfo.getQuantitySold());
            goodsMapper.updateGoodsInventory(goods);
        }

        //操作响应
        if (billInsertResult == 1 && billInfoResult == bill.getBillGoodsInfos().size()){
            return ResponseVO.success(StatusCode.SUCCESS,"生成账单成功！",
                    billMapper.getBillCreatTimeById(bill.getId()));
        }
        return ResponseVO.error(StatusCode.ERROR,"生成账单成功失败！");
    }
}
