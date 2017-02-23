import javax.swing.JButton;

public class ButtonClose extends JButton {

	public ButtonClose() {
		setText("Close");
		addActionListener(new ActionClose());
	}
}
