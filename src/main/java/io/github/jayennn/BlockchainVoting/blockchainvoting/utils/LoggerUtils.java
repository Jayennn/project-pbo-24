package io.github.jayennn.BlockchainVoting.blockchainvoting.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Kelas utilitas untuk logging dalam sistem voting berbasis blockchain.
 *
 * Kelas ini menyediakan metode untuk mencatat log dengan berbagai level (TRACE, DEBUG, INFO, WARN, ERROR, FATAL)
 * dan menyimpan informasi log dalam format JSON.
 */
public class LoggerUtils {
    private static final Logger logger = LogManager.getLogger(LoggerUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String SERVICE_NAME = "blockchain-voting";

    /**
     * Enum untuk level log yang tersedia.
     */
    public enum LogLevel {
        TRACE, DEBUG, INFO, WARN, ERROR, FATAL
    }

    /**
     * Kelas pembangun log untuk membangun data log sebelum mencatatnya.
     */
    public static class LogBuilder {
        private final Map<String, Object> logData = new HashMap<>();
        private final LogLevel level;

        /**
         * Konstruktor untuk LogBuilder.
         *
         * @param level level log yang akan digunakan.
         */
        public LogBuilder(LogLevel level) {
            this.level = level;
            logData.put("service", SERVICE_NAME);
            logData.put("timestamp", System.currentTimeMillis());
        }

        /**
         * Menambahkan data log dengan kunci dan nilai tertentu.
         *
         * @param key kunci untuk data log.
         * @param value nilai untuk data log.
         * @return instance LogBuilder untuk chaining.
         */
        public LogBuilder with(String key, Object value) {
            if(value != null) {
                logData.put(key, value);
            }
            return this;
        }

        /**
         * Mencatat log dengan level yang ditentukan.
         */
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

    /**
     * Mencatat pesan dengan level INFO.
     *
     * @param message pesan yang akan dicatat.
     */
    public static void info(String message) {
        new LogBuilder(LogLevel.INFO).
                with("message", message)
                .log();
    }

    /**
     * Mencatat pesan kesalahan dengan level ERROR.
     *
     * @param error pesan kesalahan yang akan dicatat.
     * @param t throwable yang terkait dengan kesalahan.
     */
    public static void error(String error, Throwable t) {
        new LogBuilder(LogLevel.ERROR)
                .with("error", error)
                .with("stacktrace", getStackTrace(t))
                .log();
    }

    public static LoggerUtils.LogBuilder createLogger(LoggerUtils.LogLevel level) {
        return new LoggerUtils.LogBuilder(level);
    }

    /**
     * Mengambil stack trace dari throwable dalam bentuk string.
     *
     * @param t throwable yang akan diambil stack trace-nya.
     * @return string yang berisi stack trace.
     */
    private static String getStackTrace(Throwable t) {
        if (t == null) return null;
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : t.getStackTrace()) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}
