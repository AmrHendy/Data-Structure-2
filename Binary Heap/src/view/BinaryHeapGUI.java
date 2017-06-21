package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import binaryheap.MaxBinaryHeapIF;
import binaryheap.MaxBinaryHeapImp;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class BinaryHeapGUI {

    private JFrame frame;
    private MaxBinaryHeapIF max_binary_heap_obj;
    private int[] items;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BinaryHeapGUI window = new BinaryHeapGUI();
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
    public BinaryHeapGUI() {
        items = new int[0];
        max_binary_heap_obj = new MaxBinaryHeapImp();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(20, 20, 450, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle(new String("Binary Heap"));
        frame.setResizable(false);

        final JTextArea input_textarea = new JTextArea();
        input_textarea.setBackground(Color.LIGHT_GRAY);
        JScrollPane scroll_pane1 = new JScrollPane(input_textarea);
        scroll_pane1.setBounds(10, 40, 420, 30);
        scroll_pane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scroll_pane1);

        JLabel input_label = new JLabel("Enter Input");
        input_label.setBounds(10, 20, 70, 14);
        frame.getContentPane().add(input_label);

        JLabel heap_array_label = new JLabel("Heap Array");
        heap_array_label.setBackground(Color.BLUE);
        heap_array_label.setBounds(10, 190, 70, 15);
        frame.getContentPane().add(heap_array_label);

        final JTextArea heap_array_textarea = new JTextArea();
        heap_array_textarea.setBackground(Color.LIGHT_GRAY);
        heap_array_textarea.setEditable(false);
        JScrollPane scroll_pane2 = new JScrollPane(heap_array_textarea);
        scroll_pane2.setBounds(10, 210, 420, 30);
        scroll_pane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scroll_pane2);

        JLabel result_label = new JLabel("Result");
        result_label.setBackground(Color.BLUE);
        result_label.setBounds(10, 255, 70, 15);
        frame.getContentPane().add(result_label);

        final JTextArea result_textarea = new JTextArea();
        result_textarea.setBackground(Color.LIGHT_GRAY);
        result_textarea.setEditable(false);
        JScrollPane scroll_pane3 = new JScrollPane(result_textarea);
        scroll_pane3.setBounds(10, 275, 420, 30);
        scroll_pane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scroll_pane3);

        final JTextArea size_textarea = new JTextArea();
        size_textarea.setBackground(Color.LIGHT_GRAY);
        JScrollPane scroll_pane4 = new JScrollPane(size_textarea);
        scroll_pane4.setBounds(10, 140, 150, 25);
        scroll_pane4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scroll_pane4);

        JLabel error_label = new JLabel("Errors History");
        error_label.setBackground(Color.BLUE);
        error_label.setBounds(10, 317, 70, 15);
        frame.getContentPane().add(error_label);

        final JTextArea error_textarea = new JTextArea();
        error_textarea.setBackground(Color.LIGHT_GRAY);
        error_textarea.setEditable(false);
        JScrollPane scroll_pane5 = new JScrollPane(error_textarea);
        scroll_pane5.setBounds(10, 336, 420, 60);
        scroll_pane5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scroll_pane5);

        JButton insert_button = new JButton("insert");
        insert_button.setBackground(Color.LIGHT_GRAY);
        insert_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    getItems(input_textarea);
                    max_binary_heap_obj.max_heap_insert(items[0]);
                } catch (Exception e) {
                    error_textarea.setText(error_textarea.getText() + e.getMessage() + "\n");
                }
                int[] arr = max_binary_heap_obj.getArray();
                printItems(arr, heap_array_textarea);
            }
        });
        insert_button.setBounds(10, 100, 90, 23);
        frame.getContentPane().add(insert_button);

        JButton extract_button = new JButton("extract");
        extract_button.setBackground(Color.LIGHT_GRAY);
        extract_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int value;
                try {
                    value = max_binary_heap_obj.max_heap_extract();
                    result_textarea.setText(String.valueOf(value));
                } catch (Exception e) {
                    error_textarea.setText(error_textarea.getText() + e.getMessage() + "\n");
                }
                int[] arr = max_binary_heap_obj.getArray();
                printItems(arr, heap_array_textarea);
            }
        });
        extract_button.setBounds(110, 100, 90, 23);
        frame.getContentPane().add(extract_button);

        JButton build_button = new JButton("build");
        build_button.setBackground(Color.LIGHT_GRAY);
        build_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    getItems(input_textarea);
                    max_binary_heap_obj.build_max_heap(items);
                    int[] arr = max_binary_heap_obj.getArray();
                    printItems(arr, heap_array_textarea);
                } catch (Exception e) {
                    error_textarea.setText(error_textarea.getText() + e.getMessage() + "\n");
                }
            }
        });
        build_button.setBounds(210, 100, 90, 23);
        frame.getContentPane().add(build_button);

        JButton reset_button = new JButton("reset size");
        reset_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    getItems(size_textarea);
                    int size = 0;
                    if (items.length != 0) {
                        size = items[0];
                    }
                    max_binary_heap_obj = new MaxBinaryHeapImp(size);
                    int[] arr = max_binary_heap_obj.getArray();
                    printItems(arr, heap_array_textarea);
                } catch (Exception e) {
                    error_textarea.setText(error_textarea.getText() + e.getMessage() + "\n");
                }
            }
        });
        reset_button.setBackground(Color.BLUE);
        reset_button.setBounds(210, 140, 90, 25);
        frame.getContentPane().add(reset_button);
    }

    private void getItems(JTextArea textarea) {
        try {
            String[] str = textarea.getText().split(" ");
            List<Integer> items_list = new ArrayList<Integer>();
            for (String st : str) {
                items_list.add(Integer.parseInt(st));
            }
            items = new int[items_list.size()];
            int i = 0;
            for (Integer val : items_list) {
                this.items[i] = val;
                i++;
            }
        } catch (Exception e) {
            throw new RuntimeException("Input Field in empty");
        }
    }

    private void printItems(int[] arr, JTextArea textarea) {
        String txt = new String("");
        for (int val : arr) {
            txt += val + " ";
        }
        textarea.setText(txt);
    }
}