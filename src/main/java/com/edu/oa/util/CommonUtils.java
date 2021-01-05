package com.edu.oa.util;

import com.edu.oa.mdo.SequenceDo;

public class CommonUtils {
    /**
     * 获取下一个序列号
     * @param sequenceId
     * @return
     */
    public static String nextSequence(String sequenceId){
        SequenceDo sequenceDo = new SequenceDo();
        sequenceDo.setSequenceId(sequenceId);
        return sequenceDo.getNextSequence();
    }
}
