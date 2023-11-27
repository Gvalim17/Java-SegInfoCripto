package modelo;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;


public class Senha {
    private int id;
    private String chaveSecreta;

    public Senha() {

    }

    public Senha(String chaveSecreta) {
        this.chaveSecreta = chaveSecreta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChaveSecreta() {
        return chaveSecreta;
    }

    public void setChaveSecreta(String chaveSecreta) {
        this.chaveSecreta = chaveSecreta;
    }
}

