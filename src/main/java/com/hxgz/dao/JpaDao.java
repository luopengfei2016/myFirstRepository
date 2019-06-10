package com.hxgz.dao;

import com.hxgz.pojo.Talent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDao extends JpaRepository<Talent,Integer> {
}
