/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroshoes;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author ricar
 */
public class ProducirSuela extends Thread {
    private Suela suela;
    private Semaphore semSincronizacion;
    private Semaphore semExclusionMutua;
    private Semaphore semBuffer;
    private Buffer buffer;
    private int idOperador;
    private JTextArea texto;
    private JLabel totalLabel;
    private boolean sigue = true;
    
    public ProducirSuela(Buffer buffer, Semaphore semSincronizacion, Semaphore semExclusionMutua, Semaphore semBuffer, int idOperador, JTextArea texto, JLabel total) {
        this.semSincronizacion = semSincronizacion;
        this.semExclusionMutua = semExclusionMutua;
        this.semBuffer = semBuffer;
        this.buffer = buffer;
        this.idOperador = idOperador;
        this.texto = texto;
        this.totalLabel = total;
    }
    
    public void run() {
        while(sigue) {
            suela = new Suela();
            try {
                semBuffer.acquire();
                semExclusionMutua.acquire();
                buffer.producirSuela(suela);
                sleep(Suela.TIEMPO*1000);
                semExclusionMutua.release();
                semSincronizacion.release();
                texto.append("Se produjo una nueva suela por el operador "+this.idOperador+"\n");
                totalLabel.setText(Integer.toString(semSincronizacion.availablePermits()));
            } catch (InterruptedException ex) {
                Logger.getLogger(ProducirSuela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void detener() {
        sigue = false;
    }
}
