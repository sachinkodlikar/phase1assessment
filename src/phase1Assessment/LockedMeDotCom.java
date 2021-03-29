package phase1Assessment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class LockedMeDotCom {

  static Scanner keyboard;
  static PrintWriter output;
  static String path = "/home/sachinkodlikar1/eclipse-workspace/Assessment_Phase_1/src/";
  static String filename01 = "filename_01.txt";
  static String filename02 = "filename_02.txt";
  static String filename03 = "filename_03.txt";
  static String filename04 = "filename_04.txt";
  static String filename05 = "filename_05.txt";

  public static void main(String[] args) {
    welcomeScreen();
    createFiles(filename01);
    createFiles(filename02);
    createFiles(filename03);
    createFiles(filename04);
    displayExistingFiles();
    selectAction();
  }

  public static void welcomeScreen() {
    System.out.println("=========================================");
    System.out.println("*                                       *");
    System.out.println("*        Welcome To LockedMe.Com        *");
    System.out.println("*      Developed by Sachin Kodlikar     *");
    System.out.println("*                                       *");
    System.out.println("=========================================");
  }

  public static void selectAction() {
    System.out.println('\n' + "=====================================================");
    System.out.println("*                                                   *");
    System.out.println("*       Select the Action you want to perform       *");
    System.out.println("*       Select 1 to Enter Filename to add the file  *");
    System.out.println("*       Select 2 to delete the Entered file         *");
    System.out.println("*       Select 3 to search the Entered file         *");
    System.out.println("*       Press Any Other String to Terminate!        *");
    System.out.println("*                                                   *");
    System.out.println("=====================================================" + '\n');
    try {
      Scanner sc = new Scanner(System.in);
      int value = sc.nextInt();
      switch (value) {
      case 1:
        createNewFile();
        break;
      case 2:
        deleteFile();
        break;
      case 3:
        searchFile();
        break;
      default:
        System.out.println("Your Selection is Invalid! You need to Select 1 or 2 or 3 to Proceed Further.");
        selectAction();
        break;
      }
      sc.close();
    } catch (Exception e) {
      return;
    }
  }

  public static void displayExistingFiles() {
    Set < String > fname = new TreeSet < String > ();
    fname.add(filename04);
    fname.add(filename01);
    fname.add(filename02);
    fname.add(filename03);
    System.out.println('\n' + "Below are the Files in the Directory Path Arranged in an Ascending Order:" + '\n');
    File file = new File(path);
    String[] fileList = file.list();
    Arrays.sort(fileList);
    for (int i = 0; i < fileList.length; i++) {
      System.out.println(fileList[i]);
    }
  }

  public static void createFiles(String filename) {
    try {
      FileOutputStream writer = new FileOutputStream(path + filename);
      writer.write(("").getBytes());
      writer.flush();
      writer.close();
      String data = "\nText in " + filename;
      FileOutputStream out = new FileOutputStream(path + filename, true);
      out.write(data.getBytes());
      out.close();
    } catch (FileNotFoundException e) {
      System.out.println("File Creation Failed!");
    } catch (IOException e) {
      System.out.println("File Write Operation Failed!");
    }
  }

  public static void createNewFile() {
    System.out.println("=============================================");
    System.out.println("*                                           *");
    System.out.println("*     Enter the File Name to be Created     *");
    System.out.println("*                                           *");
    System.out.println("=============================================" + '\n');
    try {
      Scanner sc = new Scanner(System.in);
      FileName fn = new FileName();
      String value = sc.next();
      fn.setFilename(value);
      File file = new File(path + value);
      if (file.createNewFile()) {
        System.out.println("File is created!");
      } else {
        System.out.println("File Already Exists!");
      }
      Thread.sleep(2000);
      displayExistingFiles();
      selectAction();
      sc.close();

      // Write Content
      FileWriter writer = new FileWriter(file);
      writer.write("Test Data to file " + value);
      writer.close();
    } catch (IOException | InterruptedException e) {
      System.out.println("File Creation Failed !");
    }
  }

  public static void deleteFile() throws InterruptedException {
    System.out.println("=============================================");
    System.out.println("*                                           *");
    System.out.println("*     Enter the File Name to be Deleted     *");
    System.out.println("*                                           *");
    System.out.println("=============================================" + '\n');
    Scanner sc = new Scanner(System.in);
    try {
      String value = sc.next();
      FileName fn = new FileName();
      fn.setFilename(value);
      Files.delete(Paths.get(path + value));
      System.out.println(value + " is Deleted!");
    } catch (Exception e) {
      System.out.println("File Not Found!");
    }
    Thread.sleep(2000);
    displayExistingFiles();
    selectAction();
    sc.close();
  }

  private static void searchFile() {
    System.out.println("=============================================");
    System.out.println("*                                           *");
    System.out.println("*     Enter the File Name to be Searched    *");
    System.out.println("*                                           *");
    System.out.println("=============================================" + '\n');
    try {
      Scanner sc = new Scanner(System.in);
      String value = sc.next();
      FileName fn = new FileName();
      fn.setFilename(value);
      if (Files.exists(Paths.get(path + value))) {
        System.out.println(value + " is Available!");
      } else {
        System.out.println(value + " is not Available!");
      }
      Thread.sleep(2000);
      displayExistingFiles();
      selectAction();
      sc.close();
    } catch (Exception e) {
      System.out.println("Error in File Handling");
    }
  }

}