package ucv.codelab.model;

public class Usuario {
    
    private int id;
    private int idMedico;
    private String username;
    private transient String password;

    public Usuario(int id, int idMedico, String username, String password) {
        this.id = id;
        this.idMedico = idMedico;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
