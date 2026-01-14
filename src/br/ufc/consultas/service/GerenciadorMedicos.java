package service;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Medico;

public class GerenciadorMedicos {

    private static final String ARQUIVO = "src/br/ufc/consultas/dados/medicos.txt";
    private static List<Medico> medicos = new ArrayList<>();

    public static void adicionar(Medico m){
        carregar();

        boolean existe = medicos.stream()
                .anyMatch(med -> med.getCrm().equalsIgnoreCase(m.getCrm()));

        if (existe) {
            throw new RuntimeException("Já existe um médico cadastrado com este CRM.");
        }

        medicos.add(m);
        salvar();
    }

    public static List<Medico> listar(){
        carregar();
        return medicos;
    }

    // Nome ajustado (antes estava cpf, mas é CRM)
    public static boolean removerPorCrm(String crm){
        carregar();

        boolean removido = medicos.removeIf(m -> m.getCrm().equals(crm));

        if (removido) {
            salvar();
        }

        return removido;
    }

    private static void salvar() {
        try {
            new File("src/br/ufc/consultas/dados").mkdirs();
            PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO));

            for (Medico m : medicos) {
                pw.println(
                        m.getNome() + ";" +
                        m.getCrm() + ";" +
                        m.getEspecialidade()
                );
            }

            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void carregar() {
        medicos.clear();

        try (Scanner sc = new Scanner(new File(ARQUIVO))) {
            while (sc.hasNextLine()) {
                String[] p = sc.nextLine().split(";");
                medicos.add(new Medico(p[0], p[1], p[2]));
            }
        } catch (Exception e) {
            // ignora se não existir
        }
    }

    public static void remover(Medico medico) {
        carregar();
        medicos.remove(medico);
        salvar();
    }
}
