package com.nogueira4j.xyinc.mapper;

import com.nogueira4j.xyinc.api.v1.mapper.PoiMapper;
import com.nogueira4j.xyinc.domain.Poi;
import com.nogueira4j.xyinc.api.v1.model.PoiDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PoiMapperTest {

    public static final String NAME = "Banco";
    public static final int COORDENADA_X = 19;
    public static final int COORDENADA_Y = 12;
    PoiMapper poiMapper = PoiMapper.INSTANCE;

    @Test
    public void poiToPoiDTO() throws Exception {
        //given
        Poi poi = new Poi();
        poi.setId(1L);
        poi.setName(NAME);
        poi.setCoordenadaX(COORDENADA_X);
        poi.setCoordenadaY(COORDENADA_Y);

        //when
        PoiDTO poiDTO = poiMapper.poiToPoiDTO(poi);

        //then
        assertEquals(NAME, poiDTO.getName());
        assertEquals(COORDENADA_X, poiDTO.getCoordenadaX());
        assertEquals(COORDENADA_Y, poiDTO.getCoordenadaY());
    }
}
