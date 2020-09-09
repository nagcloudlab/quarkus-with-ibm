package io.quarkus.workshop.superheroes.fight;

import io.quarkus.workshop.superheroes.fight.client.Hero;
import io.quarkus.workshop.superheroes.fight.client.Villain;

import javax.validation.constraints.NotNull;

public class Fighters {

    @NotNull
    public Hero hero;
    @NotNull
    public Villain villain;

    @Override
    public String toString() {
        return "Fighters{" +
            "hero=" + hero +
            ", villain=" + villain +
            '}';
    }
}
