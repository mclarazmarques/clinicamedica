package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consulta {
    private static int contador = 0;

    private int id;
    private Medico medico;
    private Paciente paciente;
    private LocalDateTime dataHora;

    private StatusConsulta status;


public Consulta(Medico medico, Paciente paciente, LocalDateTime dataHora){
      this.id = ++contador;
        this.medico = medico;
        this.paciente = paciente;
        this.dataHora = dataHora;
         this.status = StatusConsulta.PENDENTE;
}

public StatusConsulta getStatus() {
    return status;
}

public void setStatus(StatusConsulta status) {
    this.status = status;
}


public LocalDateTime getDataHora() {
        return dataHora;
    }

public Medico getMedico() {
    return medico;
}

public Paciente getPaciente() {
    return paciente;
}
public void setDataHora(LocalDateTime dataHora) {
    this.dataHora = dataHora;
}

@Override
public String toString() {
    return "Paciente: " + paciente.getNome() +
           " | Médico: " + medico.getNome() +
           " | Data: " + dataHora.format(
                 DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
             ) +
           " | Status: " + status;
}


}
