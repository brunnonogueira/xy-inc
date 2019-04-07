package com.nogueira4j.xyinc.services;

import com.nogueira4j.xyinc.api.v1.model.PoiDTO;

import java.util.List;

public interface PoiService {

    List<PoiDTO> getAllPois();

    PoiDTO createNewPoi(PoiDTO poiDTO);

    List<PoiDTO> getAllPoisByProximity(int coordenadaX, int coordenadaY);
}
