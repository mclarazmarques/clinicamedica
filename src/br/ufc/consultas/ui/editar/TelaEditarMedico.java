package ui.editar;

import javax.swing.*;
import model.Medico;
import service.GerenciadorMedicos;

public class TelaEditarMedico extends JFrame {

    public TelaEditarMedico(Medico medico, DefaultListModel<Medico> model) {
        setTitle("Editar Médico");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel fundo = new JLabel(
                new ImageIcon(getClass().getResource("/ui/img/menu.jpg"))
        );
        fundo.setLayout(null);
        setContentPane(fundo);

        JTextField txtNome = new JTextField(medico.getNome());
        JTextField txtCrm = new JTextField(medico.getCrm());
        JTextField txtEsp = new JTextField(medico.getEspecialidade());

        txtCrm.setEditable(true);

        txtNome.setBounds(50, 30, 200, 25);
        txtCrm.setBounds(50, 70, 200, 25);
        txtEsp.setBounds(50, 110, 200, 25);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(90, 160, 120, 30);

        btnSalvar.addActionListener(e -> {
            GerenciadorMedicos.removerPorCrm(medico.getCrm());
            GerenciadorMedicos.adicionar(
                new Medico(txtNome.getText(), txtCrm.getText(), txtEsp.getText())
            );

            model.clear();
            GerenciadorMedicos.listar().forEach(model::addElement);

            //aviso de acao feita 
            JOptionPane.showMessageDialog(
            this,
            "Médico atualizado com sucesso!",
            "Sucesso",
            JOptionPane.INFORMATION_MESSAGE
);


            dispose();
        });

        add(txtNome);
        add(txtCrm);
        add(txtEsp);
        add(btnSalvar);

        setVisible(true);
    }
}
