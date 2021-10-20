package com.jarida.server.jaridaserver.jarida.model;

import java.util.ArrayList;
import java.util.List;

public class JaridaList {
    private List<Jarida> jaridaList;

    public JaridaList(List<Jarida> jarida) {
        this.jaridaList = jarida;
    }

    public List<Jarida> getJaridaList() {
        if(jaridaList == null){
            jaridaList = new ArrayList<>();
        }
        return jaridaList;
    }

    public void setJaridaList(List<Jarida> jaridaList) {
        this.jaridaList = jaridaList;
    }
}
