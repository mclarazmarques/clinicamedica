package ui;

import javax.swing.*;

public class TelaAgendamentoConsulta extends JFrame {

    public TelaAgendamentoConsulta() {

        setTitle("Agendamento de Consulta");
        setSize(350, 300);
        setLayout(null);

        JLabel lblPaciente = new JLabel("Paciente:");
        lblPaciente.setBounds(20, 20, 100, 25);
        add(lblPaciente);

        JTextField txtPaciente = new JTextField();
        txtPaciente.setBounds(120, 20, 180, 25);
        add(txtPaciente);

        JLabel lblMedico = new JLabel("Médico:");
        lblMedico.setBounds(20, 60, 100, 25);
        add(lblMedico);

        JTextField txtMedico = new JTextField();
        txtMedico.setBounds(120, 60, 180, 25);
        add(txtMedico);

        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(20, 100, 100, 25);
        add(lblData);

        JTextField txtData = new JTextField();
        txtData.setBounds(120, 100, 180, 25);
        add(txtData);

        JLabel lblHorario = new JLabel("Horário:");
        lblHorario.setBounds(20, 140, 100, 25);
        add(lblHorario);

        JTextField txtHorario = new JTextField();
        txtHorario.setBounds(120, 140, 180, 25);
        add(txtHorario);

        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.setBounds(110, 190, 120, 30);
        add(btnAgendar);

        btnAgendar.addActionListener(e -> {

            String paciente = txtPaciente.getText();
            String medico = txtMedico.getText();
            String data = txtData.getText();
            String horario = txtHorario.getText();

            JOptionPane.showMessageDialog(
                this,
                "Consulta agendada com sucesso!\n\n" +
                "Paciente: " + paciente + "\n" +
                "Médico: " + medico + "\n" +
                "Data: " + data + "\n" +
                "Horário: " + horario
            );
        });

        setVisible(true);
    }
}
