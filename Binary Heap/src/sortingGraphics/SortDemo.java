package sortingGraphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public abstract class SortDemo extends JPanel {

    /**
     * This main routine just shows a panel of type QuicksortThreadDemo.
     */

    protected final static int ARRAY_SIZE = 150; // The number of colored bars.

    protected int[] hue = new int[ARRAY_SIZE]; // The array that will be sorted.
    private Color[] palette = new Color[ARRAY_SIZE]; // Colors in spectral
                                                        // order.
    private Display display; // The panel that displays the colored bars.
    private JButton startButton; // The button that starts and stops the demo.

    private Runner runner; // The thread that runs the recursion.

    private volatile boolean running; // Set to true while recursion is running;
                                        // This is set to true as a signal to
                                        // the
                                        // thread to abort.

    /**
     * When the user aborts the recursion before it finishes, an exception of
     * this type is thrown to end the recursion cleanly.
     */
    private class ThreadTerminationException extends RuntimeException {
    }

    /**
     * A subpanel of type Display shows the colored bars that are being sorted.
     * The current pivot, if any, is shown in black. A 3-pixel gray border is
     * left around the bars.
     */
    private class Display extends JPanel {
        Display() {
            setPreferredSize(new Dimension(606, 206));
            setBackground(Color.GRAY);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            double barWidth = (double) (getWidth() - 6) / hue.length;
            int h = getHeight() - 6;
            for (int i = 0; i < hue.length; i++) {
                int x1 = 3 + (int) (i * barWidth + 0.49);
                int x2 = 3 + (int) ((i + 1) * barWidth + 0.49);
                int w = x2 - x1;
                if (hue[i] == -1)
                    g.setColor(Color.BLACK);
                else
                    g.setColor(palette[hue[i]]);
                g.fillRect(x1, 3, w, h);
            }
        }
    }

    /**
     * The constructor sets up the panel, containing the Display and the Start
     * button below it.
     */
    public SortDemo() {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            palette[i] = Color.getHSBColor((i * 230) / (ARRAY_SIZE * 255.0F),
                    1, 1);
            hue[i] = i;
        }
        setLayout(new BorderLayout());
        display = new Display();
        add(display, BorderLayout.CENTER);
        startButton = new JButton("Start");
        JPanel bottom = new JPanel();
        bottom.add(startButton);
        bottom.setBackground(Color.WHITE);
        add(bottom, BorderLayout.SOUTH);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (running)
                    stop();
                else
                    start();
            }
        });
    }

    /**
     * This method is called when the user clicks the Start button, while no
     * thread is running. It starts a new thread and sets the signaling
     * variable, running, to true; Also changes the text on the Start button to
     * "Finish".
     */
    private void start() {
        startButton.setText("Finish");
        runner = new Runner();
        running = true; // Set the signal before starting the thread!
        runner.setDaemon(true);
        runner.start();
    }

    /**
     * This method is called when the user clicks the button while a thread is
     * running. A signal is sent to the thread to terminate, by setting the
     * value of the signaling variable, running, to false.
     */
    private void stop() {

        /*
         * Set the value of the signaling variable to false as a signal to the
         * thread to terminate.
         */

        running = false;

        /*
         * Wake the thread, in case it is sleeping, to get a more immediate
         * reaction to the signal.
         */

        runner.interrupt();

        /*
         * Wait for the thread to stop before setting runner = null. One second
         * should be plenty of time for this to happen, but in case something
         * goes wrong, it's better not to
         */

        try {
            runner.join(1000); // Wait for thread to stop. One second should be
                                // plenty of time.
        } catch (InterruptedException e) {
        }

        runner = null;
    }

    /**
     * This method is called frequently by the thread that is running the
     * recursion, in order to insert delays. It calls the repaint() method of
     * the display to allow the user to see what is going on; the delay will
     * give the system a chance to actually update the display. Since this
     * method is called regularly while the recursion is in progress, it is also
     * used as a convenient place to check the value of the signaling variable,
     * running. If the value of running has been set to false, this method
     * throws an exception of type ThreadTerminatedException. This exception
     * will cause all active levels of the recursion to be terminated. It is
     * caught in the run() method of the thread.
     * 
     * @param millis
     *            The number of milliseconds to sleep.
     */
    protected void delay(int millis) {
        if (!running)
            throw new ThreadTerminationException();
        display.repaint();
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
        if (!running)
            throw new ThreadTerminationException();
    }

    /**
     * The sortStep is a method which is called by sort method in order to do
     * the step of the sort as merge in Merge sort or Partition in QuickSort.
     */
    protected abstract int sortStep(int lo, int hi);

    /**
     * The recursive sort algorithm, for sorting the hue array from
     * positions lo through hi into increasing order. Most of the actual work is
     * done in SortStep().
     */
    protected abstract void sort(int lo, int hi);

    /**
     * This class defines the treads that run the recursive sort algorithm.
     * The thread begins by randomizing the array. It then calls sort() to
     * sort the entire array. If sort() is aborted by a
     * ThreadTerminationExcpetion, which would be caused by the user clicking
     * the Finish button, then the thread will restore the array to sorted order
     * before terminating, so that whether or not the quickSort is aborted, the
     * array ends up sorted.
     */
    private class Runner extends Thread {
        public void run() {
            try {
                for (int i = hue.length - 1; i > 0; i--) { // Randomize array.
                    int r = (int) ((i + 1) * Math.random());
                    int temp = hue[r];
                    hue[r] = hue[i];
                    hue[i] = temp;
                }
                delay(1000); // Wait one second before starting the sort.
                sort(0, hue.length - 1); // Sort the whole array.
            } catch (ThreadTerminationException e) { // User aborted quickSort.
                for (int i = 0; i < hue.length; i++)
                    hue[i] = i;
            } finally {// Make sure running is false and button label is
                        // correct.
                running = false;
                startButton.setText("Start");
                display.repaint();
            }
        }
    }

} // end QuicksortThreadDemo