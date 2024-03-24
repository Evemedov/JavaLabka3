package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class AboutAutorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutAutorFrame frame = new AboutAutorFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AboutAutorFrame() {
		setResizable(false);
		setTitle("Про автора");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 200);

		JTextPane textPane = new JTextPane();
		getContentPane().add(textPane, BorderLayout.NORTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		editorPane.setText("<h2 anign=\"center\">Про автора</h2>\n"
				+ "<p>\n"
				+ "    Пятак Денис - головний розробник Notepad-- <br /> <br />\n"
				+ "    GitHub Сторінка: <a href=\"https://github.com/Evemedov\">https://github.com/Evemedov</a><br />\n"
				+ "    e-mail: piatak.denys@student.uzhnu.edu.ua\n"
				+ "</p>");
		contentPane.add(editorPane);
	}

}
