package com.allen.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author allen
 * @createdate 2019-02-15-10:15
 */

//@Table("allen")
public class Allen {
    @Column
    private String name;

    @Column()
    private String cityname;

    @Column
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Allen{" +
                "name='" + name + '\'' +
                ", cityname='" + cityname + '\'' +
                ", age=" + age +
                '}';
    }
}
