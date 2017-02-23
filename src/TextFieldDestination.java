import javax.swing.JTextField;

public class TextFieldDestination extends JTextField {

	public TextFieldDestination(String value, int width) {
		super(width);
		setText(value);
		addFocusListener(new FocusListenerSelectAll());
	}

}
