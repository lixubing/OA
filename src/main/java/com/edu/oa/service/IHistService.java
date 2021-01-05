package com.edu.oa.service;

import com.edu.oa.mdo.HistAvyDo;
import com.edu.oa.vo.HistAvyVo;

import java.util.List;

public interface IHistService {
    public HistAvyVo getMyHistLeave(Integer page, Integer rows);
}
