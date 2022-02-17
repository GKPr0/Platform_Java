package cz.tul.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logger1 {

    public static Logger log = LoggerFactory.getLogger(Logger1.class);


    public void log(String message) {
        log.trace("Logger1 message {}", message);
        log.debug("Logger1 message {}", message);
        log.info("Logger1 message {}", message);
        log.warn("Logger1 message {}", message);
        log.error("Logger1 message {}", message);
    }

}
