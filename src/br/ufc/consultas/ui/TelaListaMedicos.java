package ui;

import javax.swing.*;
import java.awt.*;
import model.Medico;
import service.GerenciadorMedicos;

public class TelaListaMedicos extends JFrame {

    public TelaListaMedicos() {
        setTitle("Médicos");
        setSize(1024, 695);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Médicos Cadastrados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        DefaultListModel<Medico> model = new DefaultListModel<>();
        GerenciadorMedicos.listar().forEach(model::addElement);

        JList<Medico> lista = new JList<>(model);

        JButton btnExcluir = new JButton("Excluir");
        JButton btnEditar = new JButton("Editar");

        btnExcluir.addActionListener(e -> {
            Medico m = lista.getSelectedValue();
            if (m != null) {
                GerenciadorMedicos.removerPorCpf(m.getCpf());
                model.removeElement(m);
            }
        });

        btnEditar.addActionListener(e -> {
            Medico m = lista.getSelectedValue();
            if (m != null) {
                new TelaEditarMedico(m, model);
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
