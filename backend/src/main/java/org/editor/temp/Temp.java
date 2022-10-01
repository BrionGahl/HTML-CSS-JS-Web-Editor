package org.editor.temp;

import lombok.Data;

@Data
public class Temp {
    private String name;
    private int id;

    public Temp(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
