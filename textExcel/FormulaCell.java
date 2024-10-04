package textExcel;

public class FormulaCell extends RealCell{
	private Cell[][] sheet;
	private String formula;
	public FormulaCell(String input, Cell[][] sheet) {
		super(input);
		this.formula = input;
		this.sheet = sheet;
	}
	
	@Override
	public String abbreviatedCellText() {
		String values = formula;
		double value = getDoubleValue(); 
		values = value + "          ";
		return values.substring(0,10);
	}

	@Override
	public String fullCellText() {
		return formula;
	}
	
	public Double getDoubleValue() {
		String formulas = formula.substring(2,formula.length()-1);
		String[] splitFormula = formulas.split(" ");
		double result = 0;
		double avgnum = 0;
		if (splitFormula[0].equalsIgnoreCase("SUM") || (splitFormula[0].equalsIgnoreCase("AVG"))){
			String[] splitLocs = splitFormula[1].split("-");
			SpreadsheetLocation loc = new SpreadsheetLocation(splitLocs[0]);
			SpreadsheetLocation locs = new SpreadsheetLocation(splitLocs[1]);
			for (int i = loc.getRow(); i <= locs.getRow(); i++) {
				for (int j = loc.getCol(); j <= locs.getCol(); j++ ) {
					result += Double.parseDouble(sheet[i][j].abbreviatedCellText());
					avgnum ++;
				}
			}
			if (splitFormula[0].equalsIgnoreCase("AVG")){
				return result/avgnum;
			}else {
				return result;
			}
			
		}else {
			result = getValue(splitFormula[0]);
			//System.out.println(result);
			for (int i = 1; i<splitFormula.length; i += 2) {
				String operator = splitFormula[i];
				//System.out.println(operator);
				double operand = getValue(splitFormula[i+1]);
				//System.out.println(operand);
				if (operator.equals("+")) {
					result += operand;
				}
				else if (operator.equals("-")) {
					result -= operand;
				}
				else if (operator.equals("*")) {
					result *= operand;
				}
				else if (operator.equals("/")) {
					result /= operand;
				}
			}
		}
		return result;
	}
	

	
	public double getValue(String cell) {
		//System.out.println(cell.charAt(0));
		String cell2 = cell.charAt(0) + "";
		if ((cell2.equalsIgnoreCase("A") || cell2.equalsIgnoreCase("B") || cell2.equalsIgnoreCase("C") || cell2.equalsIgnoreCase("D") || cell2.equalsIgnoreCase("E")) || cell2.equalsIgnoreCase("F") || cell2.equalsIgnoreCase("G") || cell2.equalsIgnoreCase("H") || cell2.equalsIgnoreCase("I") || cell2.equalsIgnoreCase("J") || cell2.equalsIgnoreCase("K") || cell2.equalsIgnoreCase("L")) {
			SpreadsheetLocation loc = new SpreadsheetLocation(cell);
			double num = Double.parseDouble(sheet[loc.getRow()][loc.getCol()].abbreviatedCellText());
			return num;
		}else{
			return Double.parseDouble(cell);
		}
	}	

}
