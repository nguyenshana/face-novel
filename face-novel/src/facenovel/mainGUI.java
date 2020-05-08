package facenovel;

import java.awt.*;

import javax.swing.*;


public class mainGUI extends JFrame {

    private JLabel nameLabel;
    private JLabel statusLabel;
//    private JLabel friendsLabel;
    private JButton button;
    private ImageIcon profilepic;
    private JPanel profile;
    private JPanel profilePicPanel;

    public mainGUI(Profile person) {
        setTitle("People");

        setSize(1000, 1000);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        createProfilePanel(person);
        createImagePanel();

        add(profile, BorderLayout.NORTH);
        add(profilePicPanel, BorderLayout.CENTER);

        this.pack();

        setVisible(true);
    }

    private void createProfilePanel(Profile person) {

        nameLabel = new JLabel(person.getName());
        statusLabel = new JLabel(person.getStatus());
//        friendsLabel = new JLabel(person.getFriends());

        profile = new JPanel();

        profile.add(nameLabel);
        profile.add(statusLabel);

    }

    private void createImagePanel() {

        profilepic = new ImageIcon("/Users/shana/Desktop/sun.jpg");
        JLabel profilePicLabel = new JLabel(profilepic);

        Dimension picsize = new Dimension(10, 10);
        profilePicLabel.setMaximumSize(picsize);

        profilePicPanel = new JPanel();

        profilePicPanel.add(profilePicLabel);

    }

    public static void main(String[] args) {

    }
}