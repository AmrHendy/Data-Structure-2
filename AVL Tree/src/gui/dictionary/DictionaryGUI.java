package gui.dictionary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import dictionary.DictionaryImp;
import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.IDictionary;
import tree.avl.AVLTreeImpl;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.awt.event.ActionEvent;

public class DictionaryGUI {

	private JFrame frame;
	private IDictionary dictionary;
	private String messege;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DictionaryGUI window = new DictionaryGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DictionaryGUI() {
		dictionary = new DictionaryImp();
		messege = new String();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(10, 10, 600, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea inpud_data_textarea = new JTextArea();
		inpud_data_textarea.setBackground(SystemColor.activeCaption);
		inpud_data_textarea.setBounds(10, 53, 294, 38);
		frame.getContentPane().add(inpud_data_textarea);
		
		JLabel inpud_data_label = new JLabel("input data");
		inpud_data_label.setBackground(Color.LIGHT_GRAY);
		inpud_data_label.setBounds(10, 20, 125, 23);
		frame.getContentPane().add(inpud_data_label);
		
		JLabel output_messege_label = new JLabel("output messege");
		output_messege_label.setBackground(Color.LIGHT_GRAY);
		output_messege_label.setBounds(10, 147, 125, 23);
		frame.getContentPane().add(output_messege_label);
		
		JTextArea output_messege_textarea = new JTextArea();
		output_messege_textarea.setBackground(SystemColor.activeCaption);
		output_messege_textarea.setBounds(10, 174, 294, 38);
		frame.getContentPane().add(output_messege_textarea);
		
		JButton insert_button = new JButton("insert word");
		insert_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkInputTextArea(inpud_data_textarea)){
					dictionary.insert(inpud_data_textarea.getText());
					messege = "Successful insertion";
				}
				else{
					messege = "input area is empty";
				}
				sendMessege(messege, output_messege_textarea);
			}
		});
		insert_button.setBackground(SystemColor.activeCaption);
		insert_button.setBounds(409, 55, 130, 30);
		frame.getContentPane().add(insert_button);
		
		JButton delete_button = new JButton("remove word");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				if(checkInputTextArea(inpud_data_textarea)){
					if(dictionary.delete(inpud_data_textarea.getText())){
						messege = "Successfully removed ";
					}
					else{
						messege = "Unsuccessful deletion as this word doesn't exist";
					}
				}
				else{
					messege = "input area is empty";
				}
				sendMessege(messege, output_messege_textarea);
			}
		});
		delete_button.setBackground(SystemColor.activeCaption);
		delete_button.setBounds(409, 100, 130, 30);
		frame.getContentPane().add(delete_button);
		
		JButton search_button = new JButton("Look up");
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkInputTextArea(inpud_data_textarea)){
					if(dictionary.exists(inpud_data_textarea.getText())){
						messege = "this word is existing in the AVL Tree";
					}
					else{
						messege = "this word is not existing in the AVL Tree";
					}
				}
				else{
					messege = "input area is emspty";
				}
				sendMessege(messege, output_messege_textarea);
			}
		});
		search_button.setBackground(SystemColor.activeCaption);
		search_button.setBounds(409, 145, 130, 30);
		frame.getContentPane().add(search_button);
		
		JButton height_button = new JButton("Get size");
		height_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				messege = String.valueOf(dictionary.size());
				sendMessege(messege, output_messege_textarea);
			}
		});
		height_button.setBackground(SystemColor.activeCaption);
		height_button.setBounds(409, 190, 130, 30);
		frame.getContentPane().add(height_button);
		
		
		JButton load_button = new JButton("Load Dictionary");
		load_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
            	try{
            		JFileChooser file_chooser = new JFileChooser();
            		file_chooser.showOpenDialog(frame);
                	File selected_file = file_chooser.getSelectedFile();
                	if(file_chooser.accept(selected_file)){
                		dictionary.load(selected_file);
                		messege = "Successfull Loading the file";
                	}
                	else{
                    	messege = "no file selected";
                	}
    				sendMessege(messege, output_messege_textarea);
            	} catch (Exception e){
            		messege = "Error in loading the file";
    				sendMessege(messege, output_messege_textarea);
            	}
			}
		});
		load_button.setBackground(SystemColor.activeCaption);
		load_button.setBounds(409, 235, 130, 30);
		frame.getContentPane().add(load_button);
		
		
		JButton	sort_button= new JButton("Sort Dictionary");
		sort_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				messege = "Successfull Sorting the dictionary";
				dictionary.sortDictionary(new File("out.txt"));
				sendMessege(messege, output_messege_textarea);
			}
		});
		sort_button.setBackground(SystemColor.activeCaption);
		sort_button.setBounds(409, 280, 130, 30);
		frame.getContentPane().add(sort_button);
	}
	
	private boolean checkInputTextArea(JTextArea textArea){
		System.out.println(textArea.getText());
		return !(textArea.getText().equals(new String("")));
	}
	
	private void sendMessege(String messege , JTextArea messegeTextArea){
		messegeTextArea.setText(messege);
	}
}
