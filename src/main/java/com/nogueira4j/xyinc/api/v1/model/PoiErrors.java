package com.nogueira4j.xyinc.api.v1.model;

public enum PoiErrors {
    NAME_REQUIRED("1000", "The attribute name is required and not null."),
    COORDENADA_NOT_NEGATIVE("1001", "The attribute coordenadaX and coordenadaY is not negative value.");

    private final String code;
    private final String description;

    PoiErrors(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() { return code; }
    public String getDescription() { return description; }
}
