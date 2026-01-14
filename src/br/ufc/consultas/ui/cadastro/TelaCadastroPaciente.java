package ui.cadastro;

import javax.swing.*;
import model.Paciente;
import service.GerenciadorPacientes;
import ui.TelaPrincipal;
import ui.excluir.TelaExcluirPaciente;
import ui.lista.TelaListaPacientes;

import java.awt.Color;

public class TelaCadastroPaciente extends JFrame {

    public TelaCadastroPaciente() {
        setTitle("Cadastro de Paciente");
        setSize(500, 400);
        setLayout(null);

        JLabel fundo = new JLabel(
                new ImageIcon(getClass().getResource("/ui/img/menu.jpg"))
        );
        fundo.setLayout(null);
        setContentPane(fundo);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(80, 30, 180, 30);
        lblNome.setForeground(Color.WHITE);
        add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(140, 30, 180, 30);
        add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(80, 100, 180, 30);
        lblCpf.setForeground(Color.WHITE);
        add(lblCpf);

        JTextField txtCpf = new JTextField();
        txtCpf.setBounds(140, 100, 180, 30);
        add(txtCpf);

        JLabel lblPlano = new JLabel("Plano:");
        lblPlano.setBounds(80, 170, 180, 30);
        lblPlano.setForeground(Color.WHITE);
        add(lblPlano);

        JTextField txtPlano = new JTextField();
        txtPlano.setBounds(140, 170, 180, 30);
        add(txtPlano);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(140, 240, 180, 30);
        btnSalvar.setBackground(new Color(0, 123, 255));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            try {
                if (txtNome.getText().trim().isEmpty() ||
            txtCpf.getText().trim().isEmpty() ||
            txtPlano.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Paciente p = new Paciente(
                txtNome.getText().trim(),
                txtCpf.getText().trim(),
                txtPlano.getText().trim()
        );

        GerenciadorPacientes.adicionar(p);

        JOptionPane.showMessageDialog(
                this,
                "Paciente cadastrado com sucesso!"
        );

        txtNome.setText("");
        txtCpf.setText("");
        txtPlano.setText("");

            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        JButton btnListar = new JButton("Listar Pacientes");
        btnListar.setBounds(140, 280, 180, 30);
        btnListar.setBackground(new Color(0, 123, 255));
        btnListar.setForeground(Color.WHITE);
        btnListar.setFocusPainted(false);
        add(btnListar);

        btnListar.addActionListener(e -> new TelaListaPacientes());

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(140, 320, 180, 30);
        btnExcluir.setBackground(new Color(0, 123, 255));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setFocusPainted(false);
        add(btnExcluir);

        btnExcluir.addActionListener(e -> new TelaExcluirPaciente());

        setVisible(true);

        JButton btnVoltar = new JButton("↩");
        btnVoltar.setBounds(20, 20, 50, 30);
        btnVoltar.setBackground(new Color(108, 117, 125));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        add(btnVoltar);

        btnVoltar.addActionListener(e -> {
        new TelaPrincipal();
        dispose();
        });

        
    }
}
