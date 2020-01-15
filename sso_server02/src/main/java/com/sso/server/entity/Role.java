package com.sso.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("role")
public class Role  implements Serializable {
    private String roleCode;

    private String roleName;

    private String roleContent;

}