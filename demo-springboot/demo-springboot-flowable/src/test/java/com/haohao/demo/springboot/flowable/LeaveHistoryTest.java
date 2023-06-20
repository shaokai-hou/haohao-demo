package com.haohao.demo.springboot.flowable;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@SpringBootTest
public class LeaveHistoryTest {

    @Resource
    private HistoryService historyService;

    @Test
    void queryHistoryProcess() {
        // 历史流程
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId("fd6cd4ae-08fd-11ee-9bd6-d284b73c4cb6")
                .orderByProcessInstanceStartTime().asc().singleResult();
        log.info("流程实例ID:{}", historicProcessInstance.getId());
        log.info("流程定义ID:{}", historicProcessInstance.getProcessDefinitionId());
        log.info("流程开始时间:{}", DateUtil.format(historicProcessInstance.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
        log.info("流程结束时间:{}", DateUtil.format(historicProcessInstance.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    void queryHistoryActivity() {
        // 历史活动流程
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId("fd6cd4ae-08fd-11ee-9bd6-d284b73c4cb6")
                .orderByHistoricActivityInstanceStartTime()
                .asc()
                .list();

        for (HistoricActivityInstance instance : list) {
            log.info("历史活动ID:{}", instance.getId());
            log.info("流程定义ID:{}", instance.getProcessDefinitionId());
            log.info("流程开始时间:{}", DateUtil.format(instance.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
            log.info("流程结束时间:{}", DateUtil.format(instance.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
            log.info("流程处理人:{}", instance.getAssignee());
            log.info("#####################################");
        }
    }

    @Test
    void queryHistoryTask() {
        // 历史任务流程
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId("fd6cd4ae-08fd-11ee-9bd6-d284b73c4cb6")
                .orderByHistoricTaskInstanceStartTime()
                .asc()
                .list();

        for (HistoricTaskInstance instance : list) {
            log.info("历史活动ID:{}", instance.getId());
            log.info("流程定义ID:{}", instance.getProcessDefinitionId());
            log.info("流程执行实例ID:{}", instance.getExecutionId());
            log.info("流程结束时间:{}", DateUtil.format(instance.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
            log.info("流程处理人:{}", instance.getAssignee());
            log.info("#####################################");
        }
    }

    @Test
    void queryHistoryVariables() {
        // 历史变量、
        List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery()
                .processInstanceId("fd6cd4ae-08fd-11ee-9bd6-d284b73c4cb6")
                .list();

        for (HistoricVariableInstance instance : list) {
            log.info("流程变量ID:{}", instance.getId());
            log.info("流程实例ID:{}", instance.getProcessInstanceId());
            log.info("变量名称:{}", instance.getVariableName());
            log.info("变量的值:{}", instance.getValue());
            log.info("变量创建时间:{}", DateUtil.format(instance.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            log.info("#####################################");
        }
    }
}
