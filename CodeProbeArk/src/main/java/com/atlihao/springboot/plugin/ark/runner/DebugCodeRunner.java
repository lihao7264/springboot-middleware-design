package com.atlihao.springboot.plugin.ark.runner;

import com.atlihao.springboot.plugin.ark.executor.DebugExecutor;
import com.intellij.debugger.impl.GenericDebuggerRunner;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.ModuleRunProfile;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.jar.JarApplicationConfiguration;
import com.intellij.execution.remote.RemoteConfiguration;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class DebugCodeRunner extends GenericDebuggerRunner {

    @NotNull
    @Override
    public String getRunnerId() {
        return DebugExecutor.DEBUG_ID;
    }

    @Override
    public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile) {
        return executorId.equals(DebugExecutor.DEBUG_ID) && (profile instanceof ModuleRunProfile || profile instanceof JarApplicationConfiguration)
                && !(profile instanceof RemoteConfiguration);
    }

    @Override
    public void execute(@NotNull ExecutionEnvironment env) throws ExecutionException {
        Project project = env.getProject();
        String name = project.getName();
        super.execute(env);
    }

}
