package com.company.utils;

import com.company.model.ProfessorModel;
import com.company.model.StudentModel;

import javax.tools.JavaFileManager;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class StudentFile {

    private static final String STUDENTS_PATH = "./students/";

    //It's a static initializer. It's executed when the class is loaded.
    //It's similar to constructor
    static {
        boolean isSuccessful = new File(STUDENTS_PATH).mkdirs();
        System.out.println("Creating " + STUDENTS_PATH + " directory is successful: " + isSuccessful);
    }

    public static File[] getFilesInDirectory() {
        return new File(STUDENTS_PATH).listFiles();
    }
    public static File[] getFilesInDirectoryFilter() {
        File[] files = new File(STUDENTS_PATH).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return false;
            }
        });
        return files;
    }



    /*public static String getFilesName(){
    return new File(STUDENTS_PATH).getName();}*/



    /*public static String fileReader(File file) {
        FileReader fileReader;
        String reader ="";
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()){
                reader += bufferedReader.readLine() + "\n";
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reader;
    }*/

   /* public static void fileWriter(String content) {
        String fileName = getProperFileName(content);
        FileWriter fileWriter;
        File file = new File(fileName);
        try {
            fileWriter = new FileWriter(NOTES_PATH + file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }*/


    /*public static String fileReaderInputStream(File file){
        String reader = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            while (fileInputStream.available() > 0){
                reader += (char) fileInputStream.read();
            }
            fileInputStream.close();
        } catch (FileNotFoundException e){
            //Logger.getLogger(JavaFileManager.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }*/

    /*public static void fileWriterOutputStream(String content) throws FileNotFoundException {
        String fileName = String fileName = ;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            char[] chars = content.toCharArray();
            byte[] bytes = new byte[chars.length];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) chars[i];
            }
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            //Logger.getLogger(JavaFileManager.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void objectFileWriter(StudentModel studentModel){
        String fileName = STUDENTS_PATH + studentModel.getUsername() + studentModel.getDate();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(studentModel);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StudentModel objectFileReader(File file){
        StudentModel studentModel = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            studentModel = (StudentModel) objectInputStream.readObject();
            objectInputStream.close();
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return studentModel;
    }

    public static String streamService(String item) {
        String fileName = new File(STUDENTS_PATH).getName();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.filter(lines -> lines.contains(item)).forEach(System.out::println);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File[] getFilterFiles(ProfessorModel professorModel) {
        File[] files = StudentFile.getFilesInDirectory();
        String filesName = StudentFile.streamService(String.valueOf(professorModel));
        for (File file : files) {
            if (file.getName().equals(filesName)) {
                return file.listFiles();
            }
        }
        return null;
    }
    /*private static String getProperFileName(String content) {
        int loc = content.indexOf("\n");
        if (loc != -1) {
            return content.substring(0, loc);
        }
        if (!content.isEmpty()) {
            return content;
        }
        return System.currentTimeMillis() + "_new file.txt";
    }*/
}


