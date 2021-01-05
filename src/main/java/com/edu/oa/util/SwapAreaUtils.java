package com.edu.oa.util;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SwapAreaUtils {



    private static SwapAreaManager manager;
    @Resource
    public void setManager(SwapAreaManager manager){
        SwapAreaUtils.manager = manager;
    }
    /**
     * 从交换区中获取 CommonInfo
     * @return CommonInfo
     */
    public static Object getValue(String key){
        SwapAreaMap swapArea = getSwapArea();
        if (swapArea == null){
            return null;
        }
        return swapArea.get(key);
    }
    public static SwapAreaMap getSwapArea(){
        if (manager == null){
            return null;
        }
        SwapAreaMap swapArea = manager.getSwapArea();

        return swapArea;
    }
    public static void setVale(String key, Object value){
        SwapAreaMap swapArea = getSwapArea();
        if (swapArea == null){
             swapArea = manager.initSwapArea();
        }

            swapArea.put(key, value);

    }
    public static CommonInfo getCommonInfo(){
        CommonInfo commonInfo = (CommonInfo) getValue("commonInfo");
        if (commonInfo == null){
            commonInfo = new CommonInfo();
            setVale("commonInfo", commonInfo);
        }
        return commonInfo;
    }
}
