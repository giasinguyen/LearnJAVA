import model.Album;
import service.AlbumService;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.List;

public class RMIClient {
    public static void main(String[] args) throws Exception {
        Context context = new InitialContext();

        AlbumService albumService = (AlbumService) context.lookup("rmi://GiaSi:715/albumService");
        System.out.println(albumService.listAlbumByGenre("Electropop", 2019));
        albumService.getNumberOfAlbumsByGenre().forEach((k, v) -> System.out.println(k + " - " + v));
//
//        System.out.println(albumService.updatePriceOfAlbum("AL1", 11111));
//
//        System.out.println(albumService.findById("AL1"));

    }
}
