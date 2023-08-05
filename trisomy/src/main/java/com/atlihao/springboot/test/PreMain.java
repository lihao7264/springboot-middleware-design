package com.atlihao.springboot.test;

import com.atlihao.springboot.test.probe.ProfilingTransformer;

import java.lang.instrument.Instrumentation;

public class PreMain {

    /**
     * JVM 首先尝试在代理类上调用以下方法
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ProfilingTransformer());
    }

    /**
     * 如果代理类没有实现上面的方法，则 JVM 将尝试调用该方法
     *
     * @param agentArgs
     */
    public static void premain(String agentArgs) {
    }

}
