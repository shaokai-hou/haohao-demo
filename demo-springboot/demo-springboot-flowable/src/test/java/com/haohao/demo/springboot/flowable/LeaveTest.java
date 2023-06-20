package com.haohao.demo.springboot.flowable;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class LeaveTest {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    String people = "1000";
    String group = "100";
    String manage = "10";

    @Test
    void start() {
        // ID:1000 name:张三提交请假申请
        HashMap<String, Object> map = new HashMap<>();
        map.put("leave_task", people);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave", "123456", map);
        runtimeService.setVariable(processInstance.getId(), "name", "张三");
        log.info("创建流程 processId：{}", processInstance.getId());
    }

    @Test
    void submitGroup() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(people).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
            Map<String, Object> map = new HashMap<>();
            //提交给组长的时候，需要指定组长的 id
            map.put("leave_group", group);
            taskService.complete(task.getId(), map);
        }
    }

    @Test
    void group() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(group).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("组长 {} 在审批 {} 任务", task.getAssignee(), task.getId());
            Map<String, Object> map = new HashMap<>();
            //组长审批的时候，如果是同意，需要指定经理的 id
            map.put("leave_manager", manage);
            map.put("comment", "通过");
            taskService.complete(task.getId(), map);
        }
    }

    @Test
    void submitManager() {

    }
}