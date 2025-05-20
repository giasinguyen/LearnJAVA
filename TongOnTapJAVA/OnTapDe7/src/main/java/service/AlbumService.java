package service;

import model.Album;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface AlbumService extends GenericService<Album, String>{
    public boolean updatePriceOfAlbum(String id, double newPrice) throws RemoteException;
    public List<Album> listAlbumByGenre(String genreName, int releaseYear) throws RemoteException;
    public Map<String, Long> getNumberOfAlbumsByGenre() throws RemoteException;
}
