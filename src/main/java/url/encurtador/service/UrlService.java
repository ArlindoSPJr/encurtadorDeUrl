package url.encurtador.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import url.encurtador.model.Url;
import url.encurtador.repositories.UrlRespository;

@Service
public class UrlService {

    @Autowired
    private UrlRespository urlRespository;

    public Url createShortUrl(String urlOriginal) {
        String shortUrl = generateCode();
        Url novaUrl = new Url();
        novaUrl.setOriginalUrl(urlOriginal);
        novaUrl.setShortCode(shortUrl);
        return urlRespository.save(novaUrl);
    }

    public String getOriginalUrl(String shortUrl) {
        return urlRespository.findByShortCode(shortUrl)
                .orElseThrow(() -> new RuntimeException("Código não encontrado"))
                .getOriginalUrl();
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 6);
    }

}
