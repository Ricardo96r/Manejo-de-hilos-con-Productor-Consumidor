/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroshoes;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author ricar
 */
public class ElaborarZapato extends Thread {
    private Semaphore semTrenzaS; // S = Sincronizacion
    private Semaphore semTrenzaEM; // EM = Exclusion Mutua
    private Semaphore semTrenzaB; // BT = Buffer Trenza
    private Semaphore semSuelaS; 
    private Semaphore semSuelaEM; 
    private Semaphore semSuelaB; 
    private Semaphore semPlantillaS;
    private Semaphore semPlantillaEM;
    private Semaphore semPlantillaB;
    Buffer BufferSuela;
    Buffer BufferPlantilla;
    Buffer BufferTrenza;
    private int zapato1;
    private int zapato2;
    private int IdOperador;
    private JTextArea texto;
    private JLabel zapatoTipoI;
    private JLabel zapatoTipoII;
    private boolean sigue = true;
    private JLabel totalTrenzas;
    private JLabel totalSuelas;
    private JLabel totalPlantillas;
    
    public ElaborarZapato(Semaphore semTrenzaS, Semaphore semTrenzaEM, Semaphore semTrenzaB, Semaphore semSuelaS, Semaphore semSuelaEM, Semaphore semSuelaB, Semaphore semPlantillaS, Semaphore semPlantillaEM, Semaphore semPlantillaB, Buffer trenza, Buffer suela, Buffer plantilla, int zapato1, int zapato2, int IdOperador, JTextArea texto, JLabel zapatoTipoI, JLabel zapatoTipoII, JLabel totalTrenzas, JLabel totalSuelas, JLabel totalPlantillas) {
        this.semTrenzaS = semTrenzaS;
        this.semTrenzaEM = semTrenzaEM;
        this.semTrenzaB = semTrenzaB;
        this.semSuelaS = semSuelaS; 
        this.semSuelaEM = semSuelaEM; 
        this.semSuelaB = semSuelaB; 
        this.semPlantillaS = semPlantillaS;
        this.semPlantillaEM = semPlantillaEM;
        this.semPlantillaB = semPlantillaB;
        this.BufferSuela = suela;
        this.BufferPlantilla = plantilla;
        this.BufferTrenza = trenza;
        this.zapato1 = zapato1;
        this.zapato2 = zapato2;
        this.IdOperador = IdOperador;
        this.texto = texto;
        this.zapatoTipoI = zapatoTipoI;
        this.zapatoTipoII = zapatoTipoII;
        this.totalTrenzas = totalTrenzas;
        this.totalSuelas = totalSuelas;
        this.totalPlantillas = totalPlantillas;
    }
    
    public void run() {
        while (sigue) {
            if (zapato1+zapato2 != 0) {
            try {
                if (zapato1 != 0) {
                    zapato1--;
                    int trenza = 0;
                    int suela = 0;
                    int plantilla = 0;
                    if (!sigue) break;
                    while(trenza < 2) {
                    if (!sigue) break;
                        semTrenzaS.acquire();
                        texto.append("El operador "+IdOperador+" ha tomado una trenza"+"\n");
                        totalTrenzas.setText(Integer.toString(semTrenzaS.availablePermits()));
                        trenza++;
                        semTrenzaEM.acquire();
                        Trenza t = BufferSuela.consumirTrenza();
                        semTrenzaEM.release();
                        semTrenzaB.release();
                        
                    }
                    if (!sigue) break;
                    while (suela < 1) {
                        semSuelaS.acquire();
                        texto.append("El operador "+IdOperador+" ha tomado una suela"+"\n");
                        totalSuelas.setText(Integer.toString(semSuelaS.availablePermits()));
                        suela++;
                        semSuelaEM.acquire();
                        Suela s = BufferSuela.consumirSuela();
                        semSuelaEM.release();
                        semSuelaB.release();
                        this.sleep(4000);
                        texto.append("El operador "+IdOperador+" ha unido dos trenzas en una suela (ZAPATO TIPO 1)"+"\n");
                    }
                    if (!sigue) break;
                    while (plantilla < 1) {   
                        semPlantillaS.acquire();
                        texto.append("El operador "+IdOperador+" ha tomado una plantilla"+"\n");
                        totalPlantillas.setText(Integer.toString(semPlantillaS.availablePermits()));
                        plantilla++;
                        semPlantillaEM.acquire();
                        Plantilla p = BufferPlantilla.consumirPlantilla();
                        semPlantillaEM.release();
                        semPlantillaB.release();
                        this.sleep(3000);
                        texto.append("El operador "+IdOperador+" ha unido la plantilla con la otra parte del zapato (ZAPATO TIPO 1)"+"\n");
                        this.sleep(8000);
                        texto.append("El operador "+IdOperador+" ha pintado un zapato tipo I. Ha completado con exito un zapato"+"\n");
                        zapatoTipoI.setText(Integer.toString(Integer.parseInt((zapatoTipoI.getText()))+1));
                    }
                } else {
                    texto.append("Se ha terminado el lote de zapatos Tipo I del dia de hoy"+"\n");
                }
                if (zapato2 != 0) {
                    zapato2--;
                    int trenza = 0;
                    int suela = 0;
                    int plantilla = 0;
                    if (!sigue) break;
                    while(trenza < 3) {
                        if (!sigue) break;
                        semTrenzaS.acquire();
                        texto.append("El operador "+IdOperador+" ha tomado una trenza"+"\n");
                        totalTrenzas.setText(Integer.toString(semTrenzaS.availablePermits()));
                        trenza++;
                        semTrenzaEM.acquire();
                        Trenza t = BufferSuela.consumirTrenza();
                        semTrenzaEM.release();
                        semTrenzaB.release();
                    }
                    if (!sigue) break;
                    while (suela < 1) {
                        semSuelaS.acquire();
                        texto.append("El operador "+IdOperador+" ha tomado una suela"+"\n");
                        totalSuelas.setText(Integer.toString(semSuelaS.availablePermits()));
                        suela++;
                        semSuelaEM.acquire();
                        Suela s = BufferSuela.consumirSuela();
                        semSuelaEM.release();
                        semSuelaB.release();
                        this.sleep(6000);
                        texto.append("El operador "+IdOperador+" ha unido las 3 trenzas con la suela (ZAPATO TIPO 2)"+"\n");
                    }
                    if (!sigue) break;
                    while (plantilla < 1) {   
                        semPlantillaS.acquire();
                        texto.append("El operador "+IdOperador+" ha tomado una plantilla"+"\n");
                        totalPlantillas.setText(Integer.toString(semPlantillaS.availablePermits()));
                        plantilla++;
                        semPlantillaEM.acquire();
                        Plantilla p = BufferPlantilla.consumirPlantilla();
                        semPlantillaEM.release();
                        semPlantillaB.release();
                        this.sleep(5000);
                        texto.append("El operador "+IdOperador+" ha unido la plantilla con la otra parte del zapato (ZAPATO TIPO 2)"+"\n");
                        this.sleep(6000);
                        texto.append("El operador "+IdOperador+" ha pintado un zapato tipo 2. Ha completado con exito un zapato"+"\n");
                        zapatoTipoII.setText(Integer.toString(Integer.parseInt((zapatoTipoII.getText()))+1));
                    }
                } else {
                    texto.append("Se ha terminado el lote de zapatos Tipo II del dia de hoy"+"\n");
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ElaborarZapato.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            sigue = false;
            texto.append("Se ha terminado el lote del dia"+"\n");
        }
        }
    }
    
    public void detener() {
        sigue = false;
    }
    
}
