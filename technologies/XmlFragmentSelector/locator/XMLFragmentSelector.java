/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package locator;


import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Martin
 */
public class XMLFragmentSelector {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws  SAXException, ParserConfigurationException, IOException, XPathExpressionException {      
       if (args.length != 3) {
           System.out.println("Usage: inputFile fragmentFile outputFile");
           System.exit(1);
       }
        
       String inputFile = args[0];
       String fragmentFile = args[1];
       String outputFile = args[2];
       
       SelectorXMLParser xmlParser = new SelectorXMLParser(new File(inputFile));
       Document doc = xmlParser.getDocument();
       //Document doc = domParse(new File(inputFile));
       
       String xPathQuery = readFragment(new File(fragmentFile));
       

       XPath xPath = XPathFactory.newInstance().newXPath();
       xPath.setNamespaceContext(new ResolverContext(doc.getDocumentElement()));
       
       XPathExpression expression = xPath.compile(xPathQuery);
       Element e = (Element)expression.evaluate(doc, XPathConstants.NODE);
       
       if (e != null) {
        System.out.println(xmlParser.getLineRange(e).toJSON());
        writeOutput(new File(outputFile), xmlParser.getLineRange(e).toJSON());
       } else {
           System.out.println("Query didn't return an element");
           System.exit(1);
       }
    }
    
    private static String readFragment(File fragment) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fragment));
        StringBuffer fragmentCode = new StringBuffer();
        String tmp = null;
        while ((tmp = reader.readLine()) != null) {
            fragmentCode.append(tmp);
        }
        return fragmentCode.toString();
    }
    
    private static void writeOutput(File outputFile, String output) throws IOException {
        FileWriter writer = new FileWriter(outputFile);
        writer.write(output);
        writer.close();
    }
    
    
    
    //Just for debugging purposes
    private static Document domParse(File f) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory xmlFact = DocumentBuilderFactory.newInstance();
        xmlFact.setNamespaceAware(true);
        DocumentBuilder builder = xmlFact.newDocumentBuilder();
        return builder.parse(f);
    }

}
