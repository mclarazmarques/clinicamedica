package ui;

import javax.swing.*;
import model.Paciente;
import service.GerenciadorPacientes;

public class TelaEditarPaciente extends JFrame {

    public TelaEditarPaciente(Paciente paciente, DefaultListModel<Paciente> model) {

        setTitle("Editar Paciente");
        setSize(1024, 695);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblCpf = new JLabel("CPF:");
        JLabel lblTelefone = new JLabel("Telefone:");

        JTextField txtNome = new JTextField(paciente.getNome());
        JTextField txtCpf = new JTextField(paciente.getCpf());
        JTextField txtTelefone = new JTextField(paciente.getTelefone());

        txtCpf.setEditable(false);

        lblNome.setBounds(30, 20, 80, 25);
        txtNome.setBounds(110, 20, 150, 25);

        lblCpf.setBounds(30, 60, 80, 25);
        txtCpf.setBounds(110, 60, 150, 25);

        lblTelefone.setBounds(30, 100, 80, 25);
        txtTelefone.setBounds(110, 100, 150, 25);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(90, 160, 120, 30);

        btnSalvar.addActionListener(e -> {

            GerenciadorPacientes.removerPorCpf(paciente.getCpf());
            GerenciadorPacientes.adicionar(
                new Paciente(
                    txtNome.getText(),
                    txtCpf.getText(),
                    txtTelefone.getText()
                )
            );

            model.clear();
            GerenciadorPacientes.listar().forEach(model::addElement);

            dispose();
        });

        add(lblNome);
        add(txtNome);
        add(lblCpf);
        add(txtCpf);
        add(lblTelefone);
        add(txtTelefone);
        add(btnSalvar);

        setVisible(true);
    }
}
