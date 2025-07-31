package url.encurtador.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import url.encurtador.dto.CreateShortUrl;
import url.encurtador.service.UrlService;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/encurtar")
    public ResponseEntity<String> createShortUrl(@RequestBody CreateShortUrl dto) {
        var url = urlService.createShortUrl(dto.urlOriginal());
        return ResponseEntity.ok("http://"+url.getShortCode());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirecionar(@PathVariable String code) {
        String originalUrl = urlService.getOriginalUrl(code);
        return ResponseEntity.status(HttpStatus.FOUND) 
                .location(URI.create(originalUrl))  
                .build();

        // Ela gera uma resposta HTTP 302, que é um redirecionamento temporário, com um
        // cabeçalho Location apontando para a URL original.
    }

}
