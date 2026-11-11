package chifataitlung.controlador;

import chifataitlung.modelo.Rol;
import chifataitlung.modelo.Usuario;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// [EXTRAPOLADO]
// Servicio de autenticación local para el chifa.
// Maneja las cuentas fijas de ADMIN y EMPLEADO con hash de contraseña sin persistencia.
public class AutenticacionService {
    private Usuario usuarios[];

    public AutenticacionService(){
        usuarios = new Usuario[2];
        usuarios[0] = new Usuario("admin", calcularHash("admin123"), Rol.ADMIN);
        usuarios[1] = new Usuario("empleado", calcularHash("empleado123"), Rol.EMPLEADO);
    }

    // [EXTRAPOLADO]
    // Algoritmo de Hashing de Contraseña (SHA-256):
    // 1. Convierte el texto de la clave en bytes utilizando codificación UTF-8.
    // 2. Aplica la función resumen criptográfica SHA-256 mediante MessageDigest.
    // 3. Formatea cada byte del resumen en una representación hexadecimal de dos caracteres.
    // 4. Retorna la cadena hexadecimal resultante (claveHash).
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

    // [EXTRAPOLADO]
    // Algoritmo de Autenticación de Credenciales:
    // 1. Valida que el nombre de usuario y la contraseña no sean nulos ni vacíos.
    // 2. Calcula el hash SHA-256 de la contraseña proporcionada para comparación segura.
    // 3. Itera linealmente sobre el arreglo de usuarios locales registrados.
    // 4. Retorna la instancia de Usuario si coinciden nombre y hash; caso contrario retorna null.
    public Usuario autenticar(String usuario, String clave){
        if(usuario == null || clave == null) return null;
        String usuarioLimpio = usuario.trim();
        if(usuarioLimpio.isEmpty() || clave.isEmpty()) return null;

        String hashIngresado = calcularHash(clave);
        for(int i = 0; i < usuarios.length; i++){
            if(usuarios[i] != null && usuarios[i].getUsuario().equalsIgnoreCase(usuarioLimpio)){
                if(usuarios[i].getClaveHash().equals(hashIngresado) || usuarios[i].getClaveHash().equals(clave)){
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
