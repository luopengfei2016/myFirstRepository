package com.hxgz.jpa.dao;

import com.hxgz.jpa.pojo.Talent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDao extends JpaRepository<Talent,Integer> {
}
