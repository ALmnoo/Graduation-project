package com.dtreel.sanctuary_shop_manager.mapper;


import com.dtreel.sanctuary_shop_manager.domain.eneity.BillCalculateInfoDO;
import com.dtreel.sanctuary_shop_manager.domain.eneity.BillDO;
import com.dtreel.sanctuary_shop_manager.domain.eneity.BillGoodsInfoDO;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author DtreeL
 */
public interface BillMapper {

    //根据日期与关键字得到账单信息
    List<BillDO> listBillByDate(@Param("dates") String[] dates, @Param("keyword") String keyword);

    //更新账单信息
    int updateBill(BillDO bill);

    //根据日期得到销售统计情况
    BillCalculateInfoDO getBillCalculateInfo(@Param("dates") String[] dates);

    //得到即将生成的账单编号
    int getNextBillId();

    //新增账单信息
    int insertBill(BillDO bill);

    //得到账单创建时间
    Date getBillCreatTimeById(BigInteger id);
}
