package com.atlihao.springboot.plugin.ark.runner;

import com.atlihao.springboot.plugin.ark.executor.RunExecutor;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.*;
import com.intellij.execution.impl.DefaultJavaProgramRunner;
import com.intellij.execution.jar.JarApplicationConfiguration;
import com.intellij.execution.remote.RemoteConfiguration;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import org.jetbrains.annotations.NotNull;

public class RunCodeRunner extends DefaultJavaProgramRunner {

    @NotNull
    @Override
    public String getRunnerId() {
        return RunExecutor.RUN_ID;
    }

    @Override
    public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile) {
        return (executorId.equals(RunExecutor.RUN_ID) && (profile instanceof ModuleRunProfile || profile instanceof JarApplicationConfiguration) && !(profile instanceof RemoteConfiguration));
    }

    @Override
    public void execute(@NotNull ExecutionEnvironment env) throws ExecutionException {
        super.execute(env);
    }

    @Override
    protected RunContentDescriptor doExecute(@NotNull RunProfileState state, @NotNull ExecutionEnvironment env) throws ExecutionException {
        JavaParameters parameters = ((JavaCommandLine) state).getJavaParameters();
        // 信息获取
        PsiFile psiFile = env.getDataContext().getData(LangDataKeys.PSI_FILE);
        String packageName = ((PsiJavaFileImpl) psiFile).getPackageName();
        // 添加字节码插装
        ParametersList parametersList = parameters.getVMParametersList();
        parametersList.add("-javaagent:" + this.getClass().getResource("/").getPath().substring(1) + "CodeProbe.jar=" + packageName);
        return super.doExecute(state, env);
    }

}
