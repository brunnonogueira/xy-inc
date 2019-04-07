package com.nogueira4j.xyinc.controllers;

import com.nogueira4j.xyinc.api.v1.model.PoiDTO;
import com.nogueira4j.xyinc.controllers.v1.PoiController;
import com.nogueira4j.xyinc.services.PoiService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PoiControllerTest extends AbstractRestControllerTest {

    @Mock
    PoiService poiService;

    @InjectMocks
    PoiController poiController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(poiController)
                .build();
    }

    @Test
    public void getListOfPois() throws Exception {

        //given
        PoiDTO bancoDTO = new PoiDTO();
        bancoDTO.setName("Banco");
        bancoDTO.setCoordenadaX(19);
        bancoDTO.setCoordenadaY(12);
        bancoDTO.setPoiUrl(PoiController.BASE_URL + "/1");

        PoiDTO barDTO = new PoiDTO();
        barDTO.setName("Bar");
        barDTO.setCoordenadaX(20);
        barDTO.setCoordenadaY(3);
        barDTO.setPoiUrl(PoiController.BASE_URL + "/2");

        when(poiService.getAllPois()).thenReturn(Arrays.asList(bancoDTO, barDTO));

        mockMvc.perform(get(PoiController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pois", hasSize(2)));
    }

    @Test
    public void createNewPoi() throws Exception {
        //given
        PoiDTO poi = new PoiDTO();
        poi.setName("Banco");
        poi.setCoordenadaX(19);
        poi.setCoordenadaY(12);

        PoiDTO returnDTO = new PoiDTO();
        returnDTO.setName(poi.getName());
        returnDTO.setCoordenadaX(poi.getCoordenadaX());
        returnDTO.setCoordenadaY(poi.getCoordenadaY());
        returnDTO.setPoiUrl(PoiController.BASE_URL + "/1");

        when(poiService.createNewPoi(poi)).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(post(PoiController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(poi)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Banco")))
                .andExpect(jsonPath("$.coordenadaX", equalTo(19)))
                .andExpect(jsonPath("$.coordenadaY", equalTo(12)))
                .andExpect(jsonPath("$.poi_url", equalTo(PoiController.BASE_URL + "/1")));
    }

    @Test
    public void getListOfPoisByProximity() throws Exception {

        int coordenadaX = 20;
        int coordenadaY = 10;

        //given
        PoiDTO lanchoneteDTO = new PoiDTO();
        lanchoneteDTO.setName("Lanchonete");
        lanchoneteDTO.setCoordenadaX(27);
        lanchoneteDTO.setCoordenadaY(12);
        lanchoneteDTO.setPoiUrl(PoiController.BASE_URL + "/1");


        PoiDTO joalheriaDTO = new PoiDTO();
        joalheriaDTO.setName("Joalheria");
        joalheriaDTO.setCoordenadaX(15);
        joalheriaDTO.setCoordenadaY(12);
        joalheriaDTO.setPoiUrl(PoiController.BASE_URL + "/3");

        when(poiService.getAllPoisByProximity(coordenadaX, coordenadaY)).thenReturn(Arrays.asList(lanchoneteDTO, joalheriaDTO));

        mockMvc.perform(get(PoiController.BASE_URL + "/"+coordenadaX+"&"+coordenadaY)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pois", hasSize(2)));
    }

}
