package dev.courses.springdemo.AssignmentDirectory.otherones;

import dev.courses.springdemo.AssignmentDirectory.otherones.AppLogger;
import dev.courses.springdemo.AssignmentDirectory.otherones.DateTimeService;
import dev.courses.springdemo.AssignmentDirectory.otherones.OutputAggregator;
import dev.courses.springdemo.AssignmentDirectory.otherones.TextFormatter;

public class Injection {

    public static void main(String[] args) {
        //OutputAggregator.printInput("Hello world") method with an input
        OutputAggregator outputAggregator = new OutputAggregator(new AppLogger(), new TextFormatter(new DateTimeService()));
        outputAggregator.printInput("hello");
    }

}
