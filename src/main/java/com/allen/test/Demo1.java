package com.allen.test;

import com.allen.pojo.Allen;
import com.allen.pojo.Person;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.sql.Sql;

import java.util.*;

/**
 * @author allen
 * @createdate 2019-02-15-8:44
 */
public class Demo1 {
    public static void main(String[] args){
        // 创建一个数据源
        SimpleDataSource dataSource = new SimpleDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/nutz");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        // 创建一个NutDao实例,在真实项目中, NutDao通常由ioc托管, 使用注入的方式获得.
        Dao dao = new NutDao(dataSource);

        // 创建表
        dao.create(Person.class, false); // false的含义是,如果表已经存在,就不要删除重建了.

        Person p = new Person();
        p.setName("ABC");
        p.setAge(20);
        //dao.insert(p);
        ArrayList<Person> people = new ArrayList<>();

        for (int i = 0; i <20 ; i++) {
            people.add(new Person("allen",1+i));
        }
        System.out.println("-------分页开始----");

        List<Person> query2 = dao.query(Person.class, Cnd.where("id",">",10).and("name","=","allen"), dao.createPager(2, 5));
        for (Person pb:query2
             ) {
            System.out.println(pb);
        }
        System.out.println("-------分页结束----");

        List<Person> query1 = dao.query(Person.class, Cnd.wrap("id>10"));
        for (Person pa:query1
             ) {
            System.out.println(pa);
        }
        System.out.println("******************");
        List<Person> query = dao.query(Person.class, Cnd.where("id",">",5));
        for (Person pp:query
             ) {
            System.out.println(pp);
        }
        System.out.println("*********原生sql语句************");
        Sql sql = Sqls.create("select p.name,p.age,c.cityname from t_person p inner join city c on p.cid=c.id where p.name=@name");
        sql.setParam("name","allen");
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(Allen.class));
        Sql execute = dao.execute(sql);
        List<Allen> lista = sql.getList(Allen.class);
        for (Allen allen:lista
             ) {
            System.out.println(allen);
        }
        System.out.println(lista.size());
    }
}
