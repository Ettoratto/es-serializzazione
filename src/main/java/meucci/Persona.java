package meucci;

public class Persona {
    String nome, cognome, sesso;
    int eta;

    public Persona(){
        
    }

    public Persona(String nome, String cognome, String sesso, int eta){
        
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.eta = eta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }
}
