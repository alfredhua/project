package com.generator.entity;

import lombok.Getter;

/**
 * @auth guozhenhua
 * @date 2018/12/24
 */
@Getter
public class JDBCEntity {


    private String jdbcDriver;

    private String jdbcUrl;

    private String jdbcUseName;

    private String jdbcPassword;


    private static JDBCEntity jdbcEntity =null;

    public static JDBCEntity getInstance(){
        if (jdbcEntity ==null){
            synchronized(JDBCEntity.class){
                if(jdbcEntity ==null) {
                    jdbcEntity = new JDBCEntity();
                }
            }
        }
        return jdbcEntity;

    }



    public JDBCEntity setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
        return this;
    }

    public JDBCEntity setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;return this;
    }

    public JDBCEntity setJdbcUseName(String jdbcUseName) {
        this.jdbcUseName = jdbcUseName;return this;
    }

    public JDBCEntity setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;return this;
    }

}
