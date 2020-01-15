package com.sso.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User  implements Serializable {
    private String userCode;

    private String userName;

    private String userContent;

    private String userPwd;
}