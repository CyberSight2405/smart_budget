package kz.message_project.userProject.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;

import java.util.function.Consumer;

class EmptyObjectProvider<T> implements ObjectProvider<T> {

    public T getObject(Object... args) throws BeansException {
        return null;
    }

    public T getObject() throws BeansException {
        return null;
    }

    public T getIfAvailable() throws BeansException {
        return null;
    }

    public T getIfUnique() throws BeansException {
        return null;
    }

    @Override
    public void forEach(Consumer action) {
        // Do nothing.
    }
}