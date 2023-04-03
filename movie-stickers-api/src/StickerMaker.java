import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class StickerMaker {

    /*
    * Etapas:
    * - Ler imagem;
    * - Criar nova imagem com transparência e com novo tamanho;
    * - Copiar a imagem original para a nova imagem (em memória);
    * - Configurar a fonte do texto que será desenhado na nova imagem;
    * - Escrever uma frase na nova imagem;
    * - Escrever a nova imagem em um arquivo.
    * */

    public void criaSticker(InputStream inputStream, String nomeArquivo, String textoDaImagem) throws Exception{

        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        //BufferedImage imagemOriginal = ImageIO.read(new File("assets/filme-maior.jpg")); lê somente arquivo

        //Cria nova imagem
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //Copia a imagem original para a nova imagem
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0,null);

        //Configura a fonte do texto
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont( fonte );

        //Escreve uma frase na nova imagem
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D textWidth = fontMetrics.getStringBounds(textoDaImagem, graphics);
        int widthText = (int) textWidth.getWidth();
        int positionTextX = (largura - widthText) / 2;
        int positionTextY = novaAltura - 80;
        graphics.drawString( textoDaImagem, positionTextX, positionTextY );

        //Escreve a nova imagem em um arquivo
       File theDir = new File("saida/");
       if (!theDir.exists()){
           theDir.mkdir();
       }
        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));
    }




}
