package com.nogueira4j.xyinc.services;

import com.nogueira4j.xyinc.domain.Poi;
import com.nogueira4j.xyinc.api.v1.mapper.PoiMapper;
import com.nogueira4j.xyinc.api.v1.model.PoiDTO;
import com.nogueira4j.xyinc.repositories.PoiRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PoiServiceImplTest {

    @Mock
    PoiRepository poiRepository;

    PoiMapper poiMapper = PoiMapper.INSTANCE;

    PoiService poiService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        poiService = new PoiServiceImpl(poiMapper, poiRepository);
    }

    @Test
    public void getAllPois() throws Exception {
        //given
        Poi banco = new Poi();
        banco.setId(1l);
        banco.setName("Banco");
        banco.setCoordenadaX(19);
        banco.setCoordenadaY(12);

        Poi bar = new Poi();
        bar.setId(2l);
        bar.setName("Bar");
        bar.setCoordenadaX(20);
        bar.setCoordenadaY(3);

        when(poiRepository.findAll()).thenReturn(Arrays.asList(banco, bar));

        //when
        List<PoiDTO> poiDTOS = poiService.getAllPois();

        //then
        assertEquals(2, poiDTOS.size());
    }

    @Test
    public void createNewPoi() throws Exception {
        //given
        PoiDTO poiDTO = new PoiDTO();
        poiDTO.setName("banco");
        poiDTO.setCoordenadaX(19);
        poiDTO.setCoordenadaY(12);


        Poi savedPoi = new Poi();
        savedPoi.setName(poiDTO.getName());
        savedPoi.setCoordenadaX(poiDTO.getCoordenadaX());
        savedPoi.setCoordenadaY(poiDTO.getCoordenadaY());
        savedPoi.setId(1l);

        when(poiRepository.save(any(Poi.class))).thenReturn(savedPoi);

        //when
        PoiDTO savedDTO = poiService.createNewPoi(poiDTO);

        //then
        assertEquals(poiDTO.getName(), savedDTO.getName());
        assertEquals(poiDTO.getCoordenadaX(), savedDTO.getCoordenadaX());
        assertEquals(poiDTO.getCoordenadaY(), savedDTO.getCoordenadaY());
        assertEquals("/api/v1/pois/1", savedDTO.getPoiUrl());
    }

    @Test
    public void getAllPoisByProximity() throws Exception {

        int coordenadaX = 20;
        int coordenadaY = 10;

        //given
        Poi lanchonete = new Poi();
        lanchonete.setId(1l);
        lanchonete.setName("Lanchonete");
        lanchonete.setCoordenadaX(27);
        lanchonete.setCoordenadaY(12);

        Poi posto = new Poi();
        posto.setId(2l);
        posto.setName("Posto");
        posto.setCoordenadaX(31);
        posto.setCoordenadaY(18);

        Poi joalheria = new Poi();
        joalheria.setId(3l);
        joalheria.setName("Joalheria");
        joalheria.setCoordenadaX(15);
        joalheria.setCoordenadaY(12);

        when(poiRepository.findAll()).thenReturn(Arrays.asList(lanchonete, posto, joalheria));

        //when
        List<PoiDTO> poiDTOS = poiService.getAllPoisByProximity(coordenadaX, coordenadaY);

        //then
        assertEquals(2, poiDTOS.size());
    }
}
