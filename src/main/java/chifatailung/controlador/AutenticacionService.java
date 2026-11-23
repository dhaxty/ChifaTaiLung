package chifatailung.controlador;

import chifatailung.modelo.Rol;
import chifatailung.modelo.Usuario;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AutenticacionService {
    private Usuario usuarios[];

    public AutenticacionService(){
        usuarios = new Usuario[2];
        usuarios[0] = new Usuario("admin", calcularHash("admin123"), Rol.ADMIN);
        usuarios[1] = new Usuario("empleado", calcularHash("empleado123"), Rol.EMPLEADO);
    }

    // genera hash sha-256 para la clave
    public static String calcularHash(String clave){
        String hash = "";
        if(clave != null){
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte digest[] = md.digest(clave.getBytes(StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < digest.length; i++){
                    sb.append(String.format("%02x", digest[i]));
                }
                hash = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                hash = clave;
            }
        }
        return hash;
    }

    public Usuario autenticar(String usuario, String clave){
        Usuario encontrado = null;
        if(usuario != null && clave != null){
            String usuarioLimpio = usuario.trim();
            if(!usuarioLimpio.isEmpty() && !clave.isEmpty()){
                String hashIngresado = calcularHash(clave);
                int i = 0;
                while(i < usuarios.length && encontrado == null){
                    if(usuarios[i] != null && usuarios[i].getUsuario().equalsIgnoreCase(usuarioLimpio)){
                        if(usuarios[i].getClaveHash().equals(hashIngresado)){
                            encontrado = usuarios[i];
                        }
                    }
                    i++;
                }
            }
        }
        return encontrado;
    }

    public Usuario[] getUsuarios(){
        return usuarios;
    }
}
