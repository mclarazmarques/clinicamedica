package ui;

import javax.swing.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema da Clínica");
        setSize(1024, 695);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel fundo = new JLabel(new ImageIcon(getClass().getResource("/ui/img/menu.png")));
        fundo.setLayout(null);
        setContentPane(fundo);
        

        JButton btnMedico = new JButton("Cadastrar Médico");
        btnMedico.setBounds(410, 80, 180, 30);
        fundo.add(btnMedico);

        JButton btnPaciente = new JButton("Cadastrar Paciente");
        btnPaciente.setBounds(410, 130, 180, 30);
        fundo.add(btnPaciente);

        btnMedico.addActionListener(e -> new TelaCadastroMedico());
        btnPaciente.addActionListener(e -> new TelaCadastroPaciente());

        setVisible(true);

        JButton btListaMedicos = new JButton("Listar Médicos");
        JButton btListaPacientes = new JButton("Listar Pacientes");

        

    }
}
