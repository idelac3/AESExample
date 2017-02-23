import javax.swing.JPasswordField;

public class TextFieldSecret extends JPasswordField  {

	public TextFieldSecret(int width) {
		super(width);
		addFocusListener(new FocusListenerSelectAll());
	}
}
