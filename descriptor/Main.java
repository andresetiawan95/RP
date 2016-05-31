
import org.basex.core.*;
import org.basex.core.cmd.*;
import org.basex.io.serial.*;
import org.basex.query.*;
import org.basex.query.iter.*;
import org.basex.query.value.*;
import org.basex.query.value.item.*;

import java.io.IOException;


public class Main {

    static Context context = new Context();
    public static void main(String[] args) throws IOException, QueryException, BaseXException{

        //System.out.println("Audio Fundamental :");
        //AudioFundamentalFrequencyType();
        //System.out.println("Audio Harmony :");
        //AudioHarmonicityType();
        //System.out.println("Audio Power type :");
        //AudioPowerType();
        //System.out.println("Audio Spectrum Basis Type:");
        //AudioSpectrumBasisType();
        //System.out.println("Audio Spectrum Projection Type:");
        //AudioSpectrumProjectionType();
        //System.out.println("Audio Spectrum Centroid Type:");
        //AudioSpectrumCentroidType();
        //System.out.println("Audio Spectrum Envelope Type:");
        //AudioSpectrumEnvelopeType();
        //System.out.println("Audio Spectrum Flatness Type:");
        //AudioSpectrumFlatnessType();
        //System.out.println("Audio Spectrum Spread Type:");
        //AudioSpectrumSpreadType();

        //nyoba baru

        System.out.println("Audio Fundamental:");
        String string;
        string = AudioFundamentalFrequencyType().trim();
        String[] hasil = string.split(" ");
        Double[] result = {0.0,0.0,0.0,0.0,0.0};

        result[0] = Double.parseDouble(hasil[0]);
        result[1] = Double.parseDouble(hasil[1]);
        result[2] = Double.parseDouble(hasil[2]);
        result[3] = Double.parseDouble(hasil[3]);
        result[4] = Double.parseDouble(hasil[4]);

        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
        System.out.println(result[3]);
        System.out.println(result[4]);

        System.out.println("Audio Harmony:");
        string = AudioHarmonicityType().trim();
        hasil = string.split(" ");

        result[0] = Double.parseDouble(hasil[0]);
        result[1] = Double.parseDouble(hasil[1]);
        result[2] = Double.parseDouble(hasil[2]);
        result[3] = Double.parseDouble(hasil[3]);
        result[4] = Double.parseDouble(hasil[4]);

        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
        System.out.println(result[3]);
        System.out.println(result[4]);

        System.out.println("Audio Power:");
        string = AudioPowerType().trim();
        hasil = string.split(" ");

        result[0] = Double.parseDouble(hasil[0]);
        result[1] = Double.parseDouble(hasil[1]);
        result[2] = Double.parseDouble(hasil[2]);
        result[3] = Double.parseDouble(hasil[3]);
        result[4] = Double.parseDouble(hasil[4]);

        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
        System.out.println(result[3]);
        System.out.println(result[4]);

        System.out.println("Audio Spectrum Centroid:");
        string = AudioSpectrumCentroidType().trim();
        hasil = string.split(" ");

        result[0] = Double.parseDouble(hasil[0]);
        result[1] = Double.parseDouble(hasil[1]);
        result[2] = Double.parseDouble(hasil[2]);
        result[3] = Double.parseDouble(hasil[3]);
        result[4] = Double.parseDouble(hasil[4]);

        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
        System.out.println(result[3]);
        System.out.println(result[4]);

        System.out.println("Audio Spectrum Spread:");
        string = AudioSpectrumSpreadType().trim();
        hasil = string.split(" ");

        result[0] = Double.parseDouble(hasil[0]);
        result[1] = Double.parseDouble(hasil[1]);
        result[2] = Double.parseDouble(hasil[2]);
        result[3] = Double.parseDouble(hasil[3]);
        result[4] = Double.parseDouble(hasil[4]);

        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
        System.out.println(result[3]);
        System.out.println(result[4]);
    }

    //v
    private static String AudioFundamentalFrequencyType() throws BaseXException{

        String query =
                "declare default element namespace \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace mpeg7 = \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace xsi = \"http://www.w3.org/2001/XMLSchema-instance\";" +
                        "for $x in doc(\"C:/Users/ponighzwa/IdeaProjects/ExtractDescriptor/hasil/Everything_has_changed2.xml\")/Mpeg7/Description/MultimediaContent/Audio return if($x/AudioDescriptor/@xsi:type=\"AudioFundamentalFrequencyType\")then data($x/AudioDescriptor/SeriesOfScalar/Raw) else \"\"";
        String hasil = new XQuery(query).execute(context);
        return hasil;
        //System.out.println(hasil);
    }


    //v
    private static String AudioHarmonicityType() throws BaseXException{

        String query =
                "declare default element namespace \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace mpeg7 = \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace xsi = \"http://www.w3.org/2001/XMLSchema-instance\";" +
                        "for $x in doc(\"C:/Users/ponighzwa/IdeaProjects/ExtractDescriptor/hasil/Everything_has_changed2.xml\")/Mpeg7/Description/MultimediaContent/Audio/AudioDescriptor\n return if($x/@xsi:type=\"AudioHarmonicityType\")then data($x/HarmonicRatio/SeriesOfScalar/Raw) else \"\"";

        String hasil = new XQuery(query).execute(context);
        return hasil;
        //System.out.println(new XQuery(query).execute(context));
    }


    //v
    private  static String AudioPowerType() throws BaseXException{
        String query =
                "declare default element namespace \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace mpeg7 = \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace xsi = \"http://www.w3.org/2001/XMLSchema-instance\";" +
                        "for $x in doc(\"C:/Users/ponighzwa/IdeaProjects/ExtractDescriptor/hasil/Everything_has_changed2.xml\")/Mpeg7/Description/MultimediaContent/Audio/AudioDescriptor\n return if($x/@xsi:type=\"AudioPowerType\")then data($x/SeriesOfScalar/Raw) else \"\"";

        String hasil = new XQuery(query).execute(context);
        return hasil;
        //System.out.println(new XQuery(query).execute(context));
    }

    private static void AudioSpectrumBasisType() throws BaseXException{

        String query =
                "declare default element namespace \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace mpeg7 = \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace xsi = \"http://www.w3.org/2001/XMLSchema-instance\";" +
                        "for $x in doc(\"C:/Users/ponighzwa/IdeaProjects/ExtractDescriptor/hasil/Isyana-1.xml\")/Mpeg7/Description/MultimediaContent/Audio/AudioDescriptor\n return if($x/@xsi:type=\"AudioSpectrumBasisType\")then data($x/SeriesOfVector/Raw) else \"\"";

        System.out.println(new XQuery(query).execute(context));
    }

    private static void AudioSpectrumProjectionType() throws BaseXException{

        String query =
                "declare default element namespace \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace mpeg7 = \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace xsi = \"http://www.w3.org/2001/XMLSchema-instance\";" +
                        "for $x in doc(\"IsyanaCoba.xml\")/Mpeg7/Description/MultimediaContent/Audio/AudioDescriptor\n return if($x/@xsi:type=\"AudioSpectrumProjectionType\")then data($x/SeriesOfVector/Raw) else \"\"";

        System.out.println(new XQuery(query).execute(context));
    }

    //v
    private static String AudioSpectrumCentroidType() throws BaseXException{

        String query =
                "declare default element namespace \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace mpeg7 = \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace xsi = \"http://www.w3.org/2001/XMLSchema-instance\";" +
                        "for $x in doc(\"C:/Users/ponighzwa/IdeaProjects/ExtractDescriptor/hasil/Everything_has_changed2.xml\")/Mpeg7/Description/MultimediaContent/Audio/AudioDescriptor\n return if($x/@xsi:type=\"AudioSpectrumCentroidType\")then data($x/SeriesOfScalar/Raw) else \"\"";

        String hasil = new XQuery(query).execute(context);
        return hasil;
        //System.out.println(new XQuery(query).execute(context));
    }

    private static void AudioSpectrumEnvelopeType() throws BaseXException{

        String query =
                "declare default element namespace \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace mpeg7 = \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace xsi = \"http://www.w3.org/2001/XMLSchema-instance\";" +
                        "for $x in doc(\"IsyanaCoba.xml\")/Mpeg7/Description/MultimediaContent/Audio/AudioDescriptor\n return if($x/@xsi:type=\"AudioSpectrumEnvelopeType\")then data($x/SeriesOfVector/Raw) else \"\"";

        System.out.println(new XQuery(query).execute(context));
    }

    private static void AudioSpectrumFlatnessType() throws BaseXException{

        String query =
                "declare default element namespace \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace mpeg7 = \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace xsi = \"http://www.w3.org/2001/XMLSchema-instance\";" +
                        "for $x in doc(\"IsyanaCoba.xml\")/Mpeg7/Description/MultimediaContent/Audio/AudioDescriptor\n return if($x/@xsi:type=\"AudioSpectrumFlatnessType\")then data($x/SeriesOfVector/Raw) else \"\"";

        System.out.println(new XQuery(query).execute(context));
    }

    //v
    private static String AudioSpectrumSpreadType() throws BaseXException{

        String query =
                "declare default element namespace \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace mpeg7 = \"urn:mpeg:mpeg7:schema:2001\";" +
                        "declare namespace xsi = \"http://www.w3.org/2001/XMLSchema-instance\";" +
                        "for $x in doc(\"C:/Users/ponighzwa/IdeaProjects/ExtractDescriptor/hasil/Everything_has_changed2.xml\")/Mpeg7/Description/MultimediaContent/Audio/AudioDescriptor\n return if($x/@xsi:type=\"AudioSpectrumSpreadType\")then data($x/SeriesOfScalar/Raw) else \"\"";

        String hasil = new XQuery(query).execute(context);
        return hasil;
        //System.out.println(new XQuery(query).execute(context));
    }
}
