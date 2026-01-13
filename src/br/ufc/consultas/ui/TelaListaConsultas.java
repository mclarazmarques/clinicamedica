package ui;

import javax.swing.*;
import java.awt.*;

import model.Consulta;
import service.GerenciadorConsultas;

public class TelaListaConsultas extends JFrame {

    private GerenciadorConsultas gerenciador;

    public TelaListaConsultas(GerenciadorConsultas gerenciador) {

        this.gerenciador = gerenciador;

        setTitle("Consultas");
        setSize(750, 420);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Consultas Agendadas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        DefaultListModel<Consulta> model = new DefaultListModel<>();
        gerenciador.listar().forEach(model::addElement);

        JList<Consulta> lista = new JList<>(model);

        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

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
                new TelaEditarConsulta(c, model, gerenciador);
            }
        });

        add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel rodape = new JPanel();
        rodape.add(btnEditar);
        rodape.add(btnExcluir);
        add(rodape, BorderLayout.SOUTH);

        setVisible(true);


        btnEditar.addActionListener(e -> {
        Consulta c = lista.getSelectedValue();
        if (c != null) {
        new TelaEditarConsulta(c, model, gerenciador);
    }
});

    }
}
