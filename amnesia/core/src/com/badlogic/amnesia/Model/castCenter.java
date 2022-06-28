package com.badlogic.amnesia.Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;

public class castCenter {

    public void act(Interactable item, String action, Interactable object) {
        Class<?> cRoot, cInterface;
        Method method;
        try {
            cRoot = Class.forName("com.badlogic.amnesia.Model.Elements.ViewElement.Interactables." + item.getClassName());
            cInterface = Class.forName("com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces." + action + "I");
            if (object == null) {
                method = cRoot.getDeclaredMethod(action);
                method.invoke(cInterface.cast(cRoot.cast(item)));
            }
            else {
                method = cRoot.getDeclaredMethod(action, object.getClass());
                method.invoke(cInterface.cast(cRoot.cast(item)), object);
            }
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | 
                IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void act(Interactable item, String action, int ID) {
        Class<?> cRoot, cInterface;
        Method method;
        try {
            cRoot = Class.forName("com.badlogic.amnesia.Model.Elements.ViewElement.Interactables." + item.getClassName());
            cInterface = Class.forName("com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces." + action + "I");
            if (ID < 0) {
                method = cRoot.getDeclaredMethod(action);
                method.invoke(cInterface.cast(cRoot.cast(item)));
            }
            else {
                method = cRoot.getDeclaredMethod(action, int.class);
                method.invoke(cInterface.cast(cRoot.cast(item)), ID);
            }
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | 
                IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Interactable act(Interactable item, String action) {
        Class<?> cRoot, cInterface;
        Method method;
        Interactable aux = null;
        try {
            cRoot = Class.forName("com.badlogic.amnesia.Model.Elements.ViewElement.Interactables." + item.getClassName());
            cInterface = Class.forName("com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces." + action + "I");
            method = cRoot.getDeclaredMethod(action);
            aux = (Interactable) method.invoke(cInterface.cast(cRoot.cast(item)));
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | 
                IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return aux;
    }
}
