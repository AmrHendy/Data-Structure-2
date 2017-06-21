package sortingGraphics;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class QuickSort extends SortDemo {

    
    public static void main(String[] args) {
        JFrame window = new JFrame("Quick Sort");
        SortDemo content = new QuickSort();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screenSize.width - window.getWidth()) / 2, (screenSize.height - window.getHeight()) / 2);
        window.setVisible(true);
    }

    protected int sortStep(int lo, int hi) {
        int pivot = hue[lo]; // Save pivot item.
        hue[lo] = -1; // Mark location lo as empty.
        delay(100);
        while (true) {
            while (hi > lo && hue[hi] > pivot)
                hi--;
            if (hi == lo)
                break;
            hue[lo] = hue[hi]; // Move hue[hi] into empty space.
            hue[hi] = -1; // Mark location hi as empty.
            delay(100);
            while (lo < hi && hue[lo] < pivot)
                lo++;
            if (hi == lo)
                break;
            hue[hi] = hue[lo]; // Move hue[lo] into empty space.
            hue[lo] = -1; // Mark location lo as empty.
            delay(100);
        }
        hue[lo] = pivot; // Move pivot item into the empty space.
        delay(100);
        return lo;
    }

    protected void sort(int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = sortStep(lo, hi);
        sort(lo, mid - 1);
        sort(mid + 1, hi);
    }

}
