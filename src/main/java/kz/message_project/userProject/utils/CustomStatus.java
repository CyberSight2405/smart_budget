package kz.message_project.userProject.utils;


import lombok.Getter;

@Getter
public enum CustomStatus {

    SUCCESS(0, "Success"),
    NOT_FOUND(1, "Not found"),
    EXCEPTION(2, "Exeption");

    private final int code;
    private final String message;

    CustomStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
