package model;
import java.time.LocalDateTime;

public class Consulta {
    private static int contador = 0;

    private int id;
    private Medico medico;
    private Paciente paciente;
    private LocalDateTime dataHora;

public Consulta(Medico medico, Paciente paciente, LocalDateTime dataHora){
      this.id = ++contador;
        this.medico = medico;
        this.paciente = paciente;
        this.dataHora = dataHora;
}

public LocalDateTime getDataHora() {
        return dataHora;
    }

public String toString() {
        return id + ";" + medico.getDescricao() + ";" + paciente.getDescricao() + ";" + dataHora;
    }
}
