package model;

public class Paciente {

    private String nome;
    private String cpf;
    private String planoSaude;

    public Paciente(String nome, String cpf, String planoSaude) {
        this.nome = nome;
        this.cpf = cpf;
        this.planoSaude = planoSaude;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPlanoSaude() {
        return planoSaude;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPlanoSaude(String planoSaude) {
        this.planoSaude = planoSaude;
    }

    @Override
    public String toString() {
        return nome + " | CPF: " + cpf + " | Plano: " + planoSaude;
    }

}
