import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

class ClassAnalyzer {

    // Combines declared and inherited methods, counts unique methods
    public static int countUniqueMethods(Class<?> clazz, boolean includeInherited) {
        Method[] methods;
        if (includeInherited) {
            // Combine both getDeclaredMethods() and getMethods()
            Method[] declaredMethods = clazz.getDeclaredMethods();
            Method[] inheritedMethods = clazz.getMethods();
            methods = combineArrays(declaredMethods, inheritedMethods, Method.class);
        } else {
            methods = clazz.getDeclaredMethods();
        }

        Set<String> methodNames = new HashSet<>();
        for (Method method : methods) {
            methodNames.add(method.getName());
        }
        return methodNames.size();
    }

    // Combines declared and inherited fields, counts unique fields
    public static int countUniqueFields(Class<?> clazz, boolean includeInherited) {
        Field[] fields;
        if (includeInherited) {
            // Combine both getDeclaredFields() and getFields()
            Field[] declaredFields = clazz.getDeclaredFields();
            Field[] inheritedFields = clazz.getFields();
            fields = combineArrays(declaredFields, inheritedFields, Field.class);
        } else {
            fields = clazz.getDeclaredFields();
        }

        Set<String> fieldNames = new HashSet<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames.size();
    }

    public static int countSuperclasses(Class<?> clazz) {
        List<String> superclasses = new ArrayList<>();
        Class<?> tempSupertype = clazz.getSuperclass();
        while (tempSupertype != null) {
            superclasses.add(tempSupertype.getName());
            tempSupertype = tempSupertype.getSuperclass();
        }
        return superclasses.size();
    }

    // Generic method to combine two arrays into one
    private static <T> T[] combineArrays(T[] array1, T[] array2, Class<T> clazz) {
        T[] combined = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, combined, array1.length, array2.length);
        return combined;
    }
}
