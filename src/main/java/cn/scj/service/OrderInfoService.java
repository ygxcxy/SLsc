package cn.scj.service;

import cn.scj.dto.ResponseCode;
import cn.scj.model.AuUser;
import cn.scj.model.OrderInfo;

public interface OrderInfoService {
    AuUser queryUserByCard(String shoppingCode);

    ResponseCode addOrderInfo(OrderInfo orderInfo);
}
