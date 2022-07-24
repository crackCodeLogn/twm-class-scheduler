package com.vv.personal.twm.scheduler.engine;

import com.vv.personal.twm.artifactory.generated.scheduler.ClassScheduler;

import java.util.Arrays;

/**
 * @author Vivek
 * @since 22/07/22
 */
public class GenerateScheduleCells {

    public ClassScheduler.ClassCells generateClassCells(ClassScheduler.Classes classes) {
        ClassScheduler.ClassCells.Builder builder = ClassScheduler.ClassCells.newBuilder();
        classes.getClassesList().forEach(scheduledClass -> Arrays.stream(scheduledClass.getDays().split(","))
                .forEach(day -> builder.addClassCells(generateScheduledClassCell(scheduledClass, day))));
        builder.setClasses(classes);
        return builder.build();
    }

    private ClassScheduler.ScheduledClassCell generateScheduledClassCell(ClassScheduler.ScheduledClass scheduledClass, String day) {
        return ClassScheduler.ScheduledClassCell.newBuilder()
                .setCode(scheduledClass.getCode())
                .setDay(day)
                .setStartTime(scheduledClass.getStartTime())
                .setEndTime(scheduledClass.getEndTime())
                .build();
    }
}