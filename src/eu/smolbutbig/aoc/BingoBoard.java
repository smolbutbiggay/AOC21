package eu.smolbutbig.aoc;

import java.util.ArrayList;
import java.util.Arrays;

public class BingoBoard {
   private int[][] board = new int[5][5];
   private boolean[][] marked = new boolean[5][5];

   public BingoBoard(ArrayList<String> input) {
      for(String row : input){
         ArrayList<String> split = new ArrayList<>(Arrays.asList(row.replaceAll("  ", " ").trim().split(" ")));
         for(String number : split) {
        	this.board[input.indexOf(row)][split.indexOf(number)] = Integer.parseInt(number);
         }
      }

   }

   public boolean markNumber(int number) {
      for(int row = 0; row < 5; ++row) {
         for(int column = 0; column < 5; ++column) {
            if (this.board[row][column] == number) {
               this.marked[row][column] = true;
               return this.checkIfWon();
            }
         }
      }

      return false;
   }

   public boolean checkIfWon() {
      for(int row = 0; row < 5; ++row) {
         int markedRowAmount = 0;
         int markedColumnAmount = 0;

         for(int column = 0; column < 5; ++column) {
            if (this.marked[row][column]) {
               ++markedRowAmount;
            }

            if (this.marked[column][row]) {
               ++markedColumnAmount;
            }
         }

         if (markedColumnAmount == 5 || markedRowAmount == 5) {
            return true;
         }
      }

      return false;
   }

   public int getUnmarkedSum() {
      int sum = 0;

      for(int row = 0; row < 5; ++row) {
         for(int column = 0; column < 5; ++column) {
            if (!this.marked[row][column]) {
               sum += this.board[row][column];
            }
         }
      }

      return sum;
   }
}
