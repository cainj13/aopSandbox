
package org.semper.reformanda.training.domain.aspect;

import org.apache.log4j.Logger;

public aspect MonitorCallFlow {
    private static final Logger log = Logger.getLogger(MonitorCallFlow.class);

    private int callDepth = 0;

    public static pointcut callInMyNamespace()
            : call(* org.semper.reformanda.training..*(..)) && !within(MonitorCallFlow);

    //before() : callInMyNamespace() {
    //    callDepth = callDepth + 3;
    //    log.trace(getSpaces(callDepth) + thisJoinPoint);
    //}
    //
    //after() : callInMyNamespace() {
    //    callDepth = callDepth - 3;
    //}

    private static String getSpaces(int numberOfSpaces) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numberOfSpaces; i++) {
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }
}