package ui;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import model.Consulta;
import model.Medico;
import model.Paciente;
import service.GerenciadorConsultas;
import service.GerenciadorMedicos;
import service.GerenciadorPacientes;

public class TelaAgendamentoConsulta extends JFrame {

    private GerenciadorConsultas gerConsultas;

    public TelaAgendamentoConsulta(GerenciadorConsultas gerConsultas) {

        this.gerConsultas = gerConsultas;

        setTitle("Agendamento de Consulta");
        setSize(1024, 695);
        setLayout(null);
        setLocationRelativeTo(null);

        // ===== PACIENTE =====
        JLabel lblPaciente = new JLabel("Paciente (nome):");
        lblPaciente.setBounds(20, 20, 120, 25);
        add(lblPaciente);

        JTextField txtPaciente = new JTextField();
        txtPaciente.setBounds(150, 20, 160, 25);
        add(txtPaciente);

        // ===== MÉDICO =====
        JLabel lblMedico = new JLabel("Médico (nome):");
        lblMedico.setBounds(20, 60, 120, 25);
        add(lblMedico);

        JTextField txtMedico = new JTextField();
        txtMedico.setBounds(150, 60, 160, 25);
        add(txtMedico);

        // ===== DATA =====
        JLabel lblData = new JLabel("Data (AAAA-MM-DD):");
        lblData.setBounds(20, 100, 150, 25);
        add(lblData);

        JTextField txtData = new JTextField();
        txtData.setBounds(180, 100, 130, 25);
        add(txtData);

        // ===== HORÁRIO =====
        JLabel lblHorario = new JLabel("Horário (HH:MM):");
        lblHorario.setBounds(20, 140, 150, 25);
        add(lblHorario);

        JTextField txtHorario = new JTextField();
        txtHorario.setBounds(180, 140, 130, 25);
        add(txtHorario);

        // ===== BOTÃO AGENDAR =====
        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.setBounds(110, 190, 120, 30);
        add(btnAgendar);

        btnAgendar.addActionListener(e -> {
            try {
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
                            "Paciente ou médico não encontrado."
                    );
                    return;
                }

                LocalDate data = LocalDate.parse(txtData.getText());
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

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Erro! Verifique os dados.\nFormato correto:\nData: AAAA-MM-DD\nHora: HH:MM"
                );
            }
        });

        // ===== BOTÃO GERENCIAR (EDITAR / EXCLUIR) =====
        JButton btnGerenciar = new JButton("Gerenciar Consultas");
        btnGerenciar.setBounds(80, 240, 200, 30);
        add(btnGerenciar);

        btnGerenciar.addActionListener(e -> {
            new TelaListaConsultas(gerConsultas);
        });

        setVisible(true);
    }
}
