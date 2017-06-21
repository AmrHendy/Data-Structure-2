package gui.tree;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import tree.avl.AVLTreeImpl;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.awt.event.ActionEvent;

public class TreeGUI {

	private JFrame frame;
	private IAVLTree<String> avlTree;
	private String messege;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TreeGUI window = new TreeGUI();
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
	public TreeGUI() {
		avlTree = new AVLTreeImpl<String>();
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
		
		JButton insert_button = new JButton("insert");
		insert_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkInputTextArea(inpud_data_textarea)){
					avlTree.insert(inpud_data_textarea.getText());
					messege = "Successful insertion";
				}
				else{
					messege = "input area is empty";
				}
				sendMessege(messege, output_messege_textarea);
			}
		});
		insert_button.setBackground(SystemColor.activeCaption);
		insert_button.setBounds(409, 55, 90, 30);
		frame.getContentPane().add(insert_button);
		
		JButton delete_button = new JButton("delete");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				if(checkInputTextArea(inpud_data_textarea)){
					if(avlTree.delete(inpud_data_textarea.getText())){
						messege = "Successful deletion";
					}
					else{
						messege = "Unsuccessful deletion as this element doesn't exist";
					}
				}
				else{
					messege = "input area is empty";
				}
				sendMessege(messege, output_messege_textarea);
			}
		});
		delete_button.setBackground(SystemColor.activeCaption);
		delete_button.setBounds(409, 100, 90, 30);
		frame.getContentPane().add(delete_button);
		
		JButton search_button = new JButton("search");
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkInputTextArea(inpud_data_textarea)){
					if(avlTree.search(inpud_data_textarea.getText())){
						messege = "this element is existing in the AVL Tree";
					}
					else{
						messege = "this element is not existing in the AVL Tree";
					}
				}
				else{
					messege = "input area is empty";
				}
				sendMessege(messege, output_messege_textarea);
			}
		});
		search_button.setBackground(SystemColor.activeCaption);
		search_button.setBounds(409, 145, 90, 30);
		frame.getContentPane().add(search_button);
		
		JButton height_button = new JButton("height");
		height_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				messege = String.valueOf(avlTree.height());
				sendMessege(messege, output_messege_textarea);
			}
		});
		height_button.setBackground(SystemColor.activeCaption);
		height_button.setBounds(409, 190, 90, 30);
		frame.getContentPane().add(height_button);
	}
	
	private boolean checkInputTextArea(JTextArea textArea){
		System.out.println(textArea.getText());
		return !(textArea.getText().equals(new String("")));
	}
	
	private void sendMessege(String messege , JTextArea messegeTextArea){
		messegeTextArea.setText(messege);
	}
}
