package gui;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame {

	public JFrame frmNotepad;
	public JEditorPane editorPane;
	
	public String currentPath;
	public boolean isFileSaved;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmNotepad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		isFileSaved = true;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNotepad = new JFrame();
		frmNotepad.setMinimumSize(new Dimension(200, 150));
		frmNotepad.setSize(new Dimension(800, 600));
		frmNotepad.setTitle("Notepad--");
		frmNotepad.setBounds(100, 100, 600, 450);
		frmNotepad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		editorPane = new JEditorPane();
		editorPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(isFileSaved)
					frmNotepad.setTitle(frmNotepad.getTitle() + " *");
				isFileSaved = false;
			}
		});
		editorPane.setFont(new Font("Inconsolata SemiBold", Font.PLAIN, 16));
		editorPane.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
				isFileSaved = false;
				frmNotepad.setTitle(frmNotepad.getTitle() + " *");
			}
			public void inputMethodTextChanged(InputMethodEvent event) {
				isFileSaved = false;
				frmNotepad.setTitle(frmNotepad.getTitle() + " *");
			}
		});
		
		frmNotepad.getContentPane().add(editorPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frmNotepad.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Файл");
		menuBar.add(menu);
		
		JMenuItem menuItem_4 = new JMenuItem("Нова вкладка");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame frame = new MainFrame();
		        frame.frmNotepad.setVisible(true);
			}
		});
		menu.add(menuItem_4);
		
		JMenuItem menuItem = new JMenuItem("Відкрити...");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkIfFileSaved()) {
					openFile();
				}
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Зберегти");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		menu.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Зберегти як...");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(savePathOfFile())
					saveFile();
			}
		});
		menu.add(menuItem_2);
		
		JMenu menu_1 = new JMenu("Довідка");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_5 = new JMenuItem("Про програму");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AboutProgFrame frame = new AboutProgFrame();
				frame.setVisible(true);
			}
		});
		menu_1.add(menuItem_5);
		
		JMenuItem menuItem_3 = new JMenuItem("Про автора");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutAutorFrame frame = new AboutAutorFrame();
				frame.setVisible(true);
			}
		});
		menu_1.add(menuItem_3);
	}

	
	//***********************user functions**************************
	
	public boolean checkIfFileSaved() {
		if(isFileSaved)
			return true;
		
		int result = JOptionPane.showConfirmDialog(frmNotepad, "Зміни не збережені. Зберегти файл?");
		switch(result) {
		case JOptionPane.OK_OPTION:
			saveFile();
			return true;
		case JOptionPane.NO_OPTION:
			
			return true;
		case JOptionPane.CANCEL_OPTION:
			
			return false;
		}
		
		return false;
	}
	
	public void saveFile() {
		if(currentPath == null) {
			if(!savePathOfFile())
				return;
		}
		
		try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(currentPath));
            editorPane.write(writer);
            writer.close();
        } 
		catch (IOException ex) {
			JOptionPane.showMessageDialog(frmNotepad, "Упс! Щось пішло не так");
        }
		
		frmNotepad.setTitle(frmNotepad.getTitle().replace(" *", ""));
		isFileSaved = true;
	}
	
	public String readFile() {
		String res = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(currentPath));
			editorPane.read(reader, 0);
			reader.close();
		}
		catch(IOException ex) {
			JOptionPane.showMessageDialog(frmNotepad, "Упс! Щось пішло не так");
		}
		
		return res;
	}
	
	public void openFile() {
		if(!checkIfFileSaved())
			return;
		
		JFileChooser fileChooser = new JFileChooser(); 
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showDialog(frmNotepad, "Відкрити файл");
		if(result == JFileChooser.APPROVE_OPTION) 
			currentPath = fileChooser.getSelectedFile().getPath();
		
		if(currentPath == "" || currentPath == null)
			return;
		
		readFile();
		
		frmNotepad.setTitle(frmNotepad.getTitle().replace(" *", ""));
		isFileSaved = true;
	}
	
	public boolean savePathOfFile() {
		JFileChooser fileChooser = new JFileChooser(); 
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showSaveDialog(frmNotepad);
		if (result == JFileChooser.APPROVE_OPTION) {
			currentPath = fileChooser.getSelectedFile().getPath();
			return true;
		}
		    
		return false;
	}
}
