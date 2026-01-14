package ui.editar;

import javax.swing.*;
import model.Paciente;
import service.GerenciadorPacientes;
import java.awt.Color;

public class TelaEditarPaciente extends JFrame {

    public TelaEditarPaciente(Paciente paciente, DefaultListModel<Paciente> model) {

        setTitle("Editar Paciente");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel fundo = new JLabel(
                new ImageIcon(getClass().getResource("/ui/img/menu.jpg"))
        );
        fundo.setLayout(null);
        setContentPane(fundo);

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblCpf = new JLabel("CPF:");
        JLabel lblPlanoSaude = new JLabel("Plano:");

        JTextField txtNome = new JTextField(paciente.getNome());
        JTextField txtCpf = new JTextField(paciente.getCpf());
        JTextField txtPlanoSaude = new JTextField(paciente.getPlanoSaude());

        txtCpf.setEditable(true);

        lblNome.setBounds(30, 20, 80, 25);
        lblNome.setForeground(Color.WHITE);

        txtNome.setBounds(110, 20, 150, 25);

        lblCpf.setBounds(30, 60, 80, 25);
        lblCpf.setForeground(Color.WHITE);
        txtCpf.setBounds(110, 60, 150, 25);

        lblPlanoSaude.setBounds(30, 100, 80, 25);
        lblPlanoSaude.setForeground(Color.WHITE);
        txtPlanoSaude.setBounds(110, 100, 150, 25);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(90, 160, 120, 30);

        btnSalvar.addActionListener(e -> {

            GerenciadorPacientes.removerPorCpf(paciente.getCpf());
            GerenciadorPacientes.adicionar(
                new Paciente(
                    txtNome.getText(),
                    txtCpf.getText(),
                    txtPlanoSaude.getText()
                )
            );

            model.clear();
            GerenciadorPacientes.listar().forEach(model::addElement);

            //aviso de acao feita

            JOptionPane.showMessageDialog(
            this,
                "Paciente atualizado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
            );

            dispose();
        });

        fundo.add(lblNome);
        fundo.add(txtNome);
        fundo.add(lblCpf);
        fundo.add(txtCpf);
        fundo.add(btnSalvar);
        fundo.add(lblPlanoSaude);
        fundo.add(txtPlanoSaude);


        setVisible(true);
    }
}
