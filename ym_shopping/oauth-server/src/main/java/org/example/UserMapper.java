package org.example;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.User;

@Mapper
public interface UserMapper {
    @Select({"select id,name,age,pwd from user where name=#{name}"})
    public User loadUserByUserName(String name);

    @Insert({"insert into user(id,name,age,pwd,phone) values(#{id},#{name},#{age},#{pwd},#{phone})"})
    public Integer insertUser(User user);
}
