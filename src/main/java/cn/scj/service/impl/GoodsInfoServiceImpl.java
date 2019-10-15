package cn.scj.service.impl;

import cn.scj.dto.ResponseCode;
import cn.scj.mapper.GoodsInfoMapper;
import cn.scj.model.GoodsInfoWithBLOBs;
import cn.scj.service.GoodsInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname GoodsInfoServiceImpl
 * @Description TODO
 * @Date 2019/10/14 10:11
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public void insertGoods(GoodsInfoWithBLOBs goodsInfo) {
        goodsInfoMapper.insert(goodsInfo);
    }

    @Override
    public ResponseCode findList(ResponseCode code, String goodsName) {
        ResponseCode responseCode = new ResponseCode();
        PageHelper.startPage(code.getPage(),code.getLimit());
        List<GoodsInfoWithBLOBs> list = goodsInfoMapper.findList(goodsName);
        PageInfo<GoodsInfoWithBLOBs> pageInfo = new PageInfo<>(list);
        responseCode.setCode(0);
        responseCode.setData(list);

        return responseCode;
    }

    @Override
    public GoodsInfoWithBLOBs findBySn(String goodsSn) {

        return goodsInfoMapper.findBySn(goodsSn);
    }

    @Override
    public ResponseCode dels(List<Integer> ids) {
        ResponseCode code = new ResponseCode();
        if(ids==null){
            code.setMsg("id值为空");
            code.setCode(ResponseCode.FAIL);
        }else {
            goodsInfoMapper.dels(ids);
        }

        return null;
    }

    @Override
    public GoodsInfoWithBLOBs query(Long id) {
        return goodsInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateGoods(GoodsInfoWithBLOBs goodsInfoWithBLOBs) {
        return goodsInfoMapper.updateByPrimaryKeySelective(goodsInfoWithBLOBs);
    }
}
