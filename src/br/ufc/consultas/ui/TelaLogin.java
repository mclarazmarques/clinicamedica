package ui;

import javax.swing.*;
import java.awt.Color;
import ui.TelaPrincipal;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        setTitle("Login - Clínica Médica");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel fundo = new JLabel(
                new ImageIcon(getClass().getResource("/ui/img/menu.jpg"))
        );
        fundo.setLayout(null);
        setContentPane(fundo);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(120, 60, 200, 30);
        lblUsuario.setForeground(Color.WHITE);
        add(lblUsuario);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 90, 200, 30);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(120, 120,  200, 30);
        lblSenha.setForeground(Color.WHITE);
        add(lblSenha);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBounds(120, 150, 200, 30);
        add(txtSenha);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(120, 200, 200, 30);
        btnEntrar.setBackground(new Color(0, 123, 255));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);
        add(btnEntrar);

        btnEntrar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());

            if (usuario.equals("admin") && senha.equals("123")) {
                new TelaPrincipal();
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Usuário ou senha inválidos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        setVisible(true);
    }
}
