package cz.tul.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logger2 {

    public static Logger log = LoggerFactory.getLogger(Logger2.class);


    public void log(String message) {
        log.trace("Logger2 message {}", message);
        log.debug("Logger2 message {}", message);
        log.info("Logger2 message {}", message);
        log.warn("Logger2 message {}", message);
        log.error("Logger2 message {}", message);
    }

}
