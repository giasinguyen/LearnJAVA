package service.impl;

import dao.AlbumDAO;
import dao.impl.AlbumDAOImpl;
import model.Album;
import service.AlbumService;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class AlbumServiceImpl extends GenericServiceImpl<Album, String> implements AlbumService {
    private AlbumDAO albumDAO;

    public AlbumServiceImpl(AlbumDAO albumDAO) throws RemoteException {
        super(albumDAO);
        this.albumDAO = albumDAO;

    }

    @Override
    public boolean updatePriceOfAlbum(String id, double newPrice) {
        return albumDAO.updatePriceOfAlbum(id, newPrice);
    }

    @Override
    public List<Album> listAlbumByGenre(String genreName, int releaseYear) {
        return albumDAO.listAlbumByGenre(genreName, releaseYear);
    }

    @Override
    public Map<String, Long> getNumberOfAlbumsByGenre() {
        return albumDAO.getNumberOfAlbumsByGenre();
    }
}
