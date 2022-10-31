/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Excepciones;

/**
 *
 * @author ivanb
 */
public class ManagementException extends Exception {
    
    private int errCode;
    
    public ManagementException(String message, int errCode) {
        super(message);
        this.errCode = errCode;
    }

    public ManagementException(String message) {
        super(message);
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
