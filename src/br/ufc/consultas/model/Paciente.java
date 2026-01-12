package model;
public class Paciente extends Pessoa{
    public String planoSaude;

public Paciente(String nome, String cpf, String planoSaude){
    super(nome, cpf);
    this.planoSaude = planoSaude;
}
@Override
public String getDescricao(){
    return "O paciente " + getNome() + "possui o plano de saúde: " + planoSaude;
}

@Override
public String toString() {
    return "Nome: " + getNome() + " | CPF: " + getCpf() + " | Plano de Saúde: " + planoSaude;
}
}
