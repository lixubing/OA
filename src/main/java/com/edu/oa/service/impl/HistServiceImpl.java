package com.edu.oa.service.impl;

import com.edu.oa.mdo.HistAvyDo;
import com.edu.oa.mdo.LeaveInfoDo;
import com.edu.oa.service.IHistService;
import com.edu.oa.util.Constant;
import com.edu.oa.util.SwapAreaUtils;
import com.edu.oa.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            //查询影响的课程
            LeaveInfoDo leaveInfoDo = new LeaveInfoDo();
            leaveInfoDo.setProcessInstId(avyDo.getProcessInstId());
            List<LeaveInfoDo> leaveInfoDos = leaveInfoDo.queryCourseByProcessInstId();
            StringBuilder sb = new StringBuilder();
            LOG.info("leaveInfoDos" + leaveInfoDos);
            for (LeaveInfoDo infoDo : leaveInfoDos) {
                if (StringUtils.isNotBlank(infoDo.getCourseName())) {
                    sb.append(infoDo.getCourseName()).append(",");
                }
            }

            if (sb.indexOf(",") > 0){
                sb.deleteCharAt(sb.length() - 1);
            }
            vo.setCourse(sb.toString());
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
     * @param page 当前页
     * @param rows 每页大小
     * @return 姓名，开始时间，结束时间，流程状态
     */
    public HistAvyVo findTeacherCourseLeave(Integer page, Integer rows){
        String userId = SwapAreaUtils.getCommonInfo().getUser().getUserId();
        //根据教师编号查询出所有影响课程的流程实例id
        //根据流程功能实例id查询请假信息表biz_info和流程实例表process_info
        LeaveInfoDo leaveInfoDo = new LeaveInfoDo();
        leaveInfoDo.setTeacherNo(userId);
        leaveInfoDo.setPage(true);
        leaveInfoDo.setPage(page);
        leaveInfoDo.setRows(rows);
        List<LeaveInfoDo> leaveInfoDos = leaveInfoDo.queryTeacherLeaveInfoByTeacherNo();
        HistAvyVo histAvyVo = new HistAvyVo();
        List<HistAvyInfoVo> histAvyInfoVos = new ArrayList<>();
        for (LeaveInfoDo infoDo : leaveInfoDos) {
            HistAvyInfoVo histAvyInfoVo = new HistAvyInfoVo();
            BeanUtils.copyProperties(infoDo, histAvyInfoVo);
            histAvyInfoVo.setOwner(infoDo.getUsername());
            histAvyInfoVos.add(histAvyInfoVo);
        }
        histAvyVo.setRows(histAvyInfoVos);
        histAvyVo.setTotal(leaveInfoDo.getTotal());
        return histAvyVo;
    }

    /**
     * 查询已拒绝的
     * @return
     */
    public List<RefuseLeaveVo> getRefuseList(){
        String userId = SwapAreaUtils.getCommonInfo().getUser().getUserId();
        LeaveInfoDo leaveInfoDo = new LeaveInfoDo();
        leaveInfoDo.setUserId(userId);
        leaveInfoDo.setProcessTpcd(Constant.processTPCD_14);
        //暂定只能查询请假日期开始之前的
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String now = format.format(new Date());
        leaveInfoDo.setStartDate(now);
        List<LeaveInfoDo> leaveInfoDos = leaveInfoDo.queryRefuseList();
        List<RefuseLeaveVo> result = new ArrayList<>();
        for (LeaveInfoDo infoDo : leaveInfoDos) {
            RefuseLeaveVo refuseLeaveVo= new RefuseLeaveVo();
            BeanUtils.copyProperties(infoDo, refuseLeaveVo);
            result.add(refuseLeaveVo);
        }
        return result;
    }

    /**
     * 根据审批执行人的id查询审批历史
     * @param inVo
     * @return
     */
    @Override
    public HistAvyVo findLeaveByReviewExecutor(ReviewHistLeaveInVo inVo) {
        String userId = SwapAreaUtils.getCommonInfo().getUser().getUserId();
        LeaveInfoDo leaveInfoDo = new LeaveInfoDo();
        BeanUtils.copyProperties(inVo, leaveInfoDo);
        leaveInfoDo.setExecutorId(userId);
        leaveInfoDo.setPage(true);
        List<LeaveInfoDo> leaveInfoList = leaveInfoDo.queryLeaveByReviewExecutor();
        HistAvyVo histAvyVo = new HistAvyVo();
        List<HistAvyInfoVo> list = new ArrayList<>();
        for (LeaveInfoDo infoDo : leaveInfoList) {
            HistAvyInfoVo infoVo = new HistAvyInfoVo();
            BeanUtils.copyProperties(infoDo, infoVo);
            infoVo.setOwner(infoDo.getUsername());
            list.add(infoVo);
        }
        histAvyVo.setTotal(leaveInfoDo.getTotal());
        histAvyVo.setRows(list);
        return histAvyVo;
    }

    /**
     * 根据流程实例id查询流程详细信息
     * @param processInstId
     * @return
     */
    public List<HistAvyInfoSubVo> queryHistAvyInfoByProcessInstId(String processInstId){
        HistAvyDo avyDo = new HistAvyDo();
        List<HistAvyDo> infoList = avyDo.queryHistAvyInfoByProcessInstId();
        List<HistAvyInfoSubVo> result = histAvyDoListToHistAvyInfoSubVoList(infoList);
        return result;
    }

    public List<HistAvyInfoSubVo> histAvyDoListToHistAvyInfoSubVoList(List<HistAvyDo> dos){
        List<HistAvyInfoSubVo> result = new ArrayList<>();
        HistAvyInfoSubVo vo = new HistAvyInfoSubVo();
        for (HistAvyDo aDo : dos) {
            BeanUtils.copyProperties(aDo, vo);
            result.add(vo);
        }
        return result;
    }

}
