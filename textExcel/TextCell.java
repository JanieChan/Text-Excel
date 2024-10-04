package textExcel;

public class TextCell implements Cell{
	private String text;
		
	public TextCell(String text){
		if (text.contains("\"")) {
			this.text = text.substring(1, text.length() - 1);
		}else {
			this.text = text;
		}
	}
	 
	@Override
	public String fullCellText() {
		return "\"" + text + "\"";
	}
	
	@Override 
	public String abbreviatedCellText() {
		String space = text + "          ";
		return space.substring(0,10);
	}
}
