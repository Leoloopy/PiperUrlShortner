package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ShortenerService;

@Controller
@RequestMapping(path = "/piper")
public class LinkController {
    @Autowired
    private ShortenerService shortenerService;

    @PostMapping("/shorten-my-link")
    public ResponseEntity<String> shortenLink(String myLink){
        String links = shortenerService.shortenLink(myLink);
        String savedLink = shortenerService.saveLink(links, myLink);
        return ResponseEntity.ok(savedLink);
    }
}
