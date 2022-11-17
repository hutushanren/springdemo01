package com.example.springdemo01.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

@Data
@TableName("role")
public class Role implements Serializable, Comparable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Date createTime;

    @TableField(value = "name")
    private String name;

    @TableField(value = "code")
    private String code;

    @TableField(value = "sort")
    private Integer sort;

    @TableField(value = "state")
    private Boolean state;
    @Override
    public int compareTo(Object o) {
        Role r1 = (Role) o;
        return r1.sort - this.sort;
    }
}
