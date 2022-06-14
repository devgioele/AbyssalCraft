package com.shinoow.abyssalcraft.lib.util;

import com.shinoow.abyssalcraft.api.item.ACItems;
import net.minecraft.block.Block;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reflections {

    public static Field[] getStaticFields(Class<?> c) {
        Field[] declaredFields = c.getDeclaredFields();
        List<Field> staticFields = new ArrayList<>();
        for (Field field : declaredFields) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                staticFields.add(field);
            }
        }
        return staticFields.toArray(new Field[0]);
    }

}
