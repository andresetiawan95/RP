import de.crysandt.audio.mpeg7audio.Config;
import de.crysandt.audio.mpeg7audio.ConfigDefault;
import de.crysandt.audio.mpeg7audio.MP7DocumentBuilder;
import org.w3c.dom.Document;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Created by ponighzwa on 5/10/2016.
 */
public class Descriptor{


    public static void main(String[] args){
        try {
            Config config = new ConfigDefault();
            config.enableAll(true);
            //System.out.println("1");
            //String input = new String("C:/Users/ponighzwa/IdeaProjects/CobaRP/lagu/Isyana.wav");
            String input = new String("C:/Users/ponighzwa/IdeaProjects/ExtractDescriptor/wavecut/Love_Runs_Out3.wav");
            //System.out.println("2");
            File file = new File(input);
            //System.out.println("3");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            //System.out.println("4");
            Document mpeg7 = MP7DocumentBuilder.encode(audioInputStream,config);

            //coba baru
            DOMSource domSource = new DOMSource(mpeg7);
            StringWriter stringWriter = new StringWriter();
            StreamResult result = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(domSource, result);
            String hasil = stringWriter.toString();
            //System.out.println("5");

            //File file1 = new File("C:/Users/ponighzwa/IdeaProjects/CobaRP/descriptor/Isyana-final.xml");
            File file1 = new File("C:/Users/ponighzwa/IdeaProjects/ExtractDescriptor/hasil/Love_Runs_Out3.xml");
            FileWriter fileWriter = new FileWriter(file1,false);
            fileWriter.write(hasil);
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println(e);

        }catch (UnsupportedAudioFileException e){
            System.out.println(e);

        } catch (ParserConfigurationException e){
            System.out.println(e);

        } catch (TransformerException e){
            System.out.println("e");

        }

    }

}
