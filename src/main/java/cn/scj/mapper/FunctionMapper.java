package cn.scj.mapper;

import cn.scj.model.Function;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FunctionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Function record);

    int insertSelective(Function record);

    Function selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);


    List<Function> findAll();

    List<Function> findFirst(@Param("i") int i);

    List<Function> findSecond(List<Long> i);
}