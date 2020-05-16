package Main;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Accounts.AccountLoginWindow;
import GUI.MainTabbedPane;

public class Driver {

	private static JFrame loginWindow;
	private static JFrame mainWindow;
	private static ImageIcon img = new ImageIcon("res/GradeMasterLogo.png");

	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {

			startLoginWindow();
			
			//startMainWindow("Mr. Coetzee", true);
			
		});
	}

	private static void startLoginWindow() {
		loginWindow = new JFrame("GradeMaster - Login");
		loginWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		loginWindow.setPreferredSize(new Dimension(1366, 768));
		loginWindow.setIconImage(img.getImage());

		loginWindow.setContentPane(new AccountLoginWindow());

		loginWindow.pack();
		loginWindow.setVisible(true);
	}
	
	public static void switchWindows(String teacherName, boolean admin) {
		loginWindow.dispose();
		startMainWindow(teacherName, admin);
	}

	private static void startMainWindow(String teacherName, boolean admin) {
		mainWindow = new JFrame("GradeMaster");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setPreferredSize(new Dimension(1366, 768));
		mainWindow.setIconImage(img.getImage());

		mainWindow.setContentPane(new MainTabbedPane(teacherName, admin));

		mainWindow.pack();
		mainWindow.setVisible(true);
	}

}
