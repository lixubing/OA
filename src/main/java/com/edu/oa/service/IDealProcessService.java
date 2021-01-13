package com.edu.oa.service;

import com.edu.oa.mdo.ProcessInstDo;

public interface IDealProcessService {
    public void dealProcess(String description, String decision, String processInstId);
    public ProcessInstDo getProcessInst(String processInstId);
}
