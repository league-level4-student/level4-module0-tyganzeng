package _02_Pixel_Art;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GridInputPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField windowWidthField;
	private JTextField windowHeightField;
	private JTextField rowsField;
	private JTextField colsField;
	private JButton submitButton;
	private JButton loadButton;
			
	PixelArtMaker pam;
	
	public GridInputPanel(PixelArtMaker pam) {
		this.pam = pam;
		
		windowWidthField = new JTextField(5);
		windowHeightField = new JTextField(5);
		rowsField = new JTextField(5);
		colsField = new JTextField(5);
		submitButton = new JButton("Submit");
		loadButton = new JButton("Load");
		
		add(new JLabel("screen width:"));
		add(windowWidthField);
		add(new JLabel("\tscreen height:"));
		add(windowHeightField);
		add(new JLabel("\ttotal rows:"));
		add(rowsField);
		add(new JLabel("\ttotal columns:"));
		add(colsField);
		add(submitButton);
		add(loadButton);
		
		submitButton.addActionListener((e)->submit());
		loadButton.addActionListener((e) -> load());
		
	}
	
	private void load() {
		GridPanel gp = loadGP();
		int w = gp.windowWidth;
		int h = gp.windowHeight;
		int r = gp.rows;
		int c = gp.cols;

		pam.submitGridData(w, h, r, c);
		
	}
	private static GridPanel loadGP() {
		try (FileInputStream fis = new FileInputStream(new File("saved.dat")); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (GridPanel) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// This can occur if the object we read from the file is not
			// an instance of any recognized class
			e.printStackTrace();
			return null;
		}
	}
	
	private void submit() {
		boolean valid = false;
		int w = -1;
		int h = -1;
		int r = -1;
		int c = -1;
		try {
			w = Integer.parseInt(windowWidthField.getText());
			h = Integer.parseInt(windowHeightField.getText());
			r = Integer.parseInt(rowsField.getText());
			c = Integer.parseInt(colsField.getText());
			
			if(w <= 0 || h <= 0 || r <= 0 || c <= 0) {
				invalidateInput();
			}else {
				valid = true;
			}
		}catch(NumberFormatException e) {
			invalidateInput();
		}
		
		if(valid) {
			pam.submitGridData(w, h, r, c);
		}
	}
	
	private void invalidateInput() {
		JOptionPane.showMessageDialog(null, "Be sure all fields are complete with positive numbers.", "ERROR", 0);
	}
	
	
}
