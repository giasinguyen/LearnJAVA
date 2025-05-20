import dao.AlbumDAO;
import dao.impl.AlbumDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Inheritance;
import model.Album;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import util.JPAUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test {
    private AlbumDAO albumDAO;
    EntityManager em = JPAUtil.getEntityManager();
    @BeforeAll
    void setUp() {
        albumDAO = new AlbumDAOImpl(em, Album.class);
    }

    @org.junit.jupiter.api.Test
    void testGetNumberOfAlbumsByGenre(){
        var result = albumDAO.getNumberOfAlbumsByGenre();
        assertTrue(result.size() > 0);
    }

    @org.junit.jupiter.api.Test
    void testListAlbumByGenre(){
        var result = albumDAO.listAlbumByGenre("Electropop", 2019);
        assertTrue(result.size() > 0);
    }

    @org.junit.jupiter.api.Test
    void testUpdatePriceOfAlbum(){
        boolean result = albumDAO.updatePriceOfAlbum("AL1", 10000);
        Album album = em.find(Album.class, "AL1");
        assertEquals(10000, album.getPrice());
    }
}
