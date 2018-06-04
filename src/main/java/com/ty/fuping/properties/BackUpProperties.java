package com.ty.fuping.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 备份配置
 *
 * @author ZhanJingbo
 * @version 1.0.0
 * Created on 2018/5/29
 */
@ConfigurationProperties(prefix = "fupin.backup")
public class BackUpProperties {
    /**
     * 数据库地址
     */
    private String host;
    /**
     * 数据库端口
     */
    private Integer port;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 数据库
     */
    private String database;

    /**
     * 文件
     */
    private String file;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
