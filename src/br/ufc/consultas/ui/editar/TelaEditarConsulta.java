package ui.editar;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.Consulta;

import java.awt.Color;

public class TelaEditarConsulta extends JFrame {

    private Consulta consulta;
    private DefaultListModel<Consulta> model;
    private JList<Consulta> lista;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public TelaEditarConsulta(
            Consulta consulta,
            DefaultListModel<Consulta> model,
            JList<Consulta> lista
    ) {

        this.consulta = consulta;
        this.model = model;
        this.lista = lista;

        setTitle("Editar Consulta");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel fundo = new JLabel(
                new ImageIcon(getClass().getResource("/ui/img/menu.jpg"))
        );
        fundo.setLayout(null);
        setContentPane(fundo);

        // labels
        JLabel lblMedico = new JLabel("Médico:");
        lblMedico.setForeground(Color.WHITE);

        JLabel lblPaciente = new JLabel("Paciente:");
        lblPaciente.setForeground(Color.WHITE);

        JLabel lblDataHora = new JLabel("Data e Hora (DD/MM/AAAA HH:MM):");
        lblDataHora.setForeground(Color.WHITE);

        // campos
        JTextField txtMedico =
                new JTextField(consulta.getMedico().toString());
        JTextField txtPaciente =
                new JTextField(consulta.getPaciente().toString());
        JTextField txtDataHora =
                new JTextField(consulta.getDataHora().format(FORMATTER));

        txtMedico.setEditable(false);
        txtPaciente.setEditable(false);

        //locais e posicoes dos botoes
        lblMedico.setBounds(30, 30, 120, 25);
        txtMedico.setBounds(160, 30, 200, 25);

        lblPaciente.setBounds(30, 70, 120, 25);
        txtPaciente.setBounds(160, 70, 200, 25);

        lblDataHora.setBounds(30, 110, 250, 25);
        txtDataHora.setBounds(30, 140, 330, 25);

        //salvar
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(150, 200, 120, 30);

        btnSalvar.setBackground(new Color(0, 123, 255)); // azul
        btnSalvar.setForeground(Color.WHITE);            // texto branco
        btnSalvar.setFocusPainted(false);  

        btnSalvar.addActionListener(e -> {
            try {
                LocalDateTime novaDataHora = LocalDateTime.parse(
                        txtDataHora.getText(),
                        FORMATTER
                );

                // atualiza a própria consulta
                consulta.setDataHora(novaDataHora);

                // força atualização visual
                lista.updateUI();

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Formato inválido!\nUse: DD/MM/AAAA HH:MM",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // adicao dos componentes
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
