package com.zzp.fengmiweb.dao;

import com.zzp.fengmiweb.entity.GoodsType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IGoodsTypeDao {
    @Insert("insert into t_goodstype(name,level,parentName,flag) " +
            " values(#{name},#{level},#{parentName},1)")
    int insert(GoodsType gt);

    //查询一级类型
    @Select("SELECT * from t_goodstype where level = 1")
    @ResultType(GoodsType.class)
    List<GoodsType> queryByLevel1();

    //查询全部
    @Select("SELECT * from t_goodstype")
    @ResultType(GoodsType.class)
    List<GoodsType> queryAll();

    //根据商品等级和名称查商品 --- admin
    @Select("SELECT * from t_goodstype where level = #{flag} and name like '%${name}%'")
    @ResultType(GoodsType.class)
    List<GoodsType> queryByLAndN(@Param("flag")int flag,@Param("name")String name);

    //根据商品id删除商品 --- admin
    @Select("delete from t_goodstype where id=#{id}")
    int deleteById(@Param("id")int id);
}
