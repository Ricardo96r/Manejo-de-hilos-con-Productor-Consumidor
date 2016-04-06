/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroshoes;

/**
 *
 * @author ricar
 */
public class Buffer {
    private Trenza trenzas[];
    private Suela suelas[];
    private Plantilla plantillas[];
    private int trenzaEntra;
    private int trenzaSale;
    private int suelaEntra;
    private int suelaSale;
    private int plantillaEntra;
    private int plantillaSale;
    
    public Buffer() {
        this.trenzas = new Trenza[Trenza.BUFFER];
        this.suelas = new Suela[Suela.BUFFER];
        this.plantillas = new Plantilla[Plantilla.BUFFER];
        this.trenzaEntra = 0;
        this.trenzaSale = 0;
        this.suelaSale = 0;
        this.suelaEntra = 0;
        this.plantillaEntra = 0;
        this.plantillaSale = 0;
    }
    
    public void producirTrenza(Trenza trenza)
    {
        this.trenzas[trenzaEntra] = trenza;
        trenzaEntra = (trenzaEntra +1) % Trenza.BUFFER;
         
    }
    
    public void producirSuela(Suela suela)
    {
        this.suelas[suelaEntra] = suela;
        suelaEntra = (suelaEntra +1) % Suela.BUFFER;
         
    }
   
    public void producirPlantilla(Plantilla plantilla)
    {
        this.plantillas[plantillaEntra] = plantilla;
        plantillaEntra = (plantillaEntra +1) % Plantilla.BUFFER;
    }
    
    public Trenza consumirTrenza()
    {
        Trenza trenza = this.trenzas[trenzaSale];
        trenzaSale = (trenzaSale +1) % Trenza.BUFFER;
        return trenza;   
    }
    
    public Suela consumirSuela()
    {
        Suela suela = this.suelas[suelaSale];
        suelaSale = (suelaSale +1) % Suela.BUFFER;
        return suela;  
    }
    
    public Plantilla consumirPlantilla()
    {
        Plantilla plantilla = this.plantillas[plantillaSale];
        plantillaSale = (plantillaSale +1) % Plantilla.BUFFER;
        return plantilla;   
    }
    
}
