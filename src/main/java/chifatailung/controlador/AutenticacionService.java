package chifatailung.controlador;

import chifatailung.modelo.Rol;
import chifatailung.modelo.Usuario;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// servicio local con las cuentas fijas de admin y empleado
public class AutenticacionService {
    private Usuario usuarios[];

    public AutenticacionService(){
        usuarios = new Usuario[2];
        usuarios[0] = new Usuario("admin", calcularHash("admin123"), Rol.ADMIN);
        usuarios[1] = new Usuario("empleado", calcularHash("empleado123"), Rol.EMPLEADO);
    }

    // genera el hash SHA-256 de la contrasena para no guardarla en texto plano
    public static String calcularHash(String clave){
        if(clave == null) return "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte digest[] = md.digest(clave.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < digest.length; i++){
                sb.append(String.format("%02x", digest[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return clave;
        }
    }

    // busca el usuario y compara el hash de la clave; devuelve null si falla
    public Usuario autenticar(String usuario, String clave){
        if(usuario == null || clave == null) return null;
        String usuarioLimpio = usuario.trim();
        if(usuarioLimpio.isEmpty() || clave.isEmpty()) return null;

        String hashIngresado = calcularHash(clave);
        for(int i = 0; i < usuarios.length; i++){
            if(usuarios[i] != null && usuarios[i].getUsuario().equalsIgnoreCase(usuarioLimpio)){
                if(usuarios[i].getClaveHash().equals(hashIngresado)){
                    return usuarios[i];
                }
            }
        }
        return null;
    }

    public Usuario[] getUsuarios(){
        return usuarios;
    }
}
