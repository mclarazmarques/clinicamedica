package ui;

import javax.swing.*;
import java.awt.*;
import model.Medico;
import service.GerenciadorMedicos;

public class TelaListaMedicos extends JFrame {

    public TelaListaMedicos() {
        setTitle("Médicos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // JLabel fundo = new JLabel(
        //         new ImageIcon(getClass().getResource("/ui/img/menu.jpg"))
        // );
        // fundo.setLayout(null);
        // setContentPane(fundo);

        JLabel titulo = new JLabel("Médicos Cadastrados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        DefaultListModel<Medico> model = new DefaultListModel<>();
        GerenciadorMedicos.listar().forEach(model::addElement);

        JList<Medico> lista = new JList<>(model);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(0, 123, 255)); // azul
        btnExcluir.setForeground(Color.WHITE);            // texto branco
        btnExcluir.setFocusPainted(false);  


        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(0, 123, 255)); // azul
        btnEditar.setForeground(Color.WHITE);            // texto branco
        btnEditar.setFocusPainted(false);  

        btnExcluir.addActionListener(e -> {
            Medico m = lista.getSelectedValue();
            if (m != null) {
                GerenciadorMedicos.removerPorCrm(m.getCrm());
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
