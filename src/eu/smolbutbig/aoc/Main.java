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
		day6(256);
	}

	public static void day6(int days) {
		ArrayList<String> input = parseFileString("d6");

		String[] fish = input.get(0).split(",");
		long[] fishes = new long[9];

		for (int i = 0; i < fish.length; i++) {
			fishes[Integer.parseInt(fish[i])] += 1;
		}

		for (int day = 1; day <= days; day++) {
			long temporarySix = 0;
			for (int fishBunch = 0; fishBunch < 9; fishBunch++) {
				if (fishBunch == 0) {
					temporarySix = fishes[0];
				} else {
					fishes[fishBunch - 1] = fishes[fishBunch];
				}
			}
			fishes[8] = temporarySix;
			fishes[6] += temporarySix;
		}
		
		long totalFish = 0;
		
		for (int fishBunch = 0; fishBunch < 9; fishBunch++) {
			totalFish += fishes[fishBunch];
		}
		
		log("There are: " + totalFish);

	}

	public static ArrayList<Integer> parseFileInt(String file) {
		Scanner scan = loadFile(file + ".txt");
		ArrayList<Integer> list = new ArrayList<>();

		while (scan.hasNextLine()) {
			int read = scan.nextInt();
			list.add(read);
		}

		return list;
	}

	public static ArrayList<String> parseFileString(String file) {
		Scanner scan = loadFile(file + ".txt");
		ArrayList<String> list = new ArrayList<>();

		while (scan.hasNextLine()) {
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
