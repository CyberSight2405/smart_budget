package kz.message_project.userProject.utils;

import java.util.Collection;

public class CustomResponse<T> {

    private int code;
    private String message;
    private Collection<T> responseList;

    public CustomResponse(Collection<T> responseList, CustomStatus customStatus) {
        this.code = customStatus.getCode();
        this.message = customStatus.getMessage();
        this.responseList = responseList;
    }
}
