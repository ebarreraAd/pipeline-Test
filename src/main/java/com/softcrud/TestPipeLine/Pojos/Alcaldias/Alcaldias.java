package com.softcrud.TestPipeLine.Pojos.Alcaldias;

import java.util.List;

public class Alcaldias {
    public String nhits;
    public ParametersAlcaldias parameters;
    public List<RecordsAlcaldias> records;

    public Alcaldias(String nhits, ParametersAlcaldias parameters, List<RecordsAlcaldias> records) {
        this.nhits = nhits;
        this.parameters = parameters;
        this.records = records;
    }
}
