import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public  class studentform extends JFrame {
    private JPanel panel1;
    private JLabel lblNombre;
    private JLabel lblCarrera;
    private JLabel lblSemestre;
    private JLabel lblApellidos;
    private JLabel lblEdad;
    private JLabel lblnControl;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtEdad;
    private JTextField txtnControl;
    private JComboBox cbCarrera;
    private JComboBox cbSemestre;
    private JButton registrarButton;
    private JButton limpiarButton;
    private JButton eliminarButton;
    private JButton editarButton;
    private JButton registrarDocenteButton;
    private JButton registrarAuxButton;
    private JTable tblAlumos;


   studentform() {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Carrera");
        model.addColumn("Semestre");
        model.addColumn("Apellidos");
        model.addColumn("Edad");
        model.addColumn("Control");
        tblAlumos.setModel(model);
        panel1.add(lblNombre);
        panel1.add(txtNombre);
        panel1.add(lblCarrera);
        panel1.add(txtApellidos);
        panel1.add(lblSemestre);
        panel1.add(txtEdad);
        panel1.add(lblnControl);
        panel1.add(txtnControl);
        panel1.add(lblApellidos);
        panel1.add(txtApellidos);
        panel1.add(lblEdad);
        panel1.add(txtEdad);
        panel1.add(lblnControl);
        panel1.add(txtnControl);

   }
}


