package ui;

import javax.swing.*;
import java.awt.*;
import model.Paciente;
import service.GerenciadorPacientes;

public class TelaListaPacientes extends JFrame {

    public TelaListaPacientes() {

        setTitle("Pacientes");
        setSize(1024, 695);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Pacientes Cadastrados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        DefaultListModel<Paciente> model = new DefaultListModel<>();
        GerenciadorPacientes.listar().forEach(model::addElement);

        JList<Paciente> lista = new JList<>(model);

        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        btnExcluir.addActionListener(e -> {
            Paciente p = lista.getSelectedValue();
            if (p != null) {
                GerenciadorPacientes.removerPorCpf(p.getCpf());
                model.removeElement(p);
            }
        });

        btnEditar.addActionListener(e -> {
            Paciente p = lista.getSelectedValue();
            if (p != null) {
                new TelaEditarPaciente(p, model);
            }
        });

        add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel rodape = new JPanel();
        rodape.add(btnEditar);
        rodape.add(btnExcluir);
        add(rodape, BorderLayout.SOUTH);

        setVisible(true);
    }
}
