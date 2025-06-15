package com.achao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("resources")
public class Resource {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String url;

    // 构造方法、getter和setter
    public Resource() {}

    public Resource(String name, String type, String url) {
        this.name = name;
        this.type = type;
        this.url = url;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}