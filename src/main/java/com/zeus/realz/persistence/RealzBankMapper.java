package com.zeus.realz.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zeus.realz.domain.RealzBank;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: liping.zheng
 * @Date: 2019/3/11
 */
public interface RealzBankMapper extends BaseMapper<RealzBank> {
    RealzBank selectByBankId(@Param("bankId") String bankId);
}
