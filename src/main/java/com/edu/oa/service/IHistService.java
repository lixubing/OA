package com.edu.oa.service;

import com.edu.oa.mdo.HistAvyDo;
import com.edu.oa.vo.HistAvyVo;
import com.edu.oa.vo.RefuseLeaveVo;
import com.edu.oa.vo.ReviewHistLeaveInVo;

import java.util.List;

public interface IHistService {
    public HistAvyVo getMyHistLeave(Integer page, Integer rows);
    public List<RefuseLeaveVo> getRefuseList();

    HistAvyVo findLeaveByReviewExecutor(ReviewHistLeaveInVo inVo);
}
