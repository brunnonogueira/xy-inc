package com.nogueira4j.xyinc.services;

import com.nogueira4j.xyinc.controllers.v1.PoiController;
import com.nogueira4j.xyinc.domain.Poi;
import static com.nogueira4j.xyinc.domain.PoiPredicate.isDistanceLessThanMax;
import com.nogueira4j.xyinc.api.v1.mapper.PoiMapper;
import com.nogueira4j.xyinc.api.v1.model.PoiDTO;
import com.nogueira4j.xyinc.repositories.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PoiServiceImpl implements PoiService {

    private final PoiMapper poiMapper;
    private final PoiRepository poiRepository;

    @Autowired
    public PoiServiceImpl(PoiMapper poiMapper, PoiRepository poiRepository){
        this.poiMapper = poiMapper;
        this.poiRepository = poiRepository;
    }

    @Override
    public List<PoiDTO> getAllPois() {
        return poiRepository
                .findAll()
                .stream()
                .map(poi -> {
                    PoiDTO poiDTO = poiMapper.poiToPoiDTO(poi);
                    poiDTO.setPoiUrl(getPoiUrl(poi.getId()));
                    return poiDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PoiDTO createNewPoi(PoiDTO poiDTO) {
        return saveAndReturnDTO(poiMapper.poiDtoToPoi(poiDTO));
    }

    private PoiDTO saveAndReturnDTO(Poi poi) {
        Poi savedPoi = poiRepository.save(poi);

        PoiDTO returnPoiDTO = poiMapper.poiToPoiDTO(savedPoi);

        returnPoiDTO.setPoiUrl(getPoiUrl(savedPoi.getId()));
        return returnPoiDTO;
    }

    private String getPoiUrl(Long id) {
        return PoiController.BASE_URL + "/" + id;
    }

    @Override
    public List<PoiDTO> getAllPoisByProximity(int coordenadaX, int coordenadaY) {

        return poiRepository
                .findAll()
                .stream()
                .filter(poi ->
                    isDistanceLessThanMax()
                            .test(calcDistancePoi(poi, coordenadaX, coordenadaY))
                )
                .map(poi -> {
                    PoiDTO poiDTO = poiMapper.poiToPoiDTO(poi);
                    poiDTO.setPoiUrl(getPoiUrl(poi.getId()));
                    return poiDTO;
                })
                .collect(Collectors.toList());
    }

    private double calcDistancePoi(Poi poi, int x2, int y2) {
        return Math.sqrt(Math.pow((x2 - poi.getCoordenadaX()), 2) + Math.pow((y2 - poi.getCoordenadaY()), 2));
    }
}
