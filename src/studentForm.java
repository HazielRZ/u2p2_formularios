import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import assets.RegistroAuxiliar;
import assets.RegistroDocente;


public class studentForm extends JFrame {
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
    private JComboBox<String> cbCarrera;
    private JComboBox<String> cbSemestre;
    private JButton registrarButton;
    private JButton limpiarButton;
    private JButton eliminarButton;
    private JButton editarButton;
    private JButton registrarDocenteButton;
    private JButton registrarAuxButton;
    private JTable tblAlumos;


    public studentForm() {
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel();
        tblAlumos.setModel(model);
        tblAlumos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblAlumos.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                int selectedRow = tblAlumos.getSelectedRow();
                if (selectedRow != -1) {
                    txtNombre.setText(tblAlumos.getValueAt(selectedRow, 0).toString());
                    txtApellidos.setText(tblAlumos.getValueAt(selectedRow, 1).toString());
                    cbCarrera.setSelectedItem(tblAlumos.getValueAt(selectedRow, 2).toString());
                    cbSemestre.setSelectedItem(tblAlumos.getValueAt(selectedRow, 3).toString());
                    txtEdad.setText(tblAlumos.getValueAt(selectedRow, 4).toString());
                    txtnControl.setText(tblAlumos.getValueAt(selectedRow, 5).toString());
                }
            }
        });

        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        model.addColumn("Carrera");
        model.addColumn("Semestre");
        model.addColumn("Edad");
        model.addColumn("Control");
        registrarButton.setAction(new AbstractAction("Registrar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarButtonActionPerformed(e);
            }
        });

        eliminarButton.setAction(new AbstractAction("Eliminar") {
            @Override
            public void actionPerformed (ActionEvent e){
                eliminarButtonActionPerformed(e);
            }
        });
        limpiarButton.setAction(new AbstractAction("Limpiar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarButtonActionPerformed(e);
            }
        });
        this.pack();
        registrarDocenteButton.addActionListener(e -> {
            RegistroDocente verVentana = new RegistroDocente();
            verVentana.setVisible(true);
        });
        registrarAuxButton.addActionListener(e -> {
            RegistroAuxiliar verVentana = new RegistroAuxiliar();
            verVentana.setVisible(true);
        });
        //hola profe
        editarButton.setAction(new AbstractAction("Editar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarButtonActionPerformed(e);
            }
        });
    }

    private void eliminarButtonActionPerformed(ActionEvent e) {
        DefaultTableModel currentModel = (DefaultTableModel) tblAlumos.getModel();
        int row = tblAlumos.getSelectedRow();

        // Corrección: era el >=
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila", "Atención", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres eliminar el registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            currentModel.removeRow(row);
        }
    }

    private void limpiarButtonActionPerformed(ActionEvent e) {

        txtNombre.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        txtnControl.setText("");

        if (cbCarrera.getItemCount() > 0) {
            cbCarrera.setSelectedIndex(0);
        }
        if (cbSemestre.getItemCount() > 0) {
            cbSemestre.setSelectedIndex(0);
        }

        txtNombre.requestFocus();
    }

    private void registrarButtonActionPerformed(ActionEvent evt) {
        //  Extracción de datos
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String control = txtnControl.getText().trim();
        String Edad = txtEdad.getText().trim();
        Object carreraItem = cbCarrera.getSelectedItem();
        String carrera = (carreraItem != null) ? carreraItem.toString() : "";

        Object semestreItem = cbSemestre.getSelectedItem();
        String semestre = (semestreItem != null) ? semestreItem.toString() : "";

        // Validación de completitud de datos críticos
        if (nombre.isEmpty() || apellidos.isEmpty() || control.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar Nombre, Apellidos y Número de Control.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }


        try {
           int edad = Integer.parseInt(txtEdad.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El campo Edad debe ser un valor numérico entero válido.", "Error de Tipo de Dato", JOptionPane.ERROR_MESSAGE);
            return;
        }



        DefaultTableModel currentModel = (DefaultTableModel) tblAlumos.getModel();

        // Validación de integridad
        for (int i = 0; i < currentModel.getRowCount(); i++) {
            Object valorCelda = currentModel.getValueAt(i, 5);
            if (valorCelda != null && valorCelda.toString().equalsIgnoreCase(control)) {
                JOptionPane.showMessageDialog(this, "El Número de Control ya se encuentra registrado.", "Inconsistencia de Datos", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }


        currentModel.addRow(new Object[]{nombre, apellidos, carrera, semestre, Edad, control});


    }
    private void editarButtonActionPerformed(ActionEvent e) {
        int row = tblAlumos.getSelectedRow();

        // 1. Verificación de estado
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla para editar.", "Atención", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 2. Extracción de datos
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String control = txtnControl.getText().trim();
        String edadStr = txtEdad.getText().trim();

        Object carreraItem = cbCarrera.getSelectedItem();
        String carrera = (carreraItem != null) ? carreraItem.toString() : "";

        Object semestreItem = cbSemestre.getSelectedItem();
        String semestre = (semestreItem != null) ? semestreItem.toString() : "";

        if (nombre.isEmpty() || apellidos.isEmpty() || control.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar Nombre, Apellidos y Número de Control.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación de tipo de dato
        try {
            Integer.parseInt(edadStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El campo Edad debe ser un valor numérico entero válido.", "Error de Tipo de Dato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel currentModel = (DefaultTableModel) tblAlumos.getModel();

        //  Validación de integridad (Unicidad del Número de Control)
        for (int i = 0; i < currentModel.getRowCount(); i++) {
            if (i == row) {
                continue;
            }

            Object valorCelda = currentModel.getValueAt(i, 5);
            if (valorCelda != null && valorCelda.toString().equalsIgnoreCase(control)) {
                JOptionPane.showMessageDialog(this, "El Número de Control proporcionado ya pertenece a otro registro.", "Inconsistencia de Datos", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        //  Mutación del estado en el DefaultTableModel
        currentModel.setValueAt(nombre, row, 0);
        currentModel.setValueAt(apellidos, row, 1);
        currentModel.setValueAt(carrera, row, 2);
        currentModel.setValueAt(semestre, row, 3);
        currentModel.setValueAt(edadStr, row, 4);
        currentModel.setValueAt(control, row, 5);

        JOptionPane.showMessageDialog(this, "Registro actualizado correctamente.", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

}