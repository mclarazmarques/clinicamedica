package ui;

import javax.swing.*;
import java.awt.*;

import model.Consulta;
import model.StatusConsulta; // ← ADICIONADO
import service.GerenciadorConsultas;

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
            if (c != null) {
                gerenciador.remover(c);
                model.removeElement(c);
            }
        });

        btnEditar.addActionListener(e -> {
            Consulta c = lista.getSelectedValue();
            if (c != null) {
                new TelaEditarConsulta(c, model, lista);
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
