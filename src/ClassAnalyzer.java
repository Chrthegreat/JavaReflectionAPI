import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

class ClassAnalyzer {

    // This method does the whole work. It counts the unique methods of the class given,
    //using reflection. It also takes a boolean as a parameter. This boolean is used
    //to specify whether the user want to count the declared or both the declared and
    //inherited methods of the class. If we want both, the arrays returned by the
    //reflection are combined using the combineArrays methods. The size of the result is
    //returned, which represents the number of unique ie not overloaded methods we found.
    public static int countUniqueMethods(Class<?> clazz, boolean includeInherited) {
        Method[] methods;
        if (includeInherited) {
            Method[] declaredMethods = clazz.getDeclaredMethods();
            Method[] inheritedMethods = clazz.getMethods();
            methods = combineArrays(declaredMethods, inheritedMethods, Method.class);
        } else {
            methods = clazz.getDeclaredMethods();
        }
        //I use a Set because it only accepts unique entries. So if a method is found twice
        //the .add wont work.
        Set<String> methodNames = new HashSet<>();
        for (Method method : methods) {
            methodNames.add(method.getName());
        }
        return methodNames.size();
    }

    //Same logic as with the methods
    public static int countUniqueFields(Class<?> clazz, boolean includeInherited) {
        Field[] fields;
        if (includeInherited) {
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

    //Here, I count the number of superclasses for every class given.
    //I count superclasses until I reach null which means that this class
    //has no superclass. Every class will have at least 1 superclass,
    //the java.lang.Object
    public static int countSuperclasses(Class<?> clazz) {
        List<String> superclasses = new ArrayList<>();
        Class<?> tempSupertype = clazz.getSuperclass();
        while (tempSupertype != null) {
            superclasses.add(tempSupertype.getName());
            tempSupertype = tempSupertype.getSuperclass();
        }
        return superclasses.size();
    }
    //***No method for subclasses since, for some reason, there seems to be no method clazz.getSubclass().***


    // Generic method to combine two arrays into one
    private static <T> T[] combineArrays(T[] array1, T[] array2, Class<T> clazz) {
        T[] combined = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, combined, array1.length, array2.length);
        return combined;
    }
}
