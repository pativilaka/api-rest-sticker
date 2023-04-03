import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoImdb implements ExtratorConteudo{
    public List<Conteudo> extraiConteudos(String body){

        //Extrai somente os dados que interessam
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.extrair(body);

        List<Conteudo> conteudos = new ArrayList<>();

        //Popula a lista de conteúdos
        for (Map<String, String> atributo : listaDeAtributos) {

            String tituloOriginal = atributo.get("title");
            String urlImagem = atributo.get("image");

            String titulo = tituloOriginal.replace(":", " ");
            //String titulo = tituloTemp.replace(" ", "");

            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
