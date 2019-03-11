package com.zeus.realz.persistence.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zeus.realz.domain.RealzBank;
import com.zeus.realz.persistence.RealzBankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liping.zheng
 * @Date: 2019/3/11
 */
@Service
public class RealzBankService extends ServiceImpl<RealzBankMapper, RealzBank> {
    @Autowired
    private RealzBankMapper realzBankMapper;

    public RealzBank selectByBankId(String bankId) {
        return realzBankMapper.selectByBankId(bankId);
    }

    public RealzBank insert(RealzBank realzBank) {
        realzBankMapper.insert(realzBank);
        return realzBank;
    }

}
