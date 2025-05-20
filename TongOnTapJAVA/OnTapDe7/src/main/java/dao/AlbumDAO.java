package dao;

import model.Album;

import java.util.List;
import java.util.Map;

public interface AlbumDAO extends GenericDAO<Album, String> {
    public boolean updatePriceOfAlbum(String id, double newPrice);
    public List<Album> listAlbumByGenre(String genreName, int releaseYear);
    public Map<String, Long> getNumberOfAlbumsByGenre();
}
