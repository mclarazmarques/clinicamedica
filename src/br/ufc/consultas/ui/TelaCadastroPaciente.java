package ui;

import javax.swing.*;
import model.Paciente;
import service.GerenciadorPacientes;
import ui.TelaListaPacientes;


public class TelaCadastroPaciente extends JFrame {

    public TelaCadastroPaciente() {
        setTitle("Cadastro de Paciente");
         setSize(1024, 695);
        setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 80, 25);
        add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(100, 20, 150, 25);
        add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(20, 60, 80, 25);
        add(lblCpf);

        JTextField txtCpf = new JTextField();
        txtCpf.setBounds(100, 60, 150, 25);
        add(txtCpf);

        JLabel lblPlano = new JLabel("Plano:");
        lblPlano.setBounds(20, 100, 80, 25);
        add(lblPlano);

        JTextField txtPlano = new JTextField();
        txtPlano.setBounds(100, 100, 150, 25);
        add(txtPlano);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(90, 150, 100, 30);
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            Paciente p = new Paciente(
                txtNome.getText(),
                txtCpf.getText(),
                txtPlano.getText()
            );
            GerenciadorPacientes.adicionar(p);
            JOptionPane.showMessageDialog(this, "Paciente cadastrado!");
        });

        setVisible(true);

        JButton btnListar = new JButton("Listar Pacientes");
        btnListar.setBounds(90, 190, 150, 30);
        add(btnListar);

        btnListar.addActionListener(e -> new TelaListaPacientes());


        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(90, 230, 100, 30);
        add(btnExcluir);

btnExcluir.addActionListener(e -> {
    String cpf = txtCpf.getText();

    if (cpf.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Informe o CPF para excluir.");
        return;
    }

    boolean ok = GerenciadorPacientes.removerPorCpf(cpf);

    if (ok) {
        JOptionPane.showMessageDialog(this, "Paciente removido!");
    } else {
        JOptionPane.showMessageDialog(this, "CPF não encontrado.");
    }
});


    }
}
