import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a file path as an argument.");
            return;
        }

        System.out.println("Program Start");

        String filepath = args[0];
        String outputPath = "output.txt";
        int topN = 1;

        if (args.length >= 2) {
            outputPath = args[1];
        }
        if (args.length >= 3) {
            try {
                topN = Math.max(Integer.parseInt(args[2]), 1);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for topN. Using default value.");
            }
        }

        List<ClassInfo> classInfoList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath));
             PrintWriter writer = new PrintWriter(outputPath)) {

            String classname;
            while ((classname = reader.readLine()) != null) {
                try {
                    Class<?> clazz = Class.forName(classname);
                    ClassInfo classInfo = new ClassInfo(classname);

                    // Process declared and all methods/fields
                    classInfo.processMethods(clazz);
                    classInfo.processFields(clazz);
                    classInfo.processSupertypes(clazz);

                    classInfoList.add(classInfo);

                    // Write class information to output file
                    //writer.println("Class name: " + classInfo.getClassName());
                    //writer.println("Number of supertypes: " + classInfo.getSupertypeCount());
                    //writer.println("Declared methods: " + classInfo.getDeclaredMethodCount());
                    //writer.println("All methods (incl. inherited): " + classInfo.getAllMethodCount());
                    //writer.println("Declared fields: " + classInfo.getDeclaredFieldCount());
                    //writer.println("All fields (incl. inherited): " + classInfo.getAllFieldCount());
                    //writer.println("-----------");

                } catch (ClassNotFoundException e) {
                    writer.println("Class not found: " + classname);
                    writer.println("-----------");
                }
            }

            // Sort and display top N classes by unique declared methods
            classInfoList.sort(Comparator.comparingInt(ClassInfo::getDeclaredMethodCount).reversed());
            writer.println("\n1a: Top " + topN + " classes with the most declared methods:");
            classInfoList.stream().limit(topN).forEach(classInfo -> writer.println(
                    classInfo.getClassName() + " with " + classInfo.getDeclaredMethodCount() + " declared methods"));

            // Sort and display top N classes by all methods
            classInfoList.sort(Comparator.comparingInt(ClassInfo::getAllMethodCount).reversed());
            writer.println("\n1b: Top " + topN + " classes with the most methods (incl. inherited):");
            classInfoList.stream().limit(topN).forEach(classInfo -> writer.println(
                    classInfo.getClassName() + " with " + classInfo.getAllMethodCount() + " total methods"));

            // Sort and display top N classes by unique declared fields
            classInfoList.sort(Comparator.comparingInt(ClassInfo::getDeclaredFieldCount).reversed());
            writer.println("\n2a: Top " + topN + " classes with the most declared fields:");
            classInfoList.stream().limit(topN).forEach(classInfo -> writer.println(
                    classInfo.getClassName() + " with " + classInfo.getDeclaredFieldCount() + " declared fields"));

            // Sort and display top N classes by all fields
            classInfoList.sort(Comparator.comparingInt(ClassInfo::getAllFieldCount).reversed());
            writer.println("\n2b: Top " + topN + " classes with the most fields (incl. inherited):");
            classInfoList.stream().limit(topN).forEach(classInfo -> writer.println(
                    classInfo.getClassName() + " with " + classInfo.getAllFieldCount() + " total fields"));


            classInfoList.sort(Comparator.comparingInt(ClassInfo::getSupertypeCount).reversed());
            writer.println("\n3: Top " + topN + " classes with the most supertypes:");
            classInfoList.stream().limit(topN).forEach(classInfo -> writer.println(
                    classInfo.getClassName() + " has " + classInfo.getSupertypeCount() + " supertypes"));

            System.out.println("The end. Output written to " + outputPath);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}