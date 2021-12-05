package eu.smolbutbig.aoc;

public class Grid {
	   private int[][] grid;
	   private int rows;
	   private int columns;
	   private boolean partTwo;

	   public Grid(int rows, int columns, boolean partTwo) {
	      grid = new int[rows+1][columns+1];
	      this.rows = rows;
	      this.columns = columns;
	      this.partTwo = partTwo;
	   }
	   
	   public void parseCommand(String command) {
		  String[] split = command.split("->");
		  String[] oneValues = split[0].trim().split(",");
		  String[] twoValues = split[1].trim().split(",");
		  
		  int x1 = Integer.parseInt(oneValues[0]);
		  int y1 = Integer.parseInt(oneValues[1]);
		  
		  int x2 = Integer.parseInt(twoValues[0]);
		  int y2 = Integer.parseInt(twoValues[1]);
		  
		  parseLine(x1, x2, y1, y2, partTwo);
	   }
	   
	   private void parseLine(int x1, int x2, int y1, int y2, boolean onlyHorizontalAndVertical) {
		   System.out.println("Parsing: " + x1 + "," + x2+ "," + y1+ "," + y2);
		   if (y1 == y2) {
			   markVertical(x1,x2,y1);
		   } else if (x1 == x2) {
			   markHorizontal(y1,y2,x1);
		   } else {
			   if (!onlyHorizontalAndVertical) {
				   markDiagonal(x1,x2,y1,y2);
			   }
		   }
	   }
	   
	   private void markDiagonal(int fromX, int toX, int fromY, int toY) {
		   int steps = Math.abs(fromX-toX) + 1;
		   for (int step = 0; step < steps; step++) {
			   int x = 0; int y = 0;
			   
			   if (fromX < toX) {
				   x = fromX + step;
			   } else {
				   x = fromX - step;
			   }
			   
			   if (fromY < toY) {
				   y = fromY + step;
			   } else {
				   y = fromY - step;
			   }
			   
			   //System.out.println("fromX: " + fromX + " toX: " + toX + " x: "+x);
			   //System.out.println("fromY: " + fromY + " toY: " + toY + " y: "+y);
			   grid[y][x] += 1;
		   }
	   }
	   
	   
	   private void markVertical(int from, int to, int row) {
		   //System.out.println("From: " + from + " to: " + to + " on row: " + row + " with " + Math.abs(from-to) + " steps.");
		   for(int i = 0; i < (Math.abs(from-to) + 1); i++){
			   if (from <= to) {
				   //System.out.println("Trying on row: " + row + " with from+i: " + (from+i));
				   grid[row][from+i] += 1;
			   } else {
				   //System.out.println("Trying on row: " + row + " with from-i: " + (from-i));
				   grid[row][from-i] += 1;
			   }
		   }
	   }
	   
	   private void markHorizontal(int from, int to, int column) {
		   //System.out.println("From: " + from + " to: " + to + " on column: " + column + " with " + Math.abs(from-to) + " steps.");
		   for(int i = 0; i < (Math.abs(from-to) + 1); i++){
			   if (from <= to) {
				   //System.out.println("Trying on column: " + column + " with from+i: " + (from+i));
				   grid[from+i][column] += 1;
			   } else {
				   //System.out.println("Trying on column: " + column + " with from-i: " + (from-i));
				   grid[from-i][column] += 1;
			   }
		   }
	   }
	   
	   public int findAllOverlaps() {
		   int overlaps = 0;
		   for(int i = 0; i <= rows; i++){
			   for(int j = 0; j <= columns; j++){
				   if(grid[i][j] >= 2) {
					   overlaps++;
				   }
			   }
		   }
		   return overlaps;
	   }
}
