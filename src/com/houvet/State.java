package com.houvet;

import java.io.Serializable;

/**
 * Created by antoine on 21/02/2017.
 */
public enum State implements Serializable {
    EN_COURS_DE_PROPOSITION("Proposition"),
    EN_ATTENTE_DE_REPONSES("En attente"),
    DEV("Dev"),
    INTEGRATION("Intégration"),
    PRODUCTION("Production"),
    FERME("Fermé");

    private String name = "";

    State(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return (name);
    }
}
