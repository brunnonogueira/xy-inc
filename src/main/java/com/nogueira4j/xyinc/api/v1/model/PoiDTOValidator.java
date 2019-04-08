package com.nogueira4j.xyinc.api.v1.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class PoiDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PoiDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        PoiDTO poiDTO = (PoiDTO) target;

        if(poiDTO.getName() == null) {
            errors.rejectValue("name", PoiErrors.NAME_REQUIRED.name());
        }

        if(poiDTO.getCoordenadaX() < 0 || poiDTO.getCoordenadaY() < 0) {
            errors.rejectValue("coordenadaX", PoiErrors.COORDENADA_NOT_NEGATIVE.getCode(), PoiErrors.COORDENADA_NOT_NEGATIVE.getDescription());
        }
    }
}
