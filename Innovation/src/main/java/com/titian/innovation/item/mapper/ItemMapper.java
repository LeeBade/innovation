package com.titian.innovation.item.mapper;

import com.titian.innovation.item.domain.Item;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ItemInfoMapper {
    @Select("SELECT * FROM itemInfo WHERE id = #{id}")
    Item getOneItemInfo(@Param ("id") int id);
    @Select ("SELECT * FROM itemInfo")
    List<Item> getAllItemInfo();
    @Select("SELECT * FROM itemInfo WHERE userId=#{userId}")
    List<Item> getUserItemInfo(@Param("userId")String userId);
    @Select("SELECT *FROM itemInfo WHERE name LIKE CONCAT('%',#{keywords},'%')")
    List<Item> searchItemInfo(@Param ("keywords")String keywords);
    @Insert("INSERT INTO item (name,userId) VALUES(#{item.name},#{item.userId})")
    void createOneItemInfo (@Param("item") Item item);
    @Update("UPDATE item SET name = #{item.name},userId=#{item.userId} WHERE id = #{item.id};\n")
    int updateOneItemInfo (@Param ("item") Item item);
    @Delete("DELETE FROM itemInfo WHERE id=#{id}")
    int deleteOneItem (@Param("id") int id);

}
