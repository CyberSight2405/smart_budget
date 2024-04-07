package kz.message_project.userProject.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtils {
    private static final String DELIMITER = ",";
    private static final String USERNAME = "username: ";
    private static final String IP = "ip: ";
    private static final String PATH = "path: ";
    private static final String METHOD = "method: ";
    private static final String OPERATION_START = "operation start: ";
    private static final String OPERATION_END = "operation end: ";
    private static final String ERROR = "error: ";

    public static String createLog(String username,
                                   String ipAddress,
                                   String requestPath,
                                   String methodName,
                                   LocalDateTime operationStart,
                                   LocalDateTime operationEnd) {
        return USERNAME + username +
                DELIMITER + IP + ipAddress +
                DELIMITER + PATH + requestPath +
                DELIMITER + METHOD + methodName +
                DELIMITER + OPERATION_START + operationStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")) +
                DELIMITER + OPERATION_END + operationEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss"));
    }

    public static String createLog(String username,
                                   String ipAddress,
                                   String requestPath,
                                   String methodName,
                                   LocalDateTime operationStart,
                                   LocalDateTime operationEnd,
                                   String errorMessage) {
        return USERNAME + username +
                DELIMITER + IP + ipAddress +
                DELIMITER + PATH + requestPath +
                DELIMITER + METHOD + methodName +
                DELIMITER + OPERATION_START + operationStart +
                DELIMITER + OPERATION_END + operationEnd +
                DELIMITER + ERROR + errorMessage;

    }
}

