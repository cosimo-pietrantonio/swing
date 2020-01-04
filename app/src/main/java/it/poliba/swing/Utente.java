package it.poliba.swing;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Utente extends RealmObject implements Parcelable{


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


    public Utente() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    //metodi necessari per il passaggio di di oggetti tra activity
    @Override
    public int describeContents() {
        return 0;
    }

    private Utente(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        email = in.readString();
        nome= in.readString();
        cognome= in.readString();
        dataNascita= in.readString();
        password= in.readString();
    }

    public static final Parcelable.Creator<Utente> CREATOR
            = new Parcelable.Creator<Utente>() {
        public Utente createFromParcel(Parcel in) {
            return new Utente(in);
        }

        public Utente[] newArray(int size) {
            return new Utente[size];
        }
    };

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(email);
        out.writeString(nome);
        out.writeString(cognome);
        out.writeString(dataNascita);
        out.writeString(password);
    }

}
