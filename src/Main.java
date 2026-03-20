import javax.swing.*;

void main() {

    SwingUtilities.invokeLater(() -> {
        studentForm frame = new studentForm();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    });
}
