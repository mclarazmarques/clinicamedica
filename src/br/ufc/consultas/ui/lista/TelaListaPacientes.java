package ui.lista;

import javax.swing.*;
import java.awt.*;
import model.Paciente;
import service.GerenciadorPacientes;
import ui.editar.TelaEditarPaciente;

public class TelaListaPacientes extends JFrame {

    public TelaListaPacientes() {

        setTitle("Pacientes");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // JLabel fundo = new JLabel(
        //         new ImageIcon(getClass().getResource("/ui/img/menu.jpg"))
        // );
        // fundo.setLayout(null);
        // setContentPane(fundo);

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

        int opcao = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente excluir este paciente?",
            "Confirmar exclusão",
            JOptionPane.YES_NO_OPTION
        );

        if (opcao == JOptionPane.YES_OPTION) {
            GerenciadorPacientes.removerPorCpf(p.getCpf());
            model.removeElement(p);

            JOptionPane.showMessageDialog(
                this,
                "Paciente excluído com sucesso!",
                "Exclusão",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    } else {
        JOptionPane.showMessageDialog(
            this,
            "Selecione um paciente!",
            "Aviso",
            JOptionPane.WARNING_MESSAGE
        );
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
