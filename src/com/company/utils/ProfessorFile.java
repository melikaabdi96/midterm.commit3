package com.company.utils;

import com.company.model.ProfessorModel;

import javax.tools.JavaFileManager;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * This class holds files of professors
 * @author Melika
 * @since 2020
 */
public class ProfessorFile {

    private static final String PROFFESOR_PATH = "./proffesors/";

    //It's a static initializer. It's executed when the class is loaded.
    static {
        boolean isSuccessful = new File(PROFFESOR_PATH).mkdirs();
        System.out.println("Creating " + PROFFESOR_PATH + " directory is successful: " + isSuccessful);
    }

    /**
     * @return list of files
     */
    public static File[] getFilesInDirectory() {
        return new File(PROFFESOR_PATH).listFiles();
    }




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
        } /*catch (IOException e){
            e.printStackTrace();
        }*/


   /* public static void fileWriterOutputStream(String content) throws FileNotFoundException {
        String fileName = getProperFileName(content);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            char[] chars = content.toCharArray();
            byte[] bytes = new byte[chars.length];
            for (int i = 0; i < bytes.length; i++){
                bytes[i] = (byte) chars[i];
            }
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (FileNotFoundException e){
            //Logger.getLogger(JavaFileManager.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        } /*catch (IOException e){
            e.printStackTrace();
        }*/

    public static void objectFileWriter(ProfessorModel professorModel){
        String fileName = PROFFESOR_PATH + professorModel.getUsername() + professorModel.getDate();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(professorModel);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ProfessorModel objectFileReader(File file){
        ProfessorModel professorModel = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            professorModel = (ProfessorModel) objectInputStream.readObject();
            objectInputStream.close();
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return professorModel;
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


