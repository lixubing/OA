package com.edu.oa.util;

import org.springframework.stereotype.Component;

@Component
public class SwapAreaManager {
    private ThreadLocal<SwapAreaMap> threadLocal = new ThreadLocal<SwapAreaMap>();
    public SwapAreaMap getSwapArea(){
        SwapAreaMap swapAreaMap = threadLocal.get();
        return swapAreaMap;
    }
    public void setSwapArea(SwapAreaMap swapArea){
        threadLocal.set(swapArea);
    }
    public SwapAreaMap initSwapArea(){
        SwapAreaMap swapAreaMap = new SwapAreaMap();
        threadLocal.set(swapAreaMap);
        return swapAreaMap;
    }
}
