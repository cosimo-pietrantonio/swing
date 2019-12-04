// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package it.poliba.swing;
import io.realm.RealmObject;
import io.realm.annotations.Required;
import java.util.Date;
import io.realm.annotations.PrimaryKey;

public class utente extends RealmObject {
    @PrimaryKey
    @Required
    private String email;
    @Required
    private String password;
    @Required
    private String nome;
    @Required
    private String cognome;
    @Required
    private String dataNascita;


    public utente() {
    }


    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }

    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getDataNascita() { return dataNascita; }

    public void setDataNascita(String dataNascita) { this.dataNascita = dataNascita; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }


}
