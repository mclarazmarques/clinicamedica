package model;

public class Medico extends Pessoa {
    public String especialidade;

    public Medico(String nome, String cpf, String especialidade){
        super(nome, cpf);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    @Override
    public String getDescricao(){
        return "O médico " + getNome() + " possui especialidade: " + especialidade;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + " | CPF: " + getCpf() + " | Especialidade: " + especialidade;
    }
}
