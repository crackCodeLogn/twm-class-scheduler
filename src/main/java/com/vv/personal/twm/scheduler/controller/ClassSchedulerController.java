package com.vv.personal.twm.scheduler.controller;

import com.vv.personal.twm.artifactory.generated.scheduler.ClassScheduler;
import com.vv.personal.twm.scheduler.config.ClassSchedulerConfig;
import com.vv.personal.twm.scheduler.engine.GenerateScheduleCells;
import com.vv.personal.twm.scheduler.feign.RenderServiceFeign;
import com.vv.personal.twm.scheduler.reader.JsonReadClassScheduler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Call;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Vivek
 * @since 22/07/22
 */
@Slf4j
@RestController("ClassSchedulerController")
@RequestMapping("/classes")
@AllArgsConstructor
public class ClassSchedulerController {

    private final ClassSchedulerConfig classSchedulerConfig;
    private final RenderServiceFeign renderServiceFeign;

    @SneakyThrows
    @GetMapping("/request")
    public String requestClassSchedule(@RequestParam(defaultValue = "10") int delay) {
        log.info("Reading class schedules from: {}", classSchedulerConfig.getLocation());
        JsonReadClassScheduler jsonReadClassScheduler = new JsonReadClassScheduler(classSchedulerConfig.getLocation());
        ClassScheduler.Classes classes = jsonReadClassScheduler.builderRead().build();
        GenerateScheduleCells generateScheduleCells = new GenerateScheduleCells();
        ClassScheduler.ClassCells generatedClassCells = generateScheduleCells.generateClassCells(classes);
        log.info("Generated class cells: {}", generatedClassCells);

        log.info("Gonna sleep for {}s for eureka registration!", delay);
        Thread.sleep(delay * 1000L);

        if (StringUtils.isNotEmpty(renderServiceFeign.ping())) {
            try {
                String renderOutput = renderServiceFeign.rendClassSchedule(generatedClassCells);
                log.info("{}", renderOutput);
                return renderOutput;
            } catch (Exception e) {
                log.error("Failed to contact render service. ", e);
            }
        } else {
            log.error("Render service is not available!");
        }
        return "FAILED";
    }
}