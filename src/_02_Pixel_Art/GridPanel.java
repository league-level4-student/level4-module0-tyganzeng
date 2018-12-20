package _02_Pixel_Art;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GridPanel extends JPanel implements MouseListener, Serializable{
	
	private static final String DATA_FILE = "src/_05_Pixel_Art_Save_State/save.dat";
	private static final long serialVersionUID = 1L;
	public int windowWidth;
	public int windowHeight;
	public int pixelWidth;
	public int pixelHeight;
	public int rows;
	public int cols;
	
	public JButton saveButton;
	//1. Create a 2D array of pixels. Do not initialize it yet.
	Pixel[][] pixels;
	private Color color;
	
	public GridPanel(int w, int h, int r, int c) {
		this.windowWidth = w;
		this.windowHeight = h;
		this.rows = r;
		this.cols = c;
		
		this.pixelWidth = windowWidth / cols;
		this.pixelHeight = windowHeight / rows;
		
		color = Color.BLACK;
		
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		
		//2. Initialize the pixel array using the rows and cols variables.
		pixels = new Pixel[rows][cols];
		
		//3. Iterate through the array and initialize each element to a new pixel.
		for(int i = 0; i < pixels.length; i++) {
			for(int j = 0; j < pixels[i].length; j++) {
				pixels[i][j] = new Pixel(i,j); 
			}
		}
		
		saveButton = new JButton("Save");
		add(saveButton);
		
	}
	
	public void addListener() {
		saveButton.addActionListener((e)->save(this));
	}
	public void save(GridPanel gp) {
		try (FileOutputStream fos = new FileOutputStream(new File("saved.dat")); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(gp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Saved!");
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public void clickPixel(int mouseX, int mouseY) {
		//5. Use the mouseX and mouseY variables to change the color
		//   of the pixel that was clicked. *HINT* Use the pixel's dimensions.
		pixels[mouseX/pixelWidth][mouseY/pixelHeight].color = color;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//4. Iterate through the array.
		//   For every pixel in the list, fill in a rectangle using the pixel's color.
		//   Then, use drawRect to add a grid pattern to your display.
		for(int i = 0; i < pixels.length; i++) {
			for(int j = 0; j < pixels[i].length; j++) {
				g.setColor(pixels[i][j].color);
				g.fillRect(i*pixelWidth, j*pixelHeight, pixelWidth, pixelHeight);
				g.setColor(Color.BLACK);
				g.drawRect(i*pixelWidth, j*pixelHeight, pixelWidth, pixelHeight);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
