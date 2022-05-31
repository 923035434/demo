package com.ll.generateddemo.dao.mapper;

import com.ll.generateddemo.dao.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
* @Entity com.ll.generateddemo.dao.model.User
*/
@Repository
public interface UserMapper {


    @Select("select id,name,gender,phone,message,"+
        "wx_code,birthday "+
    "from user where "+
    " id = #{id} ")
    User selectByPrimaryKey(@Param("id") Long id );

    @Select("<script> select id,name,gender,phone,message,"+
        "wx_code,birthday "+
    "from user where "+
    " id in  <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'> #{item} </foreach>  </script>")
    List<User> selectListByPrimaryKeys(@Param("ids") List<Long> ids );


    @Delete("delete from user where "+
    " id = #{id} ")
    int deleteByPrimaryKey(@Param("id") Long id );

    @Insert("insert into user"+
    "( id,name,gender,phone,message"+
        ",wx_code,birthday) "+
    "values (#{id},#{name},#{gender},#{phone},#{message}"+
        ",#{wxCode},#{birthday})")
    int insert(User record);

    @Update("update user "+
    "set name=#{name},gender=#{gender},phone=#{phone},message=#{message},wx_code=#{wxCode}"+
        ",birthday=#{birthday}"+
    "where  id = #{id} ")
    int updateByPrimaryKey(User record);


    @Select("<script>"+
    "<bind name='offset' value='(pageIndex-1)*pageSize'></bind>\n"+
    " select id,name,gender,phone,message,"+
        "wx_code,birthday "+
    "from user limit #{offset},#{pageSize} </script>")
    List<User> pageList(@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize);



}
