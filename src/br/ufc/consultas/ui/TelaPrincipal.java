package ui;

import javax.swing.*;
import service.GerenciadorConsultas;
import ui.cadastro.TelaAgendamentoConsulta;
import ui.cadastro.TelaCadastroMedico;
import ui.cadastro.TelaCadastroPaciente;

import java.awt.Color;
import java.awt.Font;

public class TelaPrincipal extends JFrame {

    private GerenciadorConsultas gerConsultas;

    public TelaPrincipal() {

        gerConsultas = new GerenciadorConsultas();

        setTitle("Sistema da Clínica");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel fundo = new JLabel(
                new ImageIcon(getClass().getResource("/ui/img/menu.jpg"))
        );
        fundo.setLayout(null);
        setContentPane(fundo);

        JLabel titulo = new JLabel("SAM - SISTEMA DE AGENDAMENTO MÉDICO", SwingConstants.CENTER);
        titulo.setBounds(50, 20, 400, 40);
        titulo.setFont(new Font("Arial", Font.BOLD, 15));
        titulo.setForeground(Color.WHITE);

        fundo.add(titulo);


        JButton btnMedico = new JButton("Cadastrar Médico");
        btnMedico.setBounds(100, 80, 300, 50);
        btnMedico.setBackground(new Color(0, 123, 255)); // azul
        btnMedico.setForeground(Color.WHITE);            // texto branco
        btnMedico.setFocusPainted(false);    
        fundo.add(btnMedico);

        JButton btnPaciente = new JButton("Cadastrar Paciente");
        btnPaciente.setBounds(100, 150, 300, 50);
        btnPaciente.setBackground(new Color(0, 123, 255)); // azul
        btnPaciente.setForeground(Color.WHITE);            // texto branco
        fundo.add(btnPaciente);

        JButton btnAgendar = new JButton("Agendar Consulta");
        btnAgendar.setBounds(100, 220, 300, 50);
        btnAgendar.setBackground(new Color(0, 123, 255)); // azul
        btnAgendar.setForeground(Color.WHITE);            // texto branco
        fundo.add(btnAgendar);

        btnMedico.addActionListener(e -> new TelaCadastroMedico());
        btnPaciente.addActionListener(e -> new TelaCadastroPaciente());

        btnAgendar.addActionListener(e ->
                new TelaAgendamentoConsulta(gerConsultas)
        );

        setVisible(true);

        //BOTAO DE SAIR

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(200, 300, 100, 30);
        btnLogout.setBackground(new Color(220, 53, 69));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        add(btnLogout);

        btnLogout.addActionListener(e -> {
            int opcao = JOptionPane.showConfirmDialog(
            this,
            "Deseja sair do sistema?",
            "Confirmação",
            JOptionPane.YES_NO_OPTION
    );

            if (opcao == JOptionPane.YES_OPTION) {
            new TelaLogin();
            dispose();
            }
        });

    }
}
