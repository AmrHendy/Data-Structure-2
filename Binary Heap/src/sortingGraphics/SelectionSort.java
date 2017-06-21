package sortingGraphics;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class SelectionSort extends SortDemo {

    public static void main(String[] args) {

        JFrame window = new JFrame("Selection Sort");
        SortDemo content = new SelectionSort();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screenSize.width - window.getWidth()) / 2, (screenSize.height - window.getHeight()) / 2);
        window.setVisible(true);
    }

    @Override
    protected int sortStep(int lo, int hi) {
        // TODO Auto-generated method stub
        int smallest = hue[lo];
        int ind = lo;
        int tmp = smallest;

        delay(10);
        for (int i = lo + 1; i <= hi; i++) {
            if (hue[i] < smallest) {
                hue[ind] = smallest;
                smallest = hue[i];
                ind = i;
            }
            tmp = hue[i - 1];
            hue[i - 1] = -1;
            delay(10);
            hue[i - 1] = tmp;

        }
        hue[hi] = tmp;
        hue[ind] = hue[lo];
        hue[lo] = smallest;
        delay(10);
        return 0;
    }

    @Override
    protected void sort(int lo, int hi) {
        // TODO Auto-generated method stub
        if (lo >= hi)
            return;
        sortStep(lo, hi);
        sort(lo + 1, hi);

    }

}
