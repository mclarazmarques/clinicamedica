package service;

import model.Consulta;
import model.Medico;
import model.Paciente;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorConsultas {

    private static final String ARQUIVO = "src/br/ufc/consultas/dados/consultas.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static List<Consulta> consultas = new ArrayList<>();

    // Salva no arquivo
    private static void salvar() {
        try {
            new File("src/br/ufc/consultas/dados").mkdirs();
            PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO));
            for (Consulta c : consultas) {
                pw.println(
                        c.getMedico().getNome() + ";" +
                        c.getMedico().getCrm() + ";" +
                        c.getPaciente().getNome() + ";" +
                        c.getPaciente().getCpf() + ";" +
                        c.getDataHora().format(FORMATTER)
                );
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Carrega do arquivo
    private static void carregar() {
        consultas.clear();
        try (Scanner sc = new Scanner(new File(ARQUIVO))) {
            while (sc.hasNextLine()) {
                String[] c = sc.nextLine().split(";");
                // Recupera paciente e médico existentes pelo gerenciador
                Paciente paciente = service.GerenciadorPacientes.listar()
                        .stream()
                        .filter(p -> p.getCpf().equals(c[3]))
                        .findFirst()
                        .orElse(new Paciente(c[2], c[3], "")); // cria temporário se não existir
                Medico medico = service.GerenciadorMedicos.listar()
                        .stream()
                        .filter(m -> m.getCrm().equals(c[1]))
                        .findFirst()
                        .orElse(new Medico(c[0], c[1], null)); // cria temporário se não existir

                LocalDateTime dataHora = LocalDateTime.parse(c[4], FORMATTER);
                consultas.add(new Consulta(medico, paciente, dataHora));
            }
        } catch (Exception e) {
            // ignora se arquivo não existir
        }
    }

    public void agendar(Consulta nova) {
        carregar();

        boolean conflito = consultas.stream().anyMatch(c ->
                c.getMedico().getCrm().equalsIgnoreCase(nova.getMedico().getCrm())
                        && c.getDataHora().equals(nova.getDataHora())
        );

        if (conflito) {
            throw new RuntimeException("Este médico já possui consulta nesse dia e horário.");
        }

        consultas.add(nova);
        salvar();
    }

    public void remover(Consulta consulta) {
        carregar();
        consultas.remove(consulta);
        salvar();
    }

    public List<Consulta> listar() {
        carregar();
        return consultas;
    }
}
