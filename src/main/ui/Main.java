package ui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame myFrame = new JFrame();
        myFrame.setSize(500,500);
        myFrame.setTitle("profile");
        myFrame.add(new ProfileUI());

        myFrame.setVisible(true);

    }
}
