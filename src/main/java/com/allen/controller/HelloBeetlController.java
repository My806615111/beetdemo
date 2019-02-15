package com.allen.controller;

import com.allen.pojo.Allen;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.sql.Sql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author allen
 * @createdate 2019-02-14-17:08
 */
@Controller
@RequestMapping("/home")
public class HelloBeetlController {

    private static Logger logger = LoggerFactory.getLogger(HelloBeetlController.class);

    /**
     * 测试beetl模板
     *
     * @return
     */
    @RequestMapping("/add")
    public ModelAndView home() {

        ModelAndView modelAndView = new ModelAndView();
        logger.info("add request");
        modelAndView.addObject("email", "apk2sf@163.com");
        modelAndView.addObject("a", "allenc.com");
        modelAndView.setViewName("add");

        return modelAndView;
    }

    @RequestMapping("/showlist")
    public ModelAndView showlist(){
        SimpleDataSource dataSource = new SimpleDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/nutz");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        // 创建一个NutDao实例,在真实项目中, NutDao通常由ioc托管, 使用注入的方式获得.
        Dao dao = new NutDao(dataSource);

        Sql sql = Sqls.create("select p.name,p.age,c.cityname from t_person p inner join city c on p.cid=c.id where p.name=@name");
        sql.setParam("name","allen");
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(Allen.class));
        Sql execute = dao.execute(sql);
        List<Allen> lista = sql.getList(Allen.class);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("a", lista);
        modelAndView.setViewName("showlist");

        return modelAndView;
    }
}
