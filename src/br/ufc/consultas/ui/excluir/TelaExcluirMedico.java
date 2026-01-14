package ui.excluir;

import javax.swing.*;
import java.awt.*;
import model.Medico;
import service.GerenciadorMedicos;

public class TelaExcluirMedico {

    public TelaExcluirMedico() {

        JFrame frame = new JFrame("Excluir Médico");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // JLabel fundo = new JLabel(
        //         new ImageIcon(getClass().getResource("/ui/img/menu.jpg"))
        // );
        // fundo.setLayout(null);
        // setContentPane(fundo);

        JLabel titulo = new JLabel("Excluir Médico", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titulo, BorderLayout.NORTH);

        DefaultListModel<Medico> model = new DefaultListModel<>();
        for (Medico m : GerenciadorMedicos.listar()) {
            model.addElement(m);
        }

        JList<Medico> lista = new JList<>(model);

        JButton btnExcluir = new JButton("Excluir");

        btnExcluir.addActionListener(e -> {
            Medico m = lista.getSelectedValue();
            if (m != null) {
                GerenciadorMedicos.removerPorCrm(m.getCrm());
                model.removeElement(m);
            }
        });

        frame.add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel rodape = new JPanel();
        rodape.add(btnExcluir);
        frame.add(rodape, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void setContentPane(JLabel fundo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setContentPane'");
    }
}
