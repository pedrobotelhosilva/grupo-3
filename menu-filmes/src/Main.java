public class Main {
    public static void main(String[] args) {

        AtorService atorService = new AtorService();
        DiretorService diretorService = new DiretorService();
        FilmeService filmeService = new FilmeService();

        Menu menu = new Menu(atorService, diretorService, filmeService);
        menu.exibirMenu();
    }
}