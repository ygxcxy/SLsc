package cn.scj.service.impl;

import cn.scj.mapper.FunctionMapper;
import cn.scj.model.Function;
import cn.scj.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname FunctionServiceImpl
 * @Description TODO
 * @Date 2019/10/11 12:52
 */
@Service
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    private FunctionMapper functionMapper;



    @Override
    public List<Function> findAll() {
        return functionMapper.findAll();
    }

    @Override
    public List<Function> findFirst(int i) {

        return functionMapper.findFirst(i);
    }

    @Override
    public List<Function> findSecond(List<Long> i) {

        return functionMapper.findSecond(i);
    }
}
