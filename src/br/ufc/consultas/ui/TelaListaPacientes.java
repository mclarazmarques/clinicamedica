package ui;

import javax.swing.*;
import service.GerenciadorPacientes;
import model.Paciente;

public class TelaListaPacientes extends JFrame {

    public TelaListaPacientes() {
        setTitle("Lista de Pacientes");
        setSize(1024, 695);

        JTextArea area = new JTextArea();
        area.setEditable(false);

        for (Paciente p : GerenciadorPacientes.listar()) {
            area.append(p.toString() + "\n");
        }

        add(new JScrollPane(area));
        setVisible(true);
    }
}
