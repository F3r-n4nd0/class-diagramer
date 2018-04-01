import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
      JFrame frame = new JFrame("JFrame Example");

      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());

      JLabel label = new JLabel("This is a label!");

      JButton button = new JButton();
      button.setText("Press me");

      panel.add(label);
      panel.add(button);

      frame.add(panel);
      frame.setSize(300, 300);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setVisible(true);
    }
}
