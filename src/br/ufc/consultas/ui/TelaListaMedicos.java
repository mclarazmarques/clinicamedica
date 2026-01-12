package ui;

import javax.swing.*;
import service.GerenciadorMedicos;
import model.Medico;

public class TelaListaMedicos extends JFrame {

    public TelaListaMedicos() {
        setTitle("Lista de Médicos");
        setSize(1024, 695);

        JTextArea area = new JTextArea();
        area.setEditable(false);

        for (Medico m : GerenciadorMedicos.listar()) {
            area.append(m.toString() + "\n");
        }

        add(new JScrollPane(area));
        setVisible(true);
    }
}
