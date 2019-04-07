package com.nogueira4j.xyinc.bootstrap;

import com.nogueira4j.xyinc.domain.Poi;
import com.nogueira4j.xyinc.repositories.PoiRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final PoiRepository poiRepository;

    public Bootstrap(PoiRepository poiRepository) {
        this.poiRepository = poiRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadPois();
    }

    private void loadPois() {
        Poi lanchonete = new Poi();
        lanchonete.setId(1l);
        lanchonete.setName("Lanchonete");
        lanchonete.setCoordenadaX(27);
        lanchonete.setCoordenadaY(12);

        poiRepository.save(lanchonete);

        Poi posto = new Poi();
        posto.setId(2l);
        posto.setName("Posto");
        posto.setCoordenadaX(31);
        posto.setCoordenadaY(18);

        poiRepository.save(posto);

        Poi joalheria = new Poi();
        joalheria.setId(3l);
        joalheria.setName("Joalheria");
        joalheria.setCoordenadaX(15);
        joalheria.setCoordenadaY(12);

        poiRepository.save(joalheria);

        Poi floricultura = new Poi();
        floricultura.setId(4l);
        floricultura.setName("Floricultura");
        floricultura.setCoordenadaX(19);
        floricultura.setCoordenadaY(21);

        poiRepository.save(floricultura);

        Poi pub = new Poi();
        pub.setId(5l);
        pub.setName("Pub");
        pub.setCoordenadaX(12);
        pub.setCoordenadaY(8);

        poiRepository.save(pub);

        Poi supermercado = new Poi();
        supermercado.setId(6l);
        supermercado.setName("Supermercado");
        supermercado.setCoordenadaX(23);
        supermercado.setCoordenadaY(6);

        poiRepository.save(supermercado);

        Poi churrascaria = new Poi();
        churrascaria.setId(7l);
        churrascaria.setName("Churrascaria");
        churrascaria.setCoordenadaX(28);
        churrascaria.setCoordenadaY(2);

        poiRepository.save(churrascaria);

        System.out.println("Pois Loaded: " + poiRepository.count());
    }
}
