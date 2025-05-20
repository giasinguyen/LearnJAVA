package rmi;


import dao.AlbumDAO;
import dao.impl.AlbumDAOImpl;
import model.Album;
import service.AlbumService;
import service.impl.AlbumServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class RMIServer {

    public static void main(String[] args) throws Exception {

        Context context = new InitialContext();

        LocateRegistry.createRegistry(7151);

        AlbumDAO albumDAO = new AlbumDAOImpl(Album.class);

        AlbumService albumService = new AlbumServiceImpl(albumDAO);


        context.bind("rmi://GiaSi:7151/albumService", albumService);

        System.out.println("RMI server is running...");

    }
}