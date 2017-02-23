import javax.crypto.Cipher;
import javax.swing.JButton;

public class ButtonOK extends JButton {

	public ButtonOK(TextFieldSource src, TextFieldDestination dst, TextFieldSecret secret, int mode) {
		
		if (mode == Cipher.DECRYPT_MODE)
			setText("OK (decrypt)");

		if (mode == Cipher.ENCRYPT_MODE)
			setText("OK (encrypt)");

		addActionListener(new ActionOK(src, dst, secret, mode));

	}
}
