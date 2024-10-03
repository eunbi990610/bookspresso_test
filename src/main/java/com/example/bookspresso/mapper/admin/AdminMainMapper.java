package com.example.bookspresso.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface AdminMainMapper {

    Optional<Long> selectAdmin(@Param("adminLoginId") String adminLoginId,
                               @Param("adminPassword") String adminPassword);

    int selectAdminCount(@Param("adminLoginId") String adminLoginId,
                         @Param("adminPassword") String adminPassword);


    String selectAdminLoginId(Long adminId);

    int selectStatus(String adminLoginId);

}














