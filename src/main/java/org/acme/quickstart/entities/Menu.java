package org.acme.quickstart.entities;


import lombok.*;
import org.acme.quickstart.interceptor.Portable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Portable
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @NotNull
    String uuid;
    @NotNull
    String ean;
    @NotNull
    String nome;

}
