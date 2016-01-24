package it.mondogrua.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PluggableAdaptor {

    private final Object model;

    private final String action;

    private final Object[] parameters;


    public PluggableAdaptor(Object aModel, String anAction, Object[] someParameters) {
        this.model = aModel;
        this.action = anAction;
        this.parameters = someParameters;
    }

    public Object execute() {
        Method method;
        try {
            method = model.getClass().getMethod(action);
            Object o = method.invoke(model, parameters);
            return o;
        } catch (SecurityException e) {
            // ...
            return null;
        } catch (NoSuchMethodException e) {
            // ...
            return null;
        } catch (IllegalAccessException e) {
            // ...
            return null;
        } catch (IllegalArgumentException e) {
            // ...
            return null;
        } catch (InvocationTargetException e) {
            // ...
            return null;
        }

    }

}
