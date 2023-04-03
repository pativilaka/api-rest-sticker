import java.util.List;
import java.util.Map;
import java.net.URL;
import java.io.InputStream;


public class Main {
    public static void main(String[] args) throws Exception {

        /*
        * Etapas:
        * - Estabelecer conexão HTTP e buscar os top 250 filmes | utilizando biblioteca nativa Java;
        * - Extrair somente os dados que interessam: título / imagem / classificação;
        * - Exibir e manipular os dados.
        * */

        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //ExtratorConteudo extrator = new ExtratorConteudoImdb();

        //String url = "https://api.nasa.gov/planetary/apod?api_key=iEQpSbP52g4bm3SoNRtfgUHtrXoC0zINOUhFuGZz&start_date=2023-03-24&end_date=2023-03-25";
        //ExtratorConteudo extrator = new ExtratorConteudoNasa();

        String url = "http://localhost:8080/linguagens";
        ExtratorConteudo extrator = new ExtratorConteudoImdb();

        //Estabelece conexão HTTP e buscar os top filmes
        ClienteHttp http = new ClienteHttp();
        String body = http.buscaDados(url);

        //Exibe e manipula os dados

        List<Conteudo> conteudos = extrator.extraiConteudos(body);

        var newSticker = new StickerMaker();

        int count = 1;
        for (Conteudo conteudo : conteudos) {

            InputStream inputStream =
                    new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            newSticker.criaSticker(inputStream, nomeArquivo, "Gosto!");

            System.out.printf("\u001b[1m\u001b[45m%d", count);
            System.out.println("º conteúdo \u001b[m\uD83C\uDFC6");
            System.out.println("Nome: " + conteudo.getTitulo());
            System.out.println();
            count++;
        }

    }
}