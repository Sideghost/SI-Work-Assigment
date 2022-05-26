package isel.sisinf.grp3.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Veiculo {
    @Id
    private String matricula;
    private String nome_condutor;
    private String telefone_condutor;
    private String NIF;

    private int n_alarmes;

    public String getVeiculoMatricula() {
        return matricula;
    }
    public void setVeiculoMatricula(String VeiculoMatricula) {
        this.matricula = VeiculoMatricula;
    }

    public void setVeiculoTelefoneCondutor(String VeiculoTelefoneCondutor) {
        this.telefone_condutor = VeiculoTelefoneCondutor;
    }

    public void setVeiculoCondutor(String VeiculoNomeCondutor) {
        this.nome_condutor = VeiculoNomeCondutor;
    }
    public String getVeiculoTelefoneCondutor() {
        return telefone_condutor;
    }

    public void setVeiculoNIF(String VeiculoNIF) {
        this.NIF = VeiculoNIF;
    }
    public String getVeiculoNomeCondutor() {
        return nome_condutor;
    }

    @Override
    public boolean equals (Object other) {
        if (this == other) {
            return true;
        }
        if(!(other instanceof Veiculo)){return false;}
        Veiculo castOther = (Veiculo)other;
        return (this.matricula.equals(castOther.matricula));
    }
    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
