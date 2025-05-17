/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.config;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String gerarMD5(String senha) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(senha.getBytes(), 0, senha.length());
            return String.format("%032x", new BigInteger(1, m.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash MD5", e);
        }
    }
}
