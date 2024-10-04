package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid{
	private int rows = 20;
	private int cols = 12;
	private Cell[][] sheet;
	
	public Spreadsheet() {
		sheet = new Cell[20][12];
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 12; j++) {
				sheet[i][j] = new EmptyCell();
			}
		}
	}

	@Override
	public String processCommand(String command)
	{		
		String output = "";
		if(command.contains(" = ")){
			String[] splitCommand = command.split(" = ", 2);
			SpreadsheetLocation loc = new SpreadsheetLocation(splitCommand[0]);
			if (command.contains("\"")){
			//cell assignment
				Cell textCell = new TextCell(splitCommand[1]);
				sheet[loc.getRow()][loc.getCol()] = textCell;
			}else if (command.contains("%")) {
				Cell textCell = new PercentCell(splitCommand[1]);
				sheet[loc.getRow()][loc.getCol()] = textCell;
			} else if (command.contains("(")) {
				Cell textCell = new FormulaCell(splitCommand[1], sheet);
				sheet[loc.getRow()][loc.getCol()] = textCell;
			} else {
				Cell textCell = new ValueCell(splitCommand[1]);
				sheet[loc.getRow()][loc.getCol()] = textCell;
			}
			output = getGridText();
		}
		else if (command.toLowerCase().contains("clear")) {
			//either clear or clear cell
			if(command.equalsIgnoreCase("clear")) {
				for(int i = 0; i < rows; i++) {
					for(int j = 0; j < cols; j++) {
						sheet[i][j] = new EmptyCell();
					}
				}
			}
			else {
				String[] splitCommand = command.split(" ", 2);
				SpreadsheetLocation loc = new SpreadsheetLocation(splitCommand[1]);
				sheet[loc.getRow()][loc.getCol()] = new EmptyCell();
			}
			output = getGridText();
			
		} else {
			//cell inspection
			SpreadsheetLocation loc = new SpreadsheetLocation(command);
			output = sheet[loc.getRow()][loc.getCol()].fullCellText();
		}
		
		// TODO Auto-generated method stub
		return output;
	}

	@Override
	public int getRows()
	{
		// TODO Auto-generated method stub
		return rows;
	}

	@Override
	public int getCols()
	{
		// TODO Auto-generated method stub
		return cols;
	}

	@Override
	public Cell getCell(Location loc)
	{
		// TODO Auto-generated method stub
		return sheet[loc.getRow()][loc.getCol()];
	}

	@Override
	public String getGridText(){
		// TODO Auto-generated method stub
		String grid = "";
		for (int i=0; i < 21; i++) {
			if(i>0) {
				grid += i;
				if (i<10) {
					grid += "  |";
				}else {
					grid += " |";
				}
			}
			for (int j=0; j < cols; j++) {
				if (i == 0 && j == 0) {
					grid += "   |";
				}
				if (i== 0) {
					char letter = (char)('A' + j);
					if (j < cols - 1) {
						grid += letter + "         |";
					} else {
						grid += letter + "         |\n";
					}
				} else if (j < cols - 1) {
					grid += sheet[i - 1][j].abbreviatedCellText()+ "|";
				} else {
					grid += sheet[i - 1][j].abbreviatedCellText()+ "|\n";
				}
			}	
		}
		return grid;
	}
}
