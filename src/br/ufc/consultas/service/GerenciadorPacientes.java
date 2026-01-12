package service;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Paciente;

public class GerenciadorPacientes {

    private static final String ARQUIVO = "src/br/ufc/consultas/dados/pacientes.txt";
    private static List<Paciente> pacientes = new ArrayList<>();

    public static void adicionar(Paciente p){
        carregar();
        pacientes.add(p);
        salvar();
    }

    public static List<Paciente> listar(){
        carregar();
        return pacientes;
    }

    public static boolean removerPorCpf(String cpf){
        carregar();

        boolean removido = pacientes.removeIf(p -> p.getCpf().equals(cpf));

        if (removido) {
            salvar();
        }

        return removido;
    }

    private static void salvar() {
        try {
            new File("src/br/ufc/consultas/dados").mkdirs();
            PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO));

            for (Paciente p : pacientes) {
                pw.println(p.getNome() + ";" + p.getCpf() + ";" + p.planoSaude);
            }

            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void carregar() {
        pacientes.clear();

        try (Scanner sc = new Scanner(new File(ARQUIVO))) {
            while (sc.hasNextLine()) {
                String[] p = sc.nextLine().split(";");
                pacientes.add(new Paciente(p[0], p[1], p[2]));
            }
        } catch (Exception e) {
            // ignora se não existir
        }
    }

    public static void remover(Paciente paciente) {
    carregar();
    pacientes.remove(paciente);
    salvar();
}

}
