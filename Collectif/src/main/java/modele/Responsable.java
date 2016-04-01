package modele;

public class Responsable  {
    private String mail;
    private long mdp;

    public Responsable() {
        this.mail = "admin@collect_if.com";
        this.mdp = 10278393;
    }

    public String getMail() {
        return mail;
    }
    
    public long getMdp () {
        return mdp;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public void setMdp (long mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Responsable{" + "mail=" + mail + '}';
    }
}