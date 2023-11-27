package modelo;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

public class Hemoglobina {

    private int id;
    private String resultado;

    public Hemoglobina(){

    }

    public Hemoglobina(String resultado) {

        this.resultado = resultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int idHemoglobina) {
        this.id = idHemoglobina;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }





}



