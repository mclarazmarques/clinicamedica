package ui;

import javax.swing.*;
import service.GerenciadorConsultas;

public class TelaPrincipal extends JFrame {

    private GerenciadorConsultas gerConsultas;

    public TelaPrincipal() {

        gerConsultas = new GerenciadorConsultas();

        setTitle("Sistema da Clínica");
        setSize(1024, 695);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel fundo = new JLabel(
                new ImageIcon(getClass().getResource("/ui/img/menu.png"))
        );
        fundo.setLayout(null);
        setContentPane(fundo);

        JButton btnMedico = new JButton("Cadastrar Médico");
        btnMedico.setBounds(410, 80, 180, 30);
        fundo.add(btnMedico);

        JButton btnPaciente = new JButton("Cadastrar Paciente");
        btnPaciente.setBounds(410, 130, 180, 30);
        fundo.add(btnPaciente);

        JButton btnAgendar = new JButton("Agendar Consulta");
        btnAgendar.setBounds(410, 180, 180, 30);
        fundo.add(btnAgendar);

        btnMedico.addActionListener(e -> new TelaCadastroMedico());
        btnPaciente.addActionListener(e -> new TelaCadastroPaciente());

        btnAgendar.addActionListener(e ->
                new TelaAgendamentoConsulta(gerConsultas)
        );

        setVisible(true);
    }
}
