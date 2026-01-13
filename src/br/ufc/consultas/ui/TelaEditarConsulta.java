package ui;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.Consulta;
import service.GerenciadorConsultas;

public class TelaEditarConsulta extends JFrame {

    private Consulta consulta;
    private DefaultListModel<Consulta> model;
    private GerenciadorConsultas gerenciador;

    public TelaEditarConsulta(
            Consulta consulta,
            DefaultListModel<Consulta> model,
            GerenciadorConsultas gerenciador
    ) {

        this.consulta = consulta;
        this.model = model;
        this.gerenciador = gerenciador;

        setTitle("Editar Consulta");
        setSize(420, 320);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblMedico = new JLabel("Médico:");
        JLabel lblPaciente = new JLabel("Paciente:");
        JLabel lblDataHora = new JLabel("Data e Hora:");

        JTextField txtMedico = new JTextField(consulta.getMedico().toString());
        JTextField txtPaciente = new JTextField(consulta.getPaciente().toString());
        JTextField txtDataHora = new JTextField(
                consulta.getDataHora().format(
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                )
        );

        txtMedico.setEditable(false);
        txtPaciente.setEditable(false);

        lblMedico.setBounds(30, 30, 100, 25);
        txtMedico.setBounds(140, 30, 230, 25);

        lblPaciente.setBounds(30, 70, 100, 25);
        txtPaciente.setBounds(140, 70, 230, 25);

        lblDataHora.setBounds(30, 110, 100, 25);
        txtDataHora.setBounds(140, 110, 230, 25);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(150, 180, 120, 30);

        btnSalvar.addActionListener(e -> {
            try {
                LocalDateTime novaDataHora = LocalDateTime.parse(
                        txtDataHora.getText(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                );

                // remove a antiga
                gerenciador.remover(consulta);

                // adiciona a nova
                Consulta novaConsulta = new Consulta(
                        consulta.getMedico(),
                        consulta.getPaciente(),
                        novaDataHora
                );

                gerenciador.agendar(novaConsulta);

                // atualiza lista
                model.clear();
                gerenciador.listar().forEach(model::addElement);

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Formato inválido!\nUse: dd/MM/yyyy HH:mm",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        add(lblMedico);
        add(txtMedico);
        add(lblPaciente);
        add(txtPaciente);
        add(lblDataHora);
        add(txtDataHora);
        add(btnSalvar);

        setVisible(true);
    }
}
