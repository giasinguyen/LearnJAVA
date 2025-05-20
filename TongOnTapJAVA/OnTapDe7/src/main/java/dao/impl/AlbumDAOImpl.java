package dao.impl;

import dao.AlbumDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.Album;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AlbumDAOImpl extends GenericDAOImpl<Album, String> implements AlbumDAO {
    public AlbumDAOImpl(Class<Album> clazz) {
        super(clazz);
    }

    public AlbumDAOImpl(EntityManager em, Class<Album> clazz) {
        super(em, clazz);
    }


    //    Cập nhật đơn giá cho một album nào đó khi biết mã số (không cho phép cập
//    nhật các thuộc tính khác của album).
    @Override
    public boolean updatePriceOfAlbum(String id, double newPrice) {
        String sql = "UPDATE albums al\n" +
                "SET al.price = :newPrice\n" +
                "WHERE al.album_id = :id";
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            var result = em.createNativeQuery(sql, Album.class)
                    .setParameter("id", id)
                    .setParameter("newPrice", newPrice)
                    .executeUpdate() == 1;
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Album> listAlbumByGenre(String genreName, int releaseYear) {
        String jpql = "SELECT al FROM Album al " +
                "WHERE al.genre.name LIKE CONCAT('%', :genreName, '%') AND al.yearOfRelease = :releaseYear\n";
        return em.createQuery(jpql, Album.class)
                .setParameter("genreName", genreName)
                .setParameter("releaseYear", releaseYear)
                .getResultList();
    }

//    @Override
//    public Map<String, Long> getNumberOfAlbumsByGenre() {
//        String sql = """
//                SELECT gr.name, COUNT(*) FROM albums al JOIN genres gr ON al.genre_id = gr.genre_id
//                GROUP BY gr.name
//                ORDER BY gr.name ASC
//                """;
//        return em.createNativeQuery(sql, Object[].class)
//                .getResultList()
//                .stream()
//                .collect(
//                        Collectors.toMap(
//                                result -> (String) result[0],
//                                result -> (Long) result[1],
//                                (a, b) -> a, LinkedHashMap::new
//                        )
//                );
//    }

    @Override
    public Map<String, Long> getNumberOfAlbumsByGenre() {
        String jpql = """
                SELECT al.genre.name, Count(al) 
                FROM Album al
                GROUP BY al.genre.name 
                ORDER BY al.genre.name  ASC
                """;
        return em.createQuery(jpql, Object[].class)
                .getResultList()
                .stream()
                .collect(
                        Collectors.toMap(
                                result -> (String) result[0],
                                result -> (Long) result[1],
                                (a, b) -> a, LinkedHashMap::new
                        )
                );
    }

}
