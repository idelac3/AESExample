import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.Cipher;
import javax.swing.JOptionPane;

public class ActionOK implements ActionListener {

	private TextFieldSource src;
	private TextFieldDestination dst;
	private TextFieldSecret secret;
	private int mode;

	public ActionOK(TextFieldSource src, TextFieldDestination dst, TextFieldSecret secret, int mode) {
		this.src = src;
		this.dst = dst;
		this.secret = secret;
		this.mode = mode;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AES aes = new AES();
		try {
			if (mode == Cipher.DECRYPT_MODE)
				aes.decryptFile(src.getText(), dst.getText(), String.valueOf(secret.getPassword()));
			if (mode == Cipher.ENCRYPT_MODE)
				aes.encryptFile(src.getText(), dst.getText(), String.valueOf(secret.getPassword()));

			JOptionPane.showMessageDialog(null, "Done.", "AES", JOptionPane.INFORMATION_MESSAGE);
			
			System.exit(0);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1, "AES", JOptionPane.ERROR_MESSAGE);
		}
	}

}
