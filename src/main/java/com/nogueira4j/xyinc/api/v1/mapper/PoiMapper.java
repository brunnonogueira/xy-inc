package com.nogueira4j.xyinc.api.v1.mapper;

import com.nogueira4j.xyinc.domain.Poi;
import com.nogueira4j.xyinc.api.v1.model.PoiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PoiMapper {

    PoiMapper INSTANCE = Mappers.getMapper(PoiMapper.class);

    PoiDTO poiToPoiDTO(Poi poi);

    Poi poiDtoToPoi(PoiDTO poiDTO);
}
