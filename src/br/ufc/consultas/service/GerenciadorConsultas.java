package service;

import java.util.ArrayList;
import java.util.List;
import model.Consulta;

public class GerenciadorConsultas {

private List<Consulta> consultas = new ArrayList<>();    

public void agendar(Consulta c){
    consultas.add(c);
}

public List<Consulta> listar(){
    return consultas;
}
}