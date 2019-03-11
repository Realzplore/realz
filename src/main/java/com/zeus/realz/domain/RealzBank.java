package com.zeus.realz.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: liping.zheng
 * @Date: 2019/3/11
 */
@Data
@TableName("realz_bank")
public class RealzBank {
    @TableId
    private Long id;
    @TableField
    private String bankId;
    @TableField
    private String bankName;
}
