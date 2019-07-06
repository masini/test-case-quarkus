package org.acme.quickstart.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum FlussoServizio {
    /**
     * Flusso previsto nei bar in cui non è presente il chiosco né il monitor numeri.
     */
    FRONT,BACK;

    public static List<FlussoServizio> getFlussi(Collection<String> strings) {
        List<FlussoServizio> stati = new ArrayList<>();
        for (String s : strings) {
            if(s!=null && !s.isEmpty()) {
                stati.add(FlussoServizio.valueOf(s.toUpperCase()));
            }
        }
        return stati;
    }
}
