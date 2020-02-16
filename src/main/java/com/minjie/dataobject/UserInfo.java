package com.minjie.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by zhengjie on 2019/12/26.
 */
@Data
@Entity
@DynamicUpdate
public class UserInfo {
    @Id
    private String userId;

    private String username;

    private String password;

    private String gender;

    private String telephone;

    private String address;

    private String email;

    private Date createTime;

    private Date updateTime;
}
