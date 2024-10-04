package textExcel;

public class PercentCell extends RealCell{

	public PercentCell(String input) {
		super(input.substring(0, input.length() -1));
	}
	@Override
	public String abbreviatedCellText() {
		String percent = super.fullCellText();
		percent = percent.substring(0, percent.indexOf(".")) + "%";
		String abbr = percent + "          ";
		return abbr.substring(0,10);
	}
	@Override
	public String fullCellText() {
		double decimal = getDoubleValue();
		decimal = decimal / 100;
		return decimal + "";
	}
	
	@Override
	public Double getDoubleValue() {
		return Double.parseDouble(super.fullCellText());
	}

}
