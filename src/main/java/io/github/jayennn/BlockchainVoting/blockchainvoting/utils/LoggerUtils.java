package io.github.jayennn.BlockchainVoting.blockchainvoting.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.api.logging.Log;

import java.util.HashMap;
import java.util.Map;

public class LoggerUtils {
    private static final Logger logger = LogManager.getLogger(LoggerUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String SERVICE_NAME = "blockchain-voting";

    public enum LogLevel {
        TRACE, DEBUG, INFO, WARN, ERROR, FATAL
    }

    public static class LogBuilder {
        private final Map<String, Object> logData = new HashMap<>();
        private final LogLevel level;

        public LogBuilder(LogLevel level) {
            this.level = level;
            logData.put("service", SERVICE_NAME);
            logData.put("timestamp", System.currentTimeMillis());
        }

        public LogBuilder with(String key, Object value) {
            if(value != null) {
                logData.put(key, value);
            }
            return this;
        }

        public void log() {
            try {
                String jsonLog = objectMapper.writeValueAsString(logData);
                switch (level) {
                    case TRACE -> logger.trace(jsonLog);
                    case DEBUG -> logger.debug(jsonLog);
                    case INFO -> logger.info(jsonLog);
                    case WARN -> logger.warn(jsonLog);
                    case ERROR -> logger.error(jsonLog);
                    case FATAL -> logger.fatal(jsonLog);
                }
            } catch (JsonProcessingException e) {
                logger.error("Failed to serialize log data: " + logData, e);
            }
        }
    }

    public static void info(String message) {
        new LogBuilder(LogLevel.INFO).
                with("message", message)
                .log();
    }

    public static void error(String error, Throwable t) {
        new LogBuilder(LogLevel.ERROR)
                .with("error", error)
                .with("stacktrace", getStackTrace(t))
                .log();
    }

    private static String getStackTrace(Throwable t) {
        if (t == null) return null;
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : t.getStackTrace()) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}
