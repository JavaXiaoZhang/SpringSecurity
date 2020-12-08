package com.zq.entity;

import com.alibaba.fastjson.util.IOUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.*;

/**
 * @author ZQ
 */
@ApiModel(value = "用户类",description = "desc")
public class User implements Serializable {

    private static final long serialVersionUID = -6849794470754667710L;

    @ApiModelProperty(name = "id")
    private Long id;
    private String username;
    private String password;
    private String roles;
    private String legalPerson;

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
