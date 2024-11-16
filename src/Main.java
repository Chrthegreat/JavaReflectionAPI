//By Christoforos Zacharis, 16/11/2024.
//You can find the source code at:
//https://github.com/Chrthegreat/JavaReflectionAPI

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        if (args.length == 0) {          //This is a check for whether any argument is given.
            System.out.println("Please provide a file path as an argument.");
            return;                      //Without an argument this program doesnt do much.
        }

        System.out.println("Program Start");

        String filepath = args[0];       //File with class names
        String outputPath = "output.txt";//Default output path. Store locally with output.txt as name
        int topN = 1;                    //Default numbers of topN if not provided.

        if (args.length >= 2) {          //If 2 arguments are given:
            outputPath = args[1];        //The second argument is the output path.
        }                                //Even if a number is given it will create a file with that number as name.
        if (args.length >= 3) {          //If 3 arguments are given the third one is topN. Here I check for integer.
            try {
                topN = Math.max(Integer.parseInt(args[2]), 1);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for topN. Using default value.");
            }
        }

        List<ClassInfo> classInfoList = new ArrayList<>();  //A list with ClassInfo type that stores ClassInfo references.

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath)); //These lines create objects that read a file
             PrintWriter writer = new PrintWriter(outputPath)) {                   //and write to a file.

            String classname;
            while ((classname = reader.readLine()) != null) {
                try {                                               //In this try I use the class name to call upon
                    Class<?> clazz = Class.forName(classname);      //ClassInfo, so that it will return the number
                    ClassInfo classInfo = new ClassInfo(classname); //of methods and fields of the class.

                    classInfo.processMethods(clazz);                //classInfo object of type ClassInfo now stores in it
                    classInfo.processFields(clazz);                 //all the necessary information
                    classInfo.processSupertypes(clazz);

                    classInfoList.add(classInfo);                   //This specific instance of ClassInfo is added to
                                                                    //the list for further processing.
                } catch (ClassNotFoundException e) {
                    writer.println("Class not found: " + classname);//Unless of course the name of the class is invalid
                    writer.println("-----------");
                }
            }

            // Here, I Sort and display top N classes by unique declared methods
            classInfoList.sort(Comparator.comparingInt(ClassInfo::getDeclaredMethodCount).reversed());
            writer.println("\n1a: Top " + topN + " classes with the most declared methods:");
            classInfoList.stream().limit(topN).forEach(classInfo -> writer.println(
                    classInfo.getClassName() + " with " + classInfo.getDeclaredMethodCount() + " declared methods"));

            // Here, I Sort and display top N classes by all methods
            classInfoList.sort(Comparator.comparingInt(ClassInfo::getAllMethodCount).reversed());
            writer.println("\n1b: Top " + topN + " classes with the most methods (incl. inherited):");
            classInfoList.stream().limit(topN).forEach(classInfo -> writer.println(
                    classInfo.getClassName() + " with " + classInfo.getAllMethodCount() + " total methods"));

            // Here, I Sort and display top N classes by unique declared fields
            classInfoList.sort(Comparator.comparingInt(ClassInfo::getDeclaredFieldCount).reversed());
            writer.println("\n2a: Top " + topN + " classes with the most declared fields:");
            classInfoList.stream().limit(topN).forEach(classInfo -> writer.println(
                    classInfo.getClassName() + " with " + classInfo.getDeclaredFieldCount() + " declared fields"));

            // Here, I Sort and display top N classes by all fields
            classInfoList.sort(Comparator.comparingInt(ClassInfo::getAllFieldCount).reversed());
            writer.println("\n2b: Top " + topN + " classes with the most fields (incl. inherited):");
            classInfoList.stream().limit(topN).forEach(classInfo -> writer.println(
                    classInfo.getClassName() + " with " + classInfo.getAllFieldCount() + " total fields"));

            // Here, I Sort and display top N classes by the number of superclasses
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