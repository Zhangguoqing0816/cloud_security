package com.sso.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("role_resource")
public class RoleResource  implements Serializable {
    private String id;

    private String rId;

    private String mId;

}