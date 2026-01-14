package ui;

import javax.swing.*;
import service.GerenciadorConsultas;
import java.awt.Color;

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
    }
}
