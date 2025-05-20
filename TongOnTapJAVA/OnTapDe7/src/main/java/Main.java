import dao.AlbumDAO;
import dao.impl.AlbumDAOImpl;
import jakarta.persistence.EntityManager;
import model.Album;
import util.JPAUtil;

public class Main {
    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        AlbumDAO albumDAO = new AlbumDAOImpl(em, Album.class);
        System.out.println(albumDAO.updatePriceOfAlbum("AL1", 1000));

        albumDAO.listAlbumByGenre("Electropop", 2019).forEach(System.out::println);
        albumDAO.getNumberOfAlbumsByGenre().forEach((k, v) -> System.out.println(k + " - " + v));
    }
}
