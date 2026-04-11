import menu.Menu;
import service.AtorService;
import service.DiretorService;
import service.FilmeService;

public class Main {
    public static void main(String[] args) {

        AtorService atorService = new AtorService();
        DiretorService diretorService = new DiretorService();
        FilmeService filmeService = new FilmeService();

        Menu menu = new Menu(atorService, diretorService, filmeService);
        menu.exibirMenu();
    }
}