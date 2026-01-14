package ui;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import model.Consulta;
import model.Medico;
import model.Paciente;
import service.GerenciadorConsultas;
import service.GerenciadorMedicos;
import service.GerenciadorPacientes;

import java.awt.Color;

public class TelaAgendamentoConsulta extends JFrame {

    private GerenciadorConsultas gerConsultas;

    public TelaAgendamentoConsulta(GerenciadorConsultas gerConsultas) {

        this.gerConsultas = gerConsultas;

        setTitle("Agendamento de Consulta");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel fundo = new JLabel(
                new ImageIcon(getClass().getResource("/ui/img/menu.jpg"))
        );
        fundo.setLayout(null);
        setContentPane(fundo);

        // ===== PACIENTE =====
        JLabel lblPaciente = new JLabel("Paciente (nome):");
        lblPaciente.setBounds(20, 20, 200, 30);
        lblPaciente.setForeground(Color.WHITE);
        add(lblPaciente);

        JTextField txtPaciente = new JTextField();
        txtPaciente.setBounds(180, 20, 200, 30);
        add(txtPaciente);

        // ===== MÉDICO =====
        JLabel lblMedico = new JLabel("Médico (nome):");
        lblMedico.setBounds(20, 60, 200, 30);
        lblMedico.setForeground(Color.WHITE);
        add(lblMedico);

        JTextField txtMedico = new JTextField();
        txtMedico.setBounds(180, 60, 200, 30);
        add(txtMedico);

        // ===== DATA =====
        JLabel lblData = new JLabel("Data (DD/MM/AAAA):");
        lblData.setBounds(20, 100, 200, 30);
        lblData.setForeground(Color.WHITE);
        add(lblData);

        JTextField txtData = new JTextField();
        txtData.setBounds(180, 100, 200, 30);
        add(txtData);

        // ===== HORÁRIO =====
        JLabel lblHorario = new JLabel("Horário (HH:MM):");
        lblHorario.setBounds(20, 140, 200, 30);
        lblHorario.setForeground(Color.WHITE);
        add(lblHorario);

        JTextField txtHorario = new JTextField();
        txtHorario.setBounds(180, 140, 200, 30);
        add(txtHorario);

        // ===== BOTÃO AGENDAR =====
        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.setBounds(180, 190, 200, 30);

        btnAgendar.setBackground(new Color(0, 123, 255)); // azul
        btnAgendar.setForeground(Color.WHITE);            // texto branco
        btnAgendar.setFocusPainted(false);  

        add(btnAgendar);

        btnAgendar.addActionListener(e -> {
            try {
                        if (txtPaciente.getText().trim().isEmpty() ||
            txtMedico.getText().trim().isEmpty() ||
            txtData.getText().trim().isEmpty() ||
            txtHorario.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        String nomePaciente = txtPaciente.getText().trim();
        String nomeMedico = txtMedico.getText().trim();

        Paciente paciente = GerenciadorPacientes
                .listar()
                .stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nomePaciente))
                .findFirst()
                .orElse(null);

        Medico medico = GerenciadorMedicos
                .listar()
                .stream()
                .filter(m -> m.getNome().equalsIgnoreCase(nomeMedico))
                .findFirst()
                .orElse(null);

        if (paciente == null || medico == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Paciente ou médico não encontrado.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate data = LocalDate.parse(txtData.getText(), formatter);
        LocalTime hora = LocalTime.parse(txtHorario.getText());
        LocalDateTime dataHora = LocalDateTime.of(data, hora);

        Consulta consulta = new Consulta(medico, paciente, dataHora);
        gerConsultas.agendar(consulta);

        JOptionPane.showMessageDialog(
                this,
                "Consulta agendada com sucesso!"
        );

        txtPaciente.setText("");
        txtMedico.setText("");
        txtData.setText("");
        txtHorario.setText("");
            }
            //  CONFLITO DE AGENDA
            catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Conflito de Agenda",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // ===== BOTÃO GERENCIAR CONSULTAS =====
        JButton btnGerenciar = new JButton("Gerenciar Consultas");
        btnGerenciar.setBounds(180, 240, 200, 30);

        btnGerenciar.setBackground(new Color(0, 123, 255)); // azul
        btnGerenciar.setForeground(Color.WHITE);            // texto branco
        btnGerenciar.setFocusPainted(false);  

        add(btnGerenciar);

        btnGerenciar.addActionListener(e -> {
            new TelaListaConsultas(gerConsultas);
        });

        setVisible(true);
    }
}
