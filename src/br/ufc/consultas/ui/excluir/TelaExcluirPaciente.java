package ui.excluir;

import javax.swing.*;
import java.awt.*;
import model.Paciente;
import service.GerenciadorPacientes;

public class TelaExcluirPaciente {

    public TelaExcluirPaciente() {

        JFrame frame = new JFrame("Excluir Paciente");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Excluir Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titulo, BorderLayout.NORTH);

        DefaultListModel<Paciente> model = new DefaultListModel<>();
        for (Paciente p : GerenciadorPacientes.listar()) {
            model.addElement(p);
        }

        JList<Paciente> lista = new JList<>(model);

        JButton btnExcluir = new JButton("Excluir");

        btnExcluir.addActionListener(e -> {
            Paciente p = lista.getSelectedValue();
            if (p != null) {
                GerenciadorPacientes.remover(p);
                model.removeElement(p);
            }
        });

        frame.add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel rodape = new JPanel();
        rodape.add(btnExcluir);
        frame.add(rodape, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
