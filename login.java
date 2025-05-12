import java.awt.Color;
import javax.swing.*;

public class login {
    public static void main(String[] args) {

        JLabel background = new JLabel(new ImageIcon("foto/21357_pri_boardelections_hero_777797 2.png"));
        background.setBounds(0, 0, 1000, 800);
        background.setLayout(null);

        JLabel logoLabel = new JLabel(new ImageIcon("foto/logo-itk 1.png"));
        logoLabel.setBounds(250, 120, 500, 100); 
        background.add(logoLabel);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(300, 250, 400, 250);
        panel.setBackground(new Color(255, 255, 255, 200));
        background.add(panel); 

        JFrame frame = new JFrame("Login");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel userLabel = new  JLabel("Username:");
        userLabel.setBounds(50, 30, 300, 25);
        panel.add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 50, 300, 30);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 90, 80, 25);
        panel.add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 110, 300, 30);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 155, 100, 30);
        panel.add(loginButton);

        JLabel erorJLabel = new JLabel("Username atau Password salah");
        erorJLabel.setBackground(Color.RED);
        erorJLabel.setBounds(50, 220, 300, 25);
        erorJLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        erorJLabel.setVisible(false);
        panel.add(erorJLabel);

        //simulasi login doang
        loginButton.addActionListener(_ -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("123")) {
                erorJLabel.setVisible(false);
                System.out.println("Login Berhasil");
            } else {
                erorJLabel.setVisible(true);}
        });

        frame.add(panel);
        frame.add(background);
        frame.setVisible(true);

    }
}
