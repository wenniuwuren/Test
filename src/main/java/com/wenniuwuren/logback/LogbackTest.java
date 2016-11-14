
package com.wenniuwuren.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hzzhuyibin on 2016/7/28.
 */
public class LogbackTest {
    static Logger logger = LoggerFactory.getLogger(LogbackTest.class);
    public static void main (String[] args) {
        logger.debug("hi logback");

        logger.debug("bye logback");

        try {
            int i = 1 / 0;

        } catch (Exception e) {
            logger.error("error:", e);
        }
    }
}

