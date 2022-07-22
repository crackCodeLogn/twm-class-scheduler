package com.vv.personal.twm.scheduler.reader;

import com.vv.personal.twm.artifactory.generated.expSim.ExpenseSimProto;
import com.vv.personal.twm.artifactory.generated.scheduler.ClassScheduler;

/**
 * @author Vivek
 * @since 12/06/21
 */
public class JsonReadClassScheduler extends JsonReader<ClassScheduler.Classes.Builder> {

    public JsonReadClassScheduler(String jsonLocation) {
        super(jsonLocation);
    }

    @Override
    public ClassScheduler.Classes.Builder builderRead() {
        ClassScheduler.Classes.Builder builder = ClassScheduler.Classes.newBuilder();
        builderRead(builder);
        return builder;
    }
}