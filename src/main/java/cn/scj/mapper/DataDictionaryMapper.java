package cn.scj.mapper;

import cn.scj.model.DataDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Shaochenjie
 */
public interface DataDictionaryMapper {

    List<DataDictionary> findByTypeCode(@Param("cardType") String cardType);

    DataDictionary findByValueId(@Param("cardId") String cardType, @Param("cardType") String card_type);

    int findValueIdByValue(@Param("userType") String userType, @Param("shipNote") String shipNote);
}