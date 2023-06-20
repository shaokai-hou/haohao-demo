package com.haohao.demo.springboot.flowable;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@SpringBootTest
public class LeaveTaskTest {


    @Resource
    private TaskService taskService;
    @Resource
    private RuntimeService runtimeService;

    @Test
    void test() {
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee("10")
                .orderByTaskId()
                .desc()
                .list();
        for (Task task : list) {
            log.info("实例 ID：{}", task.getProcessInstanceId());
            log.info("任务 ID：{}", task.getId());
            log.info("任务名称：{}", task.getName());
            log.info("任务处理人：{}", task.getAssignee());
            log.info("任务参数：{}", taskService.getVariables(task.getId()));
            log.info("任务是否挂起：{}", task.isSuspended());
            log.info("=================================");
        }
    }
}
