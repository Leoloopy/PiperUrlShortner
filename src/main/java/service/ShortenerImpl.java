package service;

import Model.Links;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.LinksRepo;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class ShortenerImpl implements ShortenerService {

    @Autowired
    private LinksRepo linksRepo;


    @Override
    public String shortenLink(String link) {
        String template = "piper/";
        String sha256hex = Hashing.murmur3_32()
                .hashString(link, StandardCharsets.UTF_8)
                .toString();
        return template+=sha256hex;
    }

    private boolean checkForLinkInDB(String url){
        Links getLink = linksRepo.findLinksByOriginalString(url);
        return getLink != null;
    }

    @Override
    public String saveLink(String hashString,String realString) {
        if (checkForLinkInDB(realString))
            return linksRepo.findLinksByOriginalString(realString).getHashedLink();
        Links links  = Links.builder()
                .creationDate(LocalDateTime.now())
                .originalString(realString)
                .hashedLink(hashString)
                .build();
        Links savedLinks = linksRepo.save(links);
        return savedLinks.getHashedLink();
    }

}
