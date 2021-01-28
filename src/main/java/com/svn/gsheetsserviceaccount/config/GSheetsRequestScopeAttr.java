package com.svn.gsheetsserviceaccount.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GSheetsRequestScopeAttr implements RequestAttributes {

    private final Map<String, Object> requestAttributeMap = new HashMap<>();

    @Override
    public Object getAttribute(String name, int scope) {
        if (scope == RequestAttributes.SCOPE_REQUEST) {
            return this.requestAttributeMap.get(name);
        } else {
            return this.errorMessage("Attribute name: " + name + "; The scope= " + scope + " not supported.");
        }
    }

    @Override
    public void setAttribute(String name, Object value, int scope) {
        if (scope == RequestAttributes.SCOPE_REQUEST) {
            this.requestAttributeMap.put(name, value);
        } else {
            this.errorMessage("Attribute name: " + name + "; The scope= " + scope + " not supported.");
        }
    }

    @Override
    public void removeAttribute(String name, int scope) {
        if (scope == RequestAttributes.SCOPE_REQUEST) {
            this.requestAttributeMap.remove(name);
        } else {
            this.errorMessage("Attribute name: " + name + "; The scope= " + scope + " not supported.");
        }
    }

    @Override
    public String[] getAttributeNames(int scope) {
        if (scope == RequestAttributes.SCOPE_REQUEST) {
            return this.requestAttributeMap.keySet().toArray(new String[0]);
        } else {
            return (String[]) this.errorMessage("The scope= " + scope + " not supported.");
        }
        // return new String[0];
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback, int scope) {
        String message = "In the class GSheetsRequestScopeAttr method " +
                "registerDestructionCallback(String name, Runnable callback, int scope) not supported.";
        this.errorMessage(message);
    }

    @Override
    public Object resolveReference(String key) {
        String message = "In the class GSheetsRequestScopeAttr method resolveReference(String key) not supported.";
        return this.errorMessage(message);
    }

    @Override
    public String getSessionId() {
        String message = "In the class GSheetsRequestScopeAttr method getSessionId() not supported.";
        return this.errorMessage(message).toString();
    }

    @Override
    public Object getSessionMutex() {
        String message = "In the class GSheetsRequestScopeAttr method getSessionMutex() not supported.";
        return this.errorMessage(message);
    }

    private Object errorMessage(String message) {
        log.error(message);
        throw new RuntimeException(message);
    }
}
