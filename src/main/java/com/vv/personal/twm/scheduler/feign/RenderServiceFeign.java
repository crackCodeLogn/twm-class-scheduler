package com.vv.personal.twm.scheduler.feign;

import com.vv.personal.twm.artifactory.generated.scheduler.ClassScheduler;
import com.vv.personal.twm.ping.feign.HealthFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Vivek
 * @since 17/11/20
 */
@FeignClient("twm-rendering-service")
public interface RenderServiceFeign extends HealthFeign {

    @PostMapping("/render/rendClassSchedule")
    String rendClassSchedule(@RequestBody ClassScheduler.ClassCells classCells);
}