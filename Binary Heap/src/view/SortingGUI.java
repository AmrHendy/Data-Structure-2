package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.sun.java.swing.plaf.windows.WindowsInternalFrameTitlePane.ScalableIconUIResource;

import sort.HeapSort;
import sort.MergeSort;
import sort.QuickSort;
import sort.SelectionSort;
import sort.SortController;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;

public class SortingGUI {

    private JFrame frame;
    private SortController sort_controller;
    private int[] items;
    private JTextArea array_input_textArea ,array_output_textarea_answer;
    private JTable table;
    private long[][] selection_test, merge_test, quick_test, heap_test;
    private boolean file_bool;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SortingGUI window = new SortingGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public SortingGUI() {
        file_bool = false;
    	sort_controller = new SortController();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(20, 20,1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle(new String("Sort Array"));
        frame.setResizable(false);
        JLabel array_input_label = new JLabel("Array input");
        array_input_label.setBounds(10, 11, 84, 14);
        frame.getContentPane().add(array_input_label);
        
        array_input_textArea = new JTextArea();
        array_input_textArea.setBackground(Color.LIGHT_GRAY);
        JScrollPane scroll_pane1 = new JScrollPane(array_input_textArea);
        scroll_pane1.setBounds(10, 30, 600, 40);
        scroll_pane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scroll_pane1);
        
        
        JLabel array_output_label = new JLabel("Array output");
        array_output_label.setBounds(10, 83, 84, 14);
        frame.getContentPane().add(array_output_label);
        
        array_output_textarea_answer = new JTextArea("");
        array_output_textarea_answer.setBackground(Color.LIGHT_GRAY);
        array_output_textarea_answer.setEditable(false);
        JScrollPane scroll_pane3 = new JScrollPane(array_output_textarea_answer);
        scroll_pane3.setBounds(10, 108, 600, 40);
        scroll_pane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scroll_pane3);
        
        JLabel upper_limit_label = new JLabel("Enter Upper Limit");
        upper_limit_label.setBounds(144, 200, 105, 14);
        frame.getContentPane().add(upper_limit_label);
        
        final JTextArea upper_limit_textarea = new JTextArea();
        upper_limit_textarea.setBackground(Color.LIGHT_GRAY);
        upper_limit_textarea.setBounds(144, 217, 105, 25);
        frame.getContentPane().add(upper_limit_textarea);
        
        JButton merge_button = new JButton("Megre Sort");
        merge_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                getItems();
                items = sort_controller.sort(items, new MergeSort());
                printItems(array_output_textarea_answer);
            }
        });
        merge_button.setBounds(700, 30, 120, 20);
        frame.getContentPane().add(merge_button);
        
        JButton quick_button = new JButton("Quick Sort");
        quick_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                getItems();
                items = sort_controller.sort(items, new QuickSort());
                printItems(array_output_textarea_answer);
            }
        });
        quick_button.setBounds(700, 60, 120, 20);
        frame.getContentPane().add(quick_button);
        
        JButton selection_button = new JButton("Selection Sort");
        selection_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                getItems();
                items = sort_controller.sort(items, new SelectionSort());
                printItems(array_output_textarea_answer);
            }
        });
        selection_button.setBounds(700, 90, 120, 20);
        frame.getContentPane().add(selection_button);
        
        JButton heap_sort_button = new JButton("Heap Sort");
        heap_sort_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                getItems();
                items = sort_controller.sort(items, new HeapSort());
                printItems(array_output_textarea_answer);
                System.out.println();
            }
        });
        heap_sort_button.setBounds(700, 121, 120, 20);
        frame.getContentPane().add(heap_sort_button);
        
        JLabel array_size_label = new JLabel("Enter Array Size");
        array_size_label.setBounds(10, 200, 105, 14);
        frame.getContentPane().add(array_size_label);
        
        final JTextArea array_size_textarea = new JTextArea();
        array_size_textarea.setBackground(Color.LIGHT_GRAY);
        array_size_textarea.setBounds(10, 217, 105, 25);
        frame.getContentPane().add(array_size_textarea);
        
        JButton test_random_data = new JButton("Generate Random Data");
        test_random_data.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int n = Integer.parseInt(array_size_textarea.getText());
                int upper = Integer.parseInt(upper_limit_textarea.getText());
                generateItems(n,upper);
                printItems(array_input_textArea);
            }
        });
        test_random_data.setBounds(304, 217, 176, 25);
        frame.getContentPane().add(test_random_data);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 300, 470, 299);
        final DefaultTableModel model = new DefaultTableModel(); 
        model.addColumn("Merge Sort Time");
        model.addColumn("Quick Sort Time");
        model.addColumn("Selection Sort Time");
        model.addColumn("Heap Sort Time");
        table = new JTable(model);
        table.setBounds(10, 480, 700, 150);
        scrollPane.setViewportView(table);
        frame.getContentPane().add(scrollPane);
        
        JButton compare_button = new JButton("run and compare running time");
        compare_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                getItems();
                long start_time,quick_time,merge_time,selection_time,heap_time;
                start_time = System.nanoTime();
                items = sort_controller.sort(items, new QuickSort());
                quick_time = System.nanoTime() - start_time;
                start_time = System.nanoTime();
                items = sort_controller.sort(items, new MergeSort());
                merge_time = System.nanoTime() - start_time;
                start_time = System.nanoTime();
                items = sort_controller.sort(items, new SelectionSort());
                selection_time = System.nanoTime() - start_time;
                start_time = System.nanoTime();
                items = sort_controller.sort(items, new HeapSort());
                heap_time = System.nanoTime() - start_time;
                printItems(array_output_textarea_answer);
                Long[] times = {merge_time , quick_time , selection_time, heap_time};
                model.addRow(times);
            }
        });
        compare_button.setBounds(75, 260, 273, 23);
        frame.getContentPane().add(compare_button);
        
        JButton btnNewButton = new JButton("Plot");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		int temp_size = 100;
        		selection_test = new long[50][2];
        		merge_test = new long[50][2];
        		quick_test = new long[50][2];
        		heap_test = new long[50][2];
        		for(int i=0;i<50;i++){
        			generateItems(temp_size, new Random().nextInt(1000));
        			long start_time,quick_time,merge_time,selection_time,heap_time;
                    start_time = System.nanoTime();
                    items = sort_controller.sort(items, new QuickSort());
                    quick_time = System.nanoTime() - start_time;
                    start_time = System.nanoTime();
                    items = sort_controller.sort(items, new MergeSort());
                    merge_time = System.nanoTime() - start_time;
                    start_time = System.nanoTime();
                    items = sort_controller.sort(items, new SelectionSort());
                    selection_time = System.nanoTime() - start_time;
                    start_time = System.nanoTime();
                    items = sort_controller.sort(items, new HeapSort());
                    heap_time = System.nanoTime() - start_time;
                    selection_test[i][0] = temp_size;
                    selection_test[i][1] = selection_time;
                    merge_test[i][0] = temp_size;
                    merge_test[i][1] = merge_time;
                    quick_test[i][0] = temp_size;
                    quick_test[i][1] = quick_time;
                    heap_test[i][0] = temp_size;
                    heap_test[i][1] = heap_time;
                    temp_size+=100;
        		}
        		Plotting plotting = new Plotting(selection_test,merge_test,quick_test, heap_test);
        		plotting.setVisible(true);
        	}
        });
        btnNewButton.setBounds(500, 218, 100, 23);
        frame.getContentPane().add(btnNewButton);
        
        
        JButton load_button = new JButton("Load");
        load_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	try{
            		JFileChooser file_chooser = new JFileChooser();
                	file_bool = true;
            		file_chooser.showOpenDialog(frame);
                	File selected_file = file_chooser.getSelectedFile();
                	if(file_chooser.accept(selected_file)){
                		Scanner scanner = new Scanner(selected_file);
                		List<Integer> listt = new ArrayList<Integer>();
                		while(scanner.hasNextLine()){
                			listt.add(scanner.nextInt());
                		}
                		int i =0 ;
                		items = new int[listt.size()];
                		for(int elem : listt){
                			items[i] = elem;
                			System.out.println(elem);
                			i++;
                		}		
                	}
            	} catch (Exception e){
            		
            	}
            	
            }
        });
        load_button.setBounds(700, 218, 100, 23);
        frame.getContentPane().add(load_button);
    }
    
    private void getItems(){
        try{
        	if(file_bool)return;
            String[] str = array_input_textArea.getText().split(" ");
            List<Integer> items_list = new ArrayList<Integer>();
            for(String st : str){
                items_list.add(Integer.parseInt(st));
            }
            items = new int[items_list.size()];
            int i=0;
            for(Integer val : items_list){
                this.items[i] = val;
                i++;
            }
        } catch (Exception e){
            
        }
    }
    
    private void printItems(JTextArea text_area){
        String txt = new String("");
        for(int val : this.items){
            txt+=val + " ";
        }
        text_area.setText(txt);
    }
    
    private void generateItems(int array_size , int upper){
        Random random = new Random();
        this.items = new int[array_size];
        for(int i=0;i<array_size;i++){
            this.items[i] = random.nextInt(upper);
        }
    }
}
