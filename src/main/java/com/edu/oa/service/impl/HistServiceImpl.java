package com.edu.oa.service.impl;

import com.edu.oa.mdo.HistAvyDo;
import com.edu.oa.service.IHistService;
import com.edu.oa.util.SwapAreaUtils;
import com.edu.oa.vo.HistAvyInfoVo;
import com.edu.oa.vo.HistAvyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询历史记录
 */
@Service
public class HistServiceImpl implements IHistService {
    private static final Logger LOG = LoggerFactory.getLogger(HistServiceImpl.class);

    public HistAvyVo getMyHistLeave(Integer page, Integer rows){
        String userId = SwapAreaUtils.getCommonInfo().getUser().getUserId();
        HistAvyDo histAvyDo = new HistAvyDo();
        histAvyDo.setPage(true);
        histAvyDo.setPage(page);
        histAvyDo.setRows(rows);
        histAvyDo.setExecutorId(userId);
        List<HistAvyDo> histAvyDos = histAvyDo.queryHistAvyDoByExecutorId();
        HistAvyVo histAvyVo = new HistAvyVo();
        List<HistAvyInfoVo> histAvyInfoVos = new ArrayList<>();
        for (HistAvyDo avyDo : histAvyDos) {
            HistAvyInfoVo vo = new HistAvyInfoVo();
            BeanUtils.copyProperties(avyDo, vo);
            histAvyInfoVos.add(vo);
        }
        histAvyVo.setTotal(histAvyDo.getTotal());
        histAvyVo.setRows(histAvyInfoVos);
        LOG.info("查询历史数据 = " + histAvyDos);
        LOG.info("总条数 = " + histAvyDo.getTotal());
        return histAvyVo;
    }
}
