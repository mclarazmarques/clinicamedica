package ui;

import javax.swing.*;
import java.awt.*;
import model.Medico;
import service.GerenciadorMedicos;

public class TelaExcluirMedico {

    public TelaExcluirMedico() {

        JFrame frame = new JFrame("Excluir Médico");
        frame.setSize(600, 420);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Excluir Médico", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titulo, BorderLayout.NORTH);

        DefaultListModel<Medico> model = new DefaultListModel<>();
        for (Medico m : GerenciadorMedicos.listar()) {
            model.addElement(m);
        }

        JList<Medico> lista = new JList<>(model);

        JButton btnExcluir = new JButton("Excluir");

        btnExcluir.addActionListener(e -> {
            Medico m = lista.getSelectedValue();
            if (m != null) {
                GerenciadorMedicos.removerPorCpf(m.getCpf());
                model.removeElement(m);
            }
        });

        frame.add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel rodape = new JPanel();
        rodape.add(btnExcluir);
        frame.add(rodape, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
