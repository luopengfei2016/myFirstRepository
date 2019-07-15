package com.hxgz.jpa.controller;

import com.hxgz.config.Result;
import com.hxgz.jpa.dao.JpaDao;
import com.hxgz.jpa.pojo.Talent;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Jackson
 * @description springBoot 整合jpa测试
 * @date 2019/6/10
 */

@RestController
@RequestMapping("/talent")
@Api(tags = "talentController", description = "jpa整合测试")

public class JpaController {

    @Autowired
    private JpaDao jpaDao;

    @RequestMapping(value = "/findAllTalent", method = RequestMethod.GET)
    public Result findAllTalent() {
        List<Talent> talentList = jpaDao.findAll();
        for (int i = 0; i < talentList.size(); i++) {
            System.out.println(talentList.get(i));
        }
        return new Result(true, 200, "查询成功", new ArrayList<Talent>());
    }
}
