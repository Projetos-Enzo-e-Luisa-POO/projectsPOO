package com.badlogic.amnesia.Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;

public class castCenter {

    public void act(Interactable item, String action) {
        Class<?> cRoot, cInterface;
        Method method;
        try {
            cRoot = Class.forName(item.getClassName());
            cInterface = Class.forName(action + "I");
            method = cRoot.getDeclaredMethod(action);
            method.invoke(cInterface.cast(cRoot.cast(item)));
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | 
                IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
