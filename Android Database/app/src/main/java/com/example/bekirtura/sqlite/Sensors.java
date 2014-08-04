package com.example.bekirtura.sqlite;

import java.io.Serializable;

/**
 * Created by bekirtura on 17/07/14.
 */
public class Sensors implements Serializable {
    private   String COLUMNS_X="x";
    private   String COLUMNS_Y="y";
    private   String COLUMNS_Z="z";
    private   double Rate=0.0;
    private   int id=0;

    public Sensors() {
        super();
    }
    public Sensors(int id,double Rate,String COLUMNS_X,String COLUMNS_Y,String COLUMNS_Z){
        super();
        this.COLUMNS_X=COLUMNS_X;
        this.COLUMNS_Y=COLUMNS_Y;
        this.COLUMNS_Z=COLUMNS_Z;
        this.Rate=Rate;
        this.id=id;

    }
    public String getCOLUMNS_X() {
        return COLUMNS_X;
    }

    public void setCOLUMNS_X(String COLUMNS_X) {
        this.COLUMNS_X = COLUMNS_X;
    }

    public String getCOLUMNS_Y() {
        return COLUMNS_Y;
    }

    public void setCOLUMNS_Y(String COLUMNS_Y) {
        this.COLUMNS_Y = COLUMNS_Y;
    }

    public String getCOLUMNS_Z() {
        return COLUMNS_Z;
    }

    public void setCOLUMNS_Z(String COLUMNS_Z) {
        this.COLUMNS_Z = COLUMNS_Z;
    }

    public Double getRate() {
        return Rate;
    }

    public void setRate(Double rate) {
        Rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
