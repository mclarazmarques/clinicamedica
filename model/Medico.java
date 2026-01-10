package model;
public class Medico extends Pessoa{
    public String especialidade;

public Medico(String nome, String cpf, String especialidade){
    super(nome, cpf);
    this.especialidade = especialidade;
}

@Override
public String getDescricao(){
    return "O médico" + getNome()+ " possui especilidade: " + especialidade;
}
}
