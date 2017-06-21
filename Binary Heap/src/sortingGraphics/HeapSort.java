package sortingGraphics;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class HeapSort extends SortDemo {

    public static void main(String[] args) {

        JFrame window = new JFrame("Heap Sort");
        SortDemo content = new HeapSort();
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
        int largest = lo;
        int value = hue[largest];
        hue[largest] = -1;
        int l = 2 * lo + 1;
        int r = 2 * lo + 2;
        delay(10);
        if (l <= hi && hue[l] > value) {
            hue[largest] = value;
            largest = l;
            value = hue[largest];
            hue[largest] = -1;
            delay(10);

        }
        if (r <= hi && hue[r] > value) {
            hue[largest] = value;
            largest = r;
            value = hue[largest];
            hue[largest] = -1;
            delay(10);
        }
        if (largest != lo) {
            hue[largest] = value;
            swap(lo, largest);
            sortStep(largest, hi);
        } else {
            hue[largest] = value;
        }
        return 0;
    }

    @Override
    protected void sort(int lo, int hi) {
        // TODO Auto-generated method stub
        int n = hi - lo + 1;
        for (int i = n / 2 - 1; i >= 0; i--) {
            sortStep(i, n - 1);
            delay(10);
        }
        for (int i = n - 1; i >= 0; i--) {
            swap(0, i);
            sortStep(0, i - 1);
        }
    }

    private void swap(int first, int second) {
        int tmp = hue[first];
        hue[first] = hue[second];
        hue[second] = tmp;
        delay(10);

    }
}
