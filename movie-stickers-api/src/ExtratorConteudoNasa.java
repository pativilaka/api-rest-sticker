import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa implements ExtratorConteudo{
    public List<Conteudo> extraiConteudos(String body){

        //Extrai somente os dados que interessam
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.extrair(body);

        List<Conteudo> conteudos = new ArrayList<>();

        //Popula a lista de conteúdos
        for (Map<String, String> atributo : listaDeAtributos) {

            String titulo = atributo.get("title");
            String urlImagem = atributo.get("url");

            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
