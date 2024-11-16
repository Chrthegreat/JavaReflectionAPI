
//This is a middle class to help make things more clear. Given a class as a
//parameter it calls upon ClassAnalyzer to do its job and stores the results
//to a number of fields.
class ClassInfo {
    private final String className;
    private int declaredMethodCount;
    private int allMethodCount;
    private int declaredFieldCount;
    private int allFieldCount;
    private int supertypeCount;

    // Constructor to initialize with class name
    public ClassInfo(String className) {
        this.className = className;
        this.declaredMethodCount = 0;
        this.allMethodCount = 0;
        this.declaredFieldCount = 0;
        this.allFieldCount = 0;
        this.supertypeCount = 0;
    }

    // Process both declared and all methods using ClassAnalyzer
    public void processMethods(Class<?> clazz) {
        this.declaredMethodCount = ClassAnalyzer.countUniqueMethods(clazz, false);
        this.allMethodCount = ClassAnalyzer.countUniqueMethods(clazz, true);
    }

    // Process both declared and all fields using ClassAnalyzer
    public void processFields(Class<?> clazz) {
        this.declaredFieldCount = ClassAnalyzer.countUniqueFields(clazz, false);
        this.allFieldCount = ClassAnalyzer.countUniqueFields(clazz, true);
    }

    // Calculate number of supertypes up to java.lang.Object
    public void processSupertypes(Class<?> clazz) {
        this.supertypeCount = ClassAnalyzer.countSuperclasses(clazz);
    }

    // Getters for each attribute
    public String getClassName() { return className; }
    public int getDeclaredMethodCount() { return declaredMethodCount; }
    public int getAllMethodCount() { return allMethodCount; }
    public int getDeclaredFieldCount() { return declaredFieldCount; }
    public int getAllFieldCount() { return allFieldCount; }
    public int getSupertypeCount() { return supertypeCount; }
}

