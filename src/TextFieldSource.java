import javax.swing.JTextField;

public class TextFieldSource extends JTextField  {

	public TextFieldSource(String value, int width) {
		super(width);
		setText(value);
		addFocusListener(new FocusListenerSelectAll());
	}

}
