import javax.swing.*;

public class HelloSwing {
    private static void CreateAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true); // Set a look style for your GUI

        JFrame frame = new JFrame("HelloWorldSwing"); // Create a frame as your Window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close event handler

        JLabel label = new JLabel("Hello, Swing!"); // Create a label
        frame.getContentPane().add(label); // Add the label into the content

        frame.pack(); 
        frame.setVisible(true); // Show the window
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CreateAndShowGUI();
            }
        });
    }
}