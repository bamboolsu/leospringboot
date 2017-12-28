package com.bamboolsu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  BoyRep extends JpaRepository<Boy,Integer> {
    //通过年龄查询
    public List<Boy> findByAge(Integer age);
}
