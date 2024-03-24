package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JEditorPane;
import java.awt.Dimension;

public class AboutProgFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutProgFrame frame = new AboutProgFrame();
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
	public AboutProgFrame() {
		setTitle("Про програму");
		setSize(new Dimension(551, 369));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 220);
		
		JTextPane textPane = new JTextPane();
		getContentPane().add(textPane, BorderLayout.NORTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		editorPane.setText("<h2 anign=\"center\">Про Notepad--</h2>\n"
				+ "<p>\n"
				+ "    Notepad-- - Простий редактор тексту зроблений в рамках лабораторної роботи <br /> <br />\n"
				+ "    Автор: Денис Пятак<br />\n"
				+ "    GitHub Сторінка: <a href=\"https://github.com/Evemedov/JavaLabka3\">https://github.com/Evemedov/JavaLabka3</a><br />\n"
				+ "    Ліцензія: Немає\n"
				+ "</p>");
		contentPane.add(editorPane);
	}

}
