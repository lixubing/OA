package com.edu.oa.mdo;

import org.apache.commons.lang3.StringUtils;

public class SequenceDo extends BaseDo {
    private String sequenceId;
    private Integer num;
    private Integer step;
    private Integer min;
    private Integer max;

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
    public String getNextSequence(){
        if (StringUtils.isBlank(this.getSequenceId()))
            return null;
        SequenceDo sequence = (SequenceDo) getObjectByParam("SequenceDo.getNextSequence", this);
        Integer newNum = sequence.getNum() + 1;
        if (newNum > sequence.getMax()){
            newNum = sequence.getMin();
        }
        sequence.setNum(newNum);
        update("SequenceDo.updateByAdd", sequence);
        return newNum.toString();
    }
}
