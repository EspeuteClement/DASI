package fr.insalyon.dasi.collectif.modele;

public class Responsable  {
    private String mail;
    private String mdp;

    public Responsable() {
        this.mail = "admin";
        this.mdp = "admin";
    }

    public String getMail() {
        return mail;
    }
    
    public String getMdp () {
        return mdp;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public void setMdp (String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Responsable{" + "mail=" + mail + '}';
    }
}