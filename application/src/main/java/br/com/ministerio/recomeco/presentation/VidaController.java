package br.com.ministerio.recomeco.presentation;

import br.com.ministerio.recomeco.service.VidaService;
import br.com.ministerio.recomeco.util.Utils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class VidaController {

    private VidaService service;

    //TODO

    public void Teste () {
        Utils.retornaString();
    }
}
