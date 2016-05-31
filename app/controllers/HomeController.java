package controllers;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSetFormatter;
import play.*;
import play.mvc.*;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    public Result index() {
        return ok(home.render());
    }
    public Result search(String lagu, String base)
    {
        if (base.equals("lyric")) {
            QueryExecution query = QueryExecutionFactory.sparqlService(
                    "http://localhost:3030/musik/query",
                    "PREFIX onto: <http://www.semanticweb.org/andre/ontologies/2016/4/untitled-ontology-101#>\n"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                            "SELECT DISTINCT ?lagu ?lyric ?artist ?release ?centroid1 ?centroid2 ?centroid3 ?jenisclass \n" +
                            "\t WHERE {?lagu onto:hasLyric ?lyric.\n" +
                            "\tFILTER regex (?lyric, \""+lagu+"\"^^xsd:string, \"i\"). \n" +
                            "\t?artist onto:WriterOf ?lagu.\n" +
                            "\t?lagu onto:ReleaseYear ?release.\n" +
                            "\t?lagu onto:AudioSpectrumCentroid1 ?centroid1.\n" +
                            "\t?lagu onto:AudioSpectrumCentroid2 ?centroid2.\n" +
                            "\t?lagu rdf:type ?jenisclass.\n" +
                            "\t?jenisclass rdfs:subClassOf onto:Song. \n" +
                            "\t?lagu onto:AudioSpectrumCentroid3 ?centroid3 } \n" +
                            "\tLIMIT 25"
            );
            org.apache.jena.query.ResultSet result = query.execSelect();
            //System.out.println(result);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ResultSetFormatter.outputAsJSON(stream, result);
            String output = new String(stream.toByteArray());
            query.close();
            return ok(lagutampil.render(output));
        }
        else if (base.equals("album"))
        {
            QueryExecution queryalbum = QueryExecutionFactory.sparqlService(
                    "http://localhost:3030/musik/query",
                    "PREFIX onto: <http://www.semanticweb.org/andre/ontologies/2016/4/untitled-ontology-101#>\n"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                            "SELECT ?lagu ?album ?namaalbum ?artist ?release \n" +
                            "\t WHERE {?lagu onto:dariAlbum ?album.\n" +
                            "\t ?album onto:hasName ?namaalbum.\n" +
                            "\tFILTER regex (?namaalbum, \""+lagu+"\"^^xsd:string, \"i\"). \n" +
                            "\t?artist onto:hasAlbum ?album.\n" +
                            "\t?album onto:ReleaseYear ?release } \n" +
                            "\tLIMIT 100"
            );
            org.apache.jena.query.ResultSet result = queryalbum.execSelect();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ResultSetFormatter.outputAsJSON(stream, result);
            String output = new String(stream.toByteArray());
            queryalbum.close();
            return ok(album.render(output));
        }
        else {
            QueryExecution queryartist = QueryExecutionFactory.sparqlService(
                    "http://localhost:3030/musik/query",
                    "PREFIX onto: <http://www.semanticweb.org/andre/ontologies/2016/4/untitled-ontology-101#>\n"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                            "SELECT ?artist ?namaartist ?lagu ?releaselagu \n" +
                            "\t WHERE {?artist onto:WriterOf ?lagu.\n" +
                            "\t ?artist onto:hasName ?namaartist.\n" +
                            "\tFILTER regex (?namaartist, \""+lagu+"\"^^xsd:string, \"i\"). \n" +
                            "\t?lagu onto:ReleaseYear ?releaselagu } \n" +
                            "\tLIMIT 100"
            );
            org.apache.jena.query.ResultSet result = queryartist.execSelect();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ResultSetFormatter.outputAsJSON(stream, result);
            String output1 = new String(stream.toByteArray());
            queryartist.close();

            QueryExecution queryduet = QueryExecutionFactory.sparqlService(
                    "http://localhost:3030/musik/query",
                    "PREFIX onto: <http://www.semanticweb.org/andre/ontologies/2016/4/untitled-ontology-101#>\n"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                            "SELECT ?artist ?namaartist ?duet \n" +
                            "\t WHERE {?artist onto:cocokDuetDengan ?duet.\n" +
                            "\t ?artist onto:hasName ?namaartist.\n" +
                            "\tFILTER regex (?namaartist, \""+lagu+"\"^^xsd:string, \"i\") } \n" +
                            "\tLIMIT 100"
            );
            org.apache.jena.query.ResultSet resultduet = queryduet.execSelect();
            ByteArrayOutputStream streamduet = new ByteArrayOutputStream();
            ResultSetFormatter.outputAsJSON(streamduet, resultduet);
            String outputduet = new String(streamduet.toByteArray());
            queryduet.close();

            QueryExecution querytimbre = QueryExecutionFactory.sparqlService(
                    "http://localhost:3030/musik/query",
                    "PREFIX onto: <http://www.semanticweb.org/andre/ontologies/2016/4/untitled-ontology-101#>\n"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                            "SELECT ?artist ?namaartist ?timbre1 ?timbre2 ?timbre3 \n" +
                            "\t WHERE {?artist onto:hasName ?namaartist.\n" +
                            "\tFILTER regex (?namaartist, \""+lagu+"\"^^xsd:string, \"i\"). \n" +
                            "\t?artist onto:AudioTimbre3 ?timbre3.\n" +
                            "\t?artist onto:AudioTimbre2 ?timbre2.\n" +
                            "\t?artist onto:AudioTimbre1 ?timbre1 } \n" +
                            "\tLIMIT 100"
            );
            org.apache.jena.query.ResultSet result2 = querytimbre.execSelect();
            ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
            ResultSetFormatter.outputAsJSON(stream2, result2);
            String output2 = new String(stream2.toByteArray());
            querytimbre.close();

            QueryExecution queryalbum = QueryExecutionFactory.sparqlService(
                    "http://localhost:3030/musik/query",
                    "PREFIX onto: <http://www.semanticweb.org/andre/ontologies/2016/4/untitled-ontology-101#>\n"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                            "SELECT ?artist ?namaartist ?album ?releasealbum \n" +
                            "\t WHERE {?artist onto:hasName ?namaartist.\n" +
                            "\tFILTER regex (?namaartist, \""+lagu+"\"^^xsd:string, \"i\"). \n" +
                            "\t?artist onto:hasAlbum ?album.\n" +
                            "\t?album onto:ReleaseYear ?releasealbum } \n" +
                            "\tLIMIT 100"
            );
            org.apache.jena.query.ResultSet result3 = queryalbum.execSelect();
            ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
            ResultSetFormatter.outputAsJSON(stream3, result3);
            String output3 = new String(stream3.toByteArray());
            queryalbum.close();

            return ok(artist.render(output1, output2, output3, outputduet));

        }
    }
}
