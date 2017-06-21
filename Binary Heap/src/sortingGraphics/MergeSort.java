package sortingGraphics;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MergeSort extends SortDemo {

    
    public static void main(String[] args) {

        JFrame window = new JFrame("Merge Sort");
        SortDemo content = new MergeSort();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screenSize.width - window.getWidth()) / 2, (screenSize.height - window.getHeight()) / 2);
        window.setVisible(true);
    }

    @Override
    protected int sortStep(int lo, int hi) {
        int[] tmp = new int[hi - lo + 1];
        for (int i = 0, j = lo; i < tmp.length; i++, j++) {
            tmp[i] = hue[j];
        }
        int i, j, k;
        i = 0;
        k = lo;
        int mid = (tmp.length - 1) / 2;
        j = mid + 1;

        delay(30);
        while (i <= mid && j <= tmp.length - 1) {
            if (tmp[i] < tmp[j]) {
                delay(10);
                hue[k] = tmp[i];
                i++;
            } else {
                hue[k] = tmp[j];
                j++;
            }
            k++;
            hue[k] = -1;
            delay(10);

        }
        while (i <= mid) {
            hue[k] = -1;
            delay(10);
            hue[k] = tmp[i];
            i++;
            k++;

        }
        while (j <= tmp.length - 1) {
            hue[k] = -1;
            delay(10);
            hue[k] = tmp[j];
            j++;
            k++;
        }
        delay(10);
        return 0;
    }

    @Override
    protected void sort(int lo, int hi) {
        // TODO Auto-generated method stub
        if (hi <= lo)
            return;
        int mid = (lo + hi) / 2;
        sort(lo, mid);
        sort(mid + 1, hi);
        sortStep(lo, hi);

    }

}
