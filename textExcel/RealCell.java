package textExcel;

public class RealCell implements Cell {
	
	private String input;
	
	public RealCell(String input) {
		this.input = input;
	}
	
	@Override
	public String abbreviatedCellText() {
		double value = getDoubleValue();
		String abbr = value + "          ";
		return abbr.substring(0, 10);
	}

	@Override
	public String fullCellText() {
		return input;
	}
	
	public Double getDoubleValue() {
		return Double.parseDouble(input);
	}
}
