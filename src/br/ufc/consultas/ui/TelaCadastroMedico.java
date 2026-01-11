package ui;

import javax.swing.*;
import model.Medico;

public class TelaCadastroMedico extends JFrame {

    public TelaCadastroMedico() {
        setTitle("Cadastro de Médico");
        setSize(300, 250);
        setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 80, 25);
        add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(100, 20, 150, 25);
        add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(20, 60, 80, 25);
        add(lblCpf);

        JTextField txtCpf = new JTextField();
        txtCpf.setBounds(100, 60, 150, 25);
        add(txtCpf);

        JLabel lblEsp = new JLabel("Especialidade:");
        lblEsp.setBounds(20, 100, 100, 25);
        add(lblEsp);

        JTextField txtEsp = new JTextField();
        txtEsp.setBounds(120, 100, 130, 25);
        add(txtEsp);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(90, 150, 100, 30);
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            new Medico(
                txtNome.getText(),
                txtCpf.getText(),
                txtEsp.getText()
            );
            JOptionPane.showMessageDialog(this, "Médico cadastrado!");
        });

        setVisible(true);
    }
}
