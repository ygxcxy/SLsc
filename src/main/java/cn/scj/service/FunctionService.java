package cn.scj.service;

import cn.scj.model.Function;

import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname FunctionService
 * @Description TODO
 * @Date 2019/10/11 12:52
 */
public interface FunctionService {

    List<Function> findAll();

    List<Function> findFirst(int i);

    List<Function> findSecond(List<Long> i);
}
