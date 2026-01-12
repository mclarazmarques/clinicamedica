package ui;

import javax.swing.*;
import model.Medico;
import service.GerenciadorMedicos;
import ui.TelaListaMedicos;

public class TelaCadastroMedico extends JFrame {

    public TelaCadastroMedico() {
        setTitle("Cadastro de Médico");
         setSize(1024, 695);
        setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(410, 80, 180, 30);
        add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(410, 110, 180, 30);
        add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(410, 150, 180, 30);
        add(lblCpf);

        JTextField txtCpf = new JTextField();
        txtCpf.setBounds(410, 180, 180, 30);
        add(txtCpf);

        JLabel lblEsp = new JLabel("Especialidade:");
        lblEsp.setBounds(410, 220, 180, 30);
        add(lblEsp);

        JTextField txtEsp = new JTextField();
        txtEsp.setBounds(410, 250, 180, 30);
        add(txtEsp);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(410, 300, 180, 30);
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            Medico m = new Medico(
                txtNome.getText(),
                txtCpf.getText(),
                txtEsp.getText()
            );
            GerenciadorMedicos.adicionar(m);
            JOptionPane.showMessageDialog(this, "Médico cadastrado!");
        });


        JButton btnListar = new JButton("Listar Médicos");
        btnListar.setBounds(410, 340, 180, 30);
        add(btnListar);

        btnListar.addActionListener(e -> new TelaListaMedicos());

        setVisible(true);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(410, 380, 180, 30);
        add(btnExcluir);

        btnExcluir.addActionListener(e -> new TelaExcluirMedico());


    }
}
