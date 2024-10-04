package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel {
	public static void main(String[] args){
		Spreadsheet sprSheet = new Spreadsheet();
		System.out.print(sprSheet.getGridText());
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a command");
		String command = input.nextLine();
		while (!command.equalsIgnoreCase("quit")) {
			System.out.println(sprSheet.processCommand(command));
			System.out.println("Enter a command");
			command = input.nextLine();
		}
		input.close();
	}
}
