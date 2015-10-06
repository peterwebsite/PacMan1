import java.awt.*;
import javax.swing.*;

public class GPanel extends JFrame{
	private int duration;
	JFrame window;
	public GPanel(int d){
		duration=d;
		window=new JFrame("Pacman");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void showSplash(){
		JPanel content = (JPanel)getContentPane();
		content.setBackground(Color.white);
		int width = 512;
		int height = 512;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = 0;
		int y = 0;
		setBounds(x,y,width,height);
		JLabel label = new JLabel(new ImageIcon("Splashscreen.jpg"));
		JLabel copyrt = new JLabel("Pacman", JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		content.add(label, BorderLayout.CENTER);
		content.add(copyrt, BorderLayout.SOUTH);
		Color oraRed = new Color(156, 20, 20, 225);
		content.setBorder(BorderFactory.createLineBorder(oraRed, 10));
		setVisible(true);
		try { Thread.sleep(duration);} catch (Exception e){}
		setVisible(false);

	}
	public void showSplashAndExit(){
		showSplash();
		new Pacman();

	}
	public static void main(String[] args){
		GPanel splashs = new GPanel(3500);
		splashs.showSplashAndExit();
	}
}
