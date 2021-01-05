package com.edu.oa.mdo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class TplMpngRuleDo extends BaseDo{
    private String tplNo;
    private String conditionDesc;
    private String classNo;
    private Date tms;
    private String functionId;
    private String days;

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTplNo() {
        return tplNo;
    }

    public void setTplNo(String tplNo) {
        this.tplNo = tplNo;
    }

    public String getConditionDesc() {
        return conditionDesc;
    }

    public void setConditionDesc(String conditionDesc) {
        this.conditionDesc = conditionDesc;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    @Override
    public Date getTms() {
        return tms;
    }

    @Override
    public void setTms(Date tms) {
        this.tms = tms;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    /**
     * 根据功能编号查询模板映射关系
     * @return List<TplMpngRuleDo>
     * @param functionId
     */
    public List<TplMpngRuleDo> getTplMpngRuleByFunctionId(){
        List<TplMpngRuleDo> result = new ArrayList<>();
        if (StringUtils.isBlank(this.getFunctionId()))
            return result;
        result = (List<TplMpngRuleDo>)getListByParam("TplMpngRuleDo.getTplMpngRuleByFunctionId", this);
        return result;
    }

    /**
     * 执行匹配规则表达式
     * @return
     */
    public boolean executeMtchRuleExp(){
        ExpressionParser parser = new SpelExpressionParser();//创建解析器
        Expression expression = parser.parseExpression(this.getConditionDesc());
        boolean b = expression.getValue(this, boolean.class);
        return b;
    }

    /**
     * 根据流程模板id查询规则映射表
     * @return
     */
    public TplMpngRuleDo getTplMpngRuleByTplNo(){
        return (TplMpngRuleDo) getObjectByParam("TplMpngRuleDo.getTplMpngRuleByTplNo", this);
    }
}
