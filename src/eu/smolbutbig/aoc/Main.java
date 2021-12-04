//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package eu.smolbutbig.aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
   public Main() {
   }

   public static void main(String[] args) {
      d4(true);
   }

   public static void d4(boolean partTwo) {
      ArrayList<String> input = parseFileString("d4");
      String numbersCalled = input.get(0);
      input.remove(0);
      input.remove(0);
      ArrayList<BingoBoard> boards = new ArrayList<>();

      for(int i = 0; i < input.size(); i += 6) {
         ArrayList<String> boardInput = new ArrayList<>(input.subList(i, i + 5));
         boards.add(new BingoBoard(boardInput));
      }

      ArrayList<String> toCall = new ArrayList<>(Arrays.asList(numbersCalled.split(",")));
      int winnerIndex = -1;
      int winnerCall = -1;
      int unmarkedSum = 0;
      boolean finalClear = false;

      for (String call : toCall) {
         int index = 0;

         for(BingoBoard board : boards) {
            if (winnerIndex < 0 || partTwo) {
               boolean cleared = board.markNumber(Integer.parseInt(call));
               if (cleared && !finalClear) {
                  winnerIndex = index;
                  winnerCall = Integer.parseInt(call);
                  unmarkedSum = board.getUnmarkedSum();
                  System.out.println("Found a winner!");
                  if (partTwo) {
                     int boardsCleared = 0;
                     for(BingoBoard b : boards) {
                        boolean won = b.checkIfWon();
                        if (won) {
                           ++boardsCleared;
                        }
                     }

                     if (boardsCleared == boards.size()) {
                        System.out.println("Found a final winner!");
                        finalClear = true;
                        System.out.println("Result 2: " + Integer.parseInt(call) * board.getUnmarkedSum());
                     }
                  }

                  System.out.println("Unmarked sum: " + board.getUnmarkedSum());
               }
            }
         }
      }

      System.out.println("Winner index: " + winnerIndex + " with call " + winnerCall);
      if (!partTwo) {
         System.out.println("Result 1: " + winnerCall * unmarkedSum);
      }

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
