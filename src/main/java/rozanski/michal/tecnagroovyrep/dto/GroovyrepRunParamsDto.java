package rozanski.michal.tecnagroovyrep.dto;

import rozanski.michal.tecnagroovyrep.aux.Param;

public class GroovyrepRunParamsDto {
    private Param[] params;

    public GroovyrepRunParamsDto() {
    }

    public GroovyrepRunParamsDto(Param[] params) {
        this.params = params;
    }

    public Param[] getParams() {
        return params;
    }

    public void setParams(Param[] params) {
        this.params = params;
    }



}
