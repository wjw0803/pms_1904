package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("mybatis_plus_user")
public class User {

    @TableId(type = IdType.AUTO)//主键策略
    private Integer id;

    private String userName;

    private String pwd;

    private String email;

    private String phone;

    private Integer age;

    private Integer isDel;

}
