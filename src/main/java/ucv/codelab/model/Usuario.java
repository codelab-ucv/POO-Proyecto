package ucv.codelab.model;

public class Usuario {
    private int idUsuario;
    private int idMedico;
    private String username;
    private transient String password;
    private boolean estado;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor esencial (todos los campos son NOT NULL)
    public Usuario(int idUsuario, int idMedico, String username, String password, boolean estado) {
        this.idUsuario = idUsuario;
        this.idMedico = idMedico;
        this.username = username;
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}