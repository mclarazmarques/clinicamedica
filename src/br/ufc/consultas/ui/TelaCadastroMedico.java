package ui;

import javax.swing.*;
import model.Medico;
import service.GerenciadorMedicos;
import java.awt.Color;

public class TelaCadastroMedico extends JFrame {

    public TelaCadastroMedico() {
        setTitle("Cadastro de Médico");
        setSize(500, 400);
        setLayout(null);

        JLabel fundo = new JLabel(
                new ImageIcon(getClass().getResource("/ui/img/menu.jpg"))
        );
        fundo.setLayout(null);
        setContentPane(fundo);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(140, 30, 180, 30);
        lblNome.setForeground(Color.WHITE);
        add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(140, 60, 180, 30);
        add(txtNome);

        JLabel lblCrm = new JLabel("CRM:");
        lblCrm.setBounds(140, 90, 180, 30);
        lblCrm.setForeground(Color.WHITE);
        add(lblCrm);

        JTextField txtCrm = new JTextField();
        txtCrm.setBounds(140, 120, 180, 30);
        add(txtCrm);

        JLabel lblEsp = new JLabel("Especialidade:");
        lblEsp.setBounds(140, 150, 180, 30);
        lblEsp.setForeground(Color.WHITE);
        add(lblEsp);

        JTextField txtEsp = new JTextField();
        txtEsp.setBounds(140, 180, 180, 30);
        add(txtEsp);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(140, 240, 180, 30);
        btnSalvar.setBackground(new Color(0, 123, 255));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            try {
if (txtNome.getText().trim().isEmpty() ||
            txtCrm.getText().trim().isEmpty() ||
            txtEsp.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Medico m = new Medico(
                txtNome.getText().trim(),
                txtCrm.getText().trim(),
                txtEsp.getText().trim()
        );

        GerenciadorMedicos.adicionar(m);

        JOptionPane.showMessageDialog(
                this,
                "Médico cadastrado com sucesso!"
        );

        txtNome.setText("");
        txtCrm.setText("");
        txtEsp.setText("");

            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        JButton btnListar = new JButton("Listar Médicos");
        btnListar.setBounds(140, 280, 180, 30);
        btnListar.setBackground(new Color(0, 123, 255));
        btnListar.setForeground(Color.WHITE);
        btnListar.setFocusPainted(false);
        add(btnListar);

        btnListar.addActionListener(e -> new TelaListaMedicos());

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(140, 320, 180, 30);
        btnExcluir.setBackground(new Color(0, 123, 255));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setFocusPainted(false);
        add(btnExcluir);

        btnExcluir.addActionListener(e -> new TelaExcluirMedico());

        setVisible(true);
    }
}
