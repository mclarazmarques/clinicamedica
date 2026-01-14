package ui.lista;

import javax.swing.*;
import java.awt.*;

import model.Consulta;
import model.StatusConsulta; // ← ADICIONADO
import service.GerenciadorConsultas;
import ui.editar.TelaEditarConsulta;

public class TelaListaConsultas extends JFrame {

    private GerenciadorConsultas gerenciador;

    public TelaListaConsultas(GerenciadorConsultas gerenciador) {

        this.gerenciador = gerenciador;

        setTitle("Consultas");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Consultas Agendadas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        DefaultListModel<Consulta> model = new DefaultListModel<>();
        gerenciador.listar().forEach(model::addElement);

        JList<Consulta> lista = new JList<>(model);
        add(new JScrollPane(lista), BorderLayout.CENTER);

        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        // ===== NOVOS BOTÕES =====
        JButton btnConcluir = new JButton("Concluir");
        JButton btnFalta = new JButton("Não Compareceu");
        JButton btnCancelar = new JButton("Cancelar");

btnExcluir.addActionListener(e -> {
    Consulta c = lista.getSelectedValue();

    if (c == null) {
        JOptionPane.showMessageDialog(
            this,
            "Selecione um agendamento!",
            "Aviso",
            JOptionPane.WARNING_MESSAGE
        );
        return; 
    }

    int opcao = JOptionPane.showConfirmDialog(
        this,
        "Deseja realmente excluir este agendamento?",
        "Confirmar exclusão",
        JOptionPane.YES_NO_OPTION
    );

    if (opcao == JOptionPane.YES_OPTION) {
        new GerenciadorConsultas().remover(c);
        model.removeElement(c);

        JOptionPane.showMessageDialog(
            this,
            "Agendamento excluído com sucesso!",
            "Exclusão",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
});

        // ===== AÇÕES DOS NOVOS BOTÕES =====
        btnConcluir.addActionListener(e -> {
            Consulta c = lista.getSelectedValue();
            if (c != null) {
                c.setStatus(StatusConsulta.CONCLUIDA);
                lista.repaint();
            }
        });

        btnFalta.addActionListener(e -> {
            Consulta c = lista.getSelectedValue();
            if (c != null) {
                c.setStatus(StatusConsulta.NAO_COMPARECEU);
                lista.repaint();
            }
        });

        btnCancelar.addActionListener(e -> {
            Consulta c = lista.getSelectedValue();
            if (c != null) {
                c.setStatus(StatusConsulta.CANCELADA);
                lista.repaint();
            }
        });

        JPanel rodape = new JPanel();
        rodape.add(btnEditar);
        rodape.add(btnExcluir);
        rodape.add(btnConcluir);
        rodape.add(btnFalta);
        rodape.add(btnCancelar);

        add(rodape, BorderLayout.SOUTH);

        setVisible(true);
    }
}
