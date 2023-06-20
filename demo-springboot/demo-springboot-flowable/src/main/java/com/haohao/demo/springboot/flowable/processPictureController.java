package com.haohao.demo.springboot.flowable;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IoUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class processPictureController {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private ProcessEngine processEngine;

    @SneakyThrows
    @GetMapping("/pic/{processId}")
    public void processPicture(@PathVariable("processId") String processId, HttpServletResponse response) {
        log.info("查看流程图 实例ID：{}", processId);
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
        List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(processId).list();
        executions.forEach(item -> activityIds.addAll(runtimeService.getActiveActivityIds(item.getId())));

        // 生成流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        ProcessEngineConfiguration configuration = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator generator = configuration.getProcessDiagramGenerator();
        InputStream inputStream = generator.generateDiagram(bpmnModel, "png", activityIds, flows, configuration.getActivityFontName(), configuration.getLabelFontName(), configuration.getAnnotationFontName(), configuration.getClassLoader(), 1.0, false);

        FastByteArrayOutputStream read = IoUtil.read(inputStream);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(read.toByteArray());
    }
}
