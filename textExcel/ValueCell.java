package textExcel;

public class ValueCell extends RealCell{ 
	
	private String input;
	
	public ValueCell(String input) {
		super(input);
		this.input = input;
	}
	
	@Override
	public String abbreviatedCellText() {
		String values = input;
		double value = getDoubleValue();
		if(!values.contains(".")) {
			values = values + ".0";
		} 
		values = value + "          ";
		return values.substring(0,10);
	}
	
	@Override
	public String fullCellText() {
		return input;
		
	}
	@Override
	public Double getDoubleValue() {
		return Double.parseDouble(input);
	}
}
