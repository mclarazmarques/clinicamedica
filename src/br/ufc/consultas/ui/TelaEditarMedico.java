package ui;

import javax.swing.*;
import model.Medico;
import service.GerenciadorMedicos;

public class TelaEditarMedico extends JFrame {

    public TelaEditarMedico(Medico medico, DefaultListModel<Medico> model) {
        setTitle("Editar Médico");
        setSize(1024, 695);
        setLayout(null);
        setLocationRelativeTo(null);

        JTextField txtNome = new JTextField(medico.getNome());
        JTextField txtCpf = new JTextField(medico.getCpf());
        JTextField txtEsp = new JTextField(medico.getEspecialidade());

        txtCpf.setEditable(false);

        txtNome.setBounds(50, 30, 200, 25);
        txtCpf.setBounds(50, 70, 200, 25);
        txtEsp.setBounds(50, 110, 200, 25);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(90, 160, 120, 30);

        btnSalvar.addActionListener(e -> {
            GerenciadorMedicos.removerPorCpf(medico.getCpf());
            GerenciadorMedicos.adicionar(
                new Medico(txtNome.getText(), txtCpf.getText(), txtEsp.getText())
            );

            model.clear();
            GerenciadorMedicos.listar().forEach(model::addElement);

            dispose();
        });

        add(txtNome);
        add(txtCpf);
        add(txtEsp);
        add(btnSalvar);

        setVisible(true);
    }
}
