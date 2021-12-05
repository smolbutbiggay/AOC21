package eu.smolbutbig.aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
   public Main() {
   }

   public static void main(String[] args) {
      day5(true);
   }

   public static void day5(boolean partTwo) {
	  ArrayList<String> input = parseFileString("d5");
	  int highestX = 0;
	  int highestY = 0;
	   
	  for (String line : input) {
		  String[] split = line.split("->");
		  String[] oneValues = split[0].trim().split(",");
		  String[] twoValues = split[1].trim().split(",");
		  
		  int x1 = Integer.parseInt(oneValues[0]);
		  int y1 = Integer.parseInt(oneValues[1]);
		  
		  int x2 = Integer.parseInt(twoValues[0]);
		  int y2 = Integer.parseInt(twoValues[1]);
		  
		  if (x1 > highestX) {
			  highestX = x1;
		  }
		  if (x2 > highestX) {
			  highestX = x2;
		  }
		  if (y1 > highestY) {
			  highestY = y1;
		  }
		  if (y2 > highestY) {
			  highestY = y2;
		  }
	  }

	  Grid grid = new Grid(highestY, highestX, partTwo);
	  
	  for (String command : input) {
		  grid.parseCommand(command);
	  }
	  
	  System.out.println("Found overlaps: " + grid.findAllOverlaps());
   }

   public static ArrayList<Integer> parseFileInt(String file) {
      Scanner scan = loadFile(file + ".txt");
      ArrayList<Integer> list = new ArrayList<>();

      while(scan.hasNextLine()) {
         int read = scan.nextInt();
         list.add(read);
      }

      return list;
   }

   public static ArrayList<String> parseFileString(String file) {
      Scanner scan = loadFile(file + ".txt");
      ArrayList<String> list = new ArrayList<>();

      while(scan.hasNextLine()) {
         String read = scan.nextLine();
         list.add(read);
      }

      return list;
   }

   public static void log(String s) {
      System.out.println(s);
   }

   public static void log(int s) {
      System.out.println(s);
   }

   public static Scanner loadFile(String name) {
      try {
         File file = new File(name);
         Scanner scanner = new Scanner(file);
         return scanner;
      } catch (FileNotFoundException var3) {
         log("No such file!");
         var3.printStackTrace();
         return null;
      }
   }
}
