
package com.wenniuwuren.logback;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * Created by hzzhuyibin on 2016/7/28.
 */
public class SomeLogFilter extends Filter<ILoggingEvent> {


    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {
        if (iLoggingEvent.getMessage().contains("bye")) {
            return FilterReply.DENY;
        }
        return FilterReply.ACCEPT;
    }
}

