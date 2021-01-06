package com.edu.oa.service.impl;

import com.edu.oa.mdo.HistAvyDo;
import com.edu.oa.service.IHistService;
import com.edu.oa.util.SwapAreaUtils;
import com.edu.oa.vo.HistAvyInfoSubVo;
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

    /**
     * 根据操作员查询参与过的流程信息
     * @param page
     * @param rows
     * @return
     */
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
            //查询详细流程信息
            List<HistAvyDo> infoList = avyDo.queryHistAvyInfoByProcessInstId();
            List<HistAvyInfoSubVo> subVoList = new ArrayList<>();
            for (HistAvyDo info : infoList) {
                HistAvyInfoSubVo subVo = new HistAvyInfoSubVo();
                BeanUtils.copyProperties(info, subVo);
                subVoList.add(subVo);
                //赋值流程申请人
                if (info.getAvyId().equals("1"))
                    vo.setOwner(info.getExecutorName());
            }
            vo.setSubVoList(subVoList);
            histAvyInfoVos.add(vo);
        }
        histAvyVo.setTotal(histAvyDo.getTotal());
        histAvyVo.setRows(histAvyInfoVos);
        LOG.info("查询历史数据 = " + histAvyDos);
        LOG.info("总条数 = " + histAvyDo.getTotal());
        return histAvyVo;
    }

    /**
     * 根据老师id查询影响到本教师课程的所有请假记录
     * @return
     */
    public HistAvyVo findMyApproveProcess(){
        String userId = SwapAreaUtils.getCommonInfo().getUser().getUserId();

        return null;
    }
}
