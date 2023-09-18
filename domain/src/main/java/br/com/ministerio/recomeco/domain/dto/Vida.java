package br.com.ministerio.recomeco.domain.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Vida implements Serializable {
    private BigInteger id;
}
