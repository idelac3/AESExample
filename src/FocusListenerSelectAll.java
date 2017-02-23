import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FocusListenerSelectAll implements FocusListener {

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() instanceof JTextField) {
			JTextField srcObj = (JTextField) e.getSource();
			srcObj.select(0, srcObj.getText().length());
		}
		if (e.getSource() instanceof JPasswordField) {
			JPasswordField srcObj = (JPasswordField) e.getSource();
			srcObj.select(0, srcObj.getPassword().length);
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}


}
