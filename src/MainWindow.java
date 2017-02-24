import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {

	public MainWindow(String title, String sourceFilename, String destinationFilename, int mode) {
		
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new GridLayout(0, 1));
		
		int width = sourceFilename.length();
		if (destinationFilename.length() > width) {
			width = destinationFilename.length();
		}
		width = width + 5;
		
		TextFieldSource src = new TextFieldSource(sourceFilename, width);
		TextFieldDestination dst = new TextFieldDestination(destinationFilename, width);
		TextFieldSecret secret = new TextFieldSecret(width);
		
		JPanel row1 = new JPanel(new FlowLayout());
		row1.add(new JLabel("     Source file:", SwingConstants.RIGHT));
		row1.add(src);
		JPanel row2 = new JPanel(new FlowLayout());
		row2.add(new JLabel("Destination file:", SwingConstants.RIGHT));
		row2.add(dst);
		JPanel row3 = new JPanel(new FlowLayout());
		row3.add(new JLabel("          Secret:", SwingConstants.RIGHT));
		row3.add(secret);
		JPanel row4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		row4.add(new ButtonClose());
		row4.add(new ButtonOK(src, dst, secret, mode));
		
		add(row1);
		add(row2);
		add(row3);
		add(row4);
		
		add(new JLabel("Author: igor.delac@gmail.com", SwingConstants.CENTER));
		
		pack();
		
		setLocationRelativeTo(null);
		
	}
}
