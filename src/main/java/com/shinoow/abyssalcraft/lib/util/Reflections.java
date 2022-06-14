package com.shinoow.abyssalcraft.lib.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Reflections {

    public static <T> Field[] getStaticFields(Class<T> c) {
        Field[] declaredFields = c.getDeclaredFields();
        List<Field> staticFields = new ArrayList<Field>();
        for (Field field : declaredFields) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                staticFields.add(field);
            }
        }
        return staticFields.toArray(new Field[0]);
    }

}
