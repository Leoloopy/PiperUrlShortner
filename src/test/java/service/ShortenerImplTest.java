package service;

import Model.Links;
import com.piper.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import repositories.LinksRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Main.class)
class ShortenerImplTest {

    @Autowired
    private ShortenerService shortenerService;
    @Autowired
    private LinksRepo linksRepo;
    private String url;

    @BeforeEach
    void setUp() {
        url = "https://www.yahoo.com";
    }

    @AfterEach
    void tearDown() {
        linksRepo.deleteAll();
    }

    @Test
    void testThatLinksCanBeShortened() {
        String hashedLinks = shortenerService.shortenLink(url);
        String savedHashLinks = shortenerService.saveLink(hashedLinks, url);
        assertEquals(savedHashLinks, linksRepo.findLinksByOriginalString(url).getHashedLink());
    }

    @Test
    void testThatLinksShouldReturnAUniqueShortened(){
        String link1 = "https://www.facebook.com";
        String link2 = "https://www.twitter.com";
        String link3 = "https://www.quora.com";

        String hashedLink1 = shortenerService.shortenLink(link1);
        String hashedLink2 = shortenerService.shortenLink(link2);
        String hashedLink3 = shortenerService.shortenLink(link3);

        String saveLink1 = shortenerService.saveLink(hashedLink1, link1);
        String saveLink2 = shortenerService.saveLink(hashedLink2, link2);
        String saveLink3 = shortenerService.saveLink(hashedLink3, link3);

        assertNotEquals(saveLink1, saveLink2);
        assertNotEquals(saveLink1, saveLink3);
        assertNotEquals(saveLink2, saveLink1);
        assertNotEquals(saveLink2, saveLink1);
        assertNotEquals(saveLink3, saveLink1);
        assertNotEquals(saveLink3, saveLink2);
    }

    @Test
    void testThatLinksSavedInDBWillNotBeReHashed(){
        String link1 = "https://www.behance.com";

        String hashedLink1 = shortenerService.shortenLink(link1);
        String savedLink1 = shortenerService.saveLink(hashedLink1, link1);
        assertEquals(hashedLink1, linksRepo.findLinksByOriginalString(link1).getHashedLink());

        String hashedLink2 = shortenerService.shortenLink(link1);
        String savedLinks2 = shortenerService.saveLink(hashedLink2, link1);

        List<String> getLinks = new ArrayList<>();
        getLinks.add(linksRepo.findLinksByOriginalString(link1).getHashedLink());

        assertEquals(1,getLinks.size() );
    }
}