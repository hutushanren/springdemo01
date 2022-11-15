package com.example.springdemo01.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName("user_role_relation")
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_id")
    private Long userId;
    @TableField(value = "role_id")
    private Long roleId;

    public UserRoleRelation(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

}
