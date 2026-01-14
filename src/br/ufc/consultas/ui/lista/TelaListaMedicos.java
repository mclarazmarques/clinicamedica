package ui.lista;

import javax.swing.*;
import java.awt.*;
import model.Medico;
import service.GerenciadorMedicos;
import ui.editar.TelaEditarMedico;

public class TelaListaMedicos extends JFrame {

    public TelaListaMedicos() {
        setTitle("Médicos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // escritp
        JLabel titulo = new JLabel("Médicos Cadastrados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // Lista
        DefaultListModel<Medico> model = new DefaultListModel<>();
        GerenciadorMedicos.listar().forEach(model::addElement);

        JList<Medico> lista = new JList<>(model);

        // Botao
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(0, 123, 255));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setFocusPainted(false);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(0, 123, 255));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFocusPainted(false);

        // Ação Excluir confirma e avisa se n der certo
        btnExcluir.addActionListener(e -> {
            Medico m = lista.getSelectedValue();

            if (m != null) {

                int opcao = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir este médico?",
                    "Confirmar exclusão",
                    JOptionPane.YES_NO_OPTION
                );

                if (opcao == JOptionPane.YES_OPTION) {
                    GerenciadorMedicos.removerPorCrm(m.getCrm());
                    model.removeElement(m);

                    JOptionPane.showMessageDialog(
                        this,
                        "Médico excluído com sucesso!",
                        "Exclusão",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }

            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Selecione um médico!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        });

        // Ação Editar avisa se n prencher
        btnEditar.addActionListener(e -> {
            Medico m = lista.getSelectedValue();

            if (m == null) {
                JOptionPane.showMessageDialog(
                    this,
                    "Selecione um médico para editar!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            new TelaEditarMedico(m, model);
        });

        // scroll da lista
        add(new JScrollPane(lista), BorderLayout.CENTER);

        //rodape
        JPanel rodape = new JPanel();
        rodape.add(btnEditar);
        rodape.add(btnExcluir);
        add(rodape, BorderLayout.SOUTH);

        setVisible(true);
    }
}
