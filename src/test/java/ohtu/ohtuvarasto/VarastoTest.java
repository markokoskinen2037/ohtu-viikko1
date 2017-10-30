package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void kayttokelvotonvarastoTunnistetaan() {
        Varasto testivarasto = new Varasto(-1);
        assertEquals(0, testivarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonTilavuusAsetetaanOikein() {
        Varasto testivarasto = new Varasto(2, 2);
        assertEquals(testivarasto.getTilavuus(), 2, vertailuTarkkuus);
    }

    @Test
    public void varastonTilavuusttaEiAsetetaJosNegatiivinen() {
        Varasto testivarasto = new Varasto(-2, 2);
        assertEquals(testivarasto.getTilavuus(), 0, vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldoAsetetaanOikein(){
        Varasto testivarasto = new Varasto(2, -1);
        assertEquals(varasto.getSaldo(),0, vertailuTarkkuus);
    }
    
    @Test
    public void lisaaminenToimiiNegatiivisilla(){
        varasto.lisaaVarastoon(-2);
        assertEquals(varasto.getTilavuus(),10,vertailuTarkkuus);
    }
    
    @Test
    public void varastoTäyttyyRajaanAsti(){
        varasto.lisaaVarastoon(1000);
        assertEquals(varasto.getSaldo(),10,vertailuTarkkuus);
    }
    
    @Test 
    public void negatiivinenOttoToimii(){
        varasto.otaVarastosta(-1);
        assertEquals(varasto.getSaldo(), 0,vertailuTarkkuus);
    }
    
    @Test
    public void otetaanKaikkiMitäVoi(){
        assertEquals(varasto.otaVarastosta(10000), 0, vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii(){
        assertEquals(varasto.toString(), "saldo = 0.0, vielä tilaa 10.0");
    }

}
