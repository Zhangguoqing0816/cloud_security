package com.sso.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("resource")
public class Resource  implements Serializable {
    private String resourceCode;

    private String resourceName;

    private String resourceParent;

    private String resourceContent;

}