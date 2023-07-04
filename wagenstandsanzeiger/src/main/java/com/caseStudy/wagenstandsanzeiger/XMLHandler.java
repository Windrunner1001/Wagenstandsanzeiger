package com.caseStudy.wagenstandsanzeiger;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class XMLHandler deals with all actions related to the XML-File. It therefore imports the XPath-library and DOM functionality.
 **/
public class XMLHandler {

    private final ResourcePatternResolver resourcePatternResolver;
    public XMLHandler(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }

    /**
     * Method getDataFromXML traverses the xml file until the train number and waggon number are found. it then gets the identifiers.
     *
     * @param ril100 holds a string with the ril100 value from the user input
     * @param numberOfTrain holds a string with the train number value from the user input
     * @param numberOfWagon holds a string with the wagon number value from the user input
     * @return identifierValues - a list of strings holding all found identifiers
     **/
    public List<String> getDataFromXML(String ril100, String numberOfTrain, String numberOfWagon) throws IOException {

        InputStream xmlFile = findXMLFile(ril100);

        if (xmlFile != null) {
            try {
                // parse the xml file to  traverse, modify, or extract information using the DOM tree structure
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(xmlFile);

                // Get a new XPath instance (XPath is a query language used to navigate and query XML files)
                XPath xPath = XPathFactory.newInstance().newXPath();

                // Define two XPath expressions for the train node and the waggon node, to get the identifier
                String trainXPathExpression = "//train[trainNumbers/trainNumber = '" + numberOfTrain + "']";
                String waggonXPathExpression = trainXPathExpression + "/waggons/waggon[number = '" + numberOfWagon + "']/sections/identifier";

                // Evaluate the train node XPath expression
                Node trainNode = (Node) xPath.evaluate(trainXPathExpression, document, javax.xml.xpath.XPathConstants.NODE);

                // Check if the train node is found and is an element node (corresponds to an opening or closing tag in the XML)
                if (trainNode instanceof Element) {
                    // Evaluate the waggon node XPath expression within the train node
                    NodeList identifierNodes = (NodeList) xPath.evaluate(waggonXPathExpression, trainNode, javax.xml.xpath.XPathConstants.NODESET);

                    //Check if the waggon node is found
                    if (identifierNodes.getLength() > 0) {
                        // get all identifier and store them into list
                        List<String> identifierValues = new ArrayList<>();
                        for (int i = 0; i < identifierNodes.getLength(); i++) {
                            Node identifierNode = identifierNodes.item(i);
                            if (identifierNode instanceof Element) {
                                String identifierValue = identifierNode.getTextContent();
                                identifierValues.add(identifierValue);
                            }
                        }
                        if (!identifierValues.isEmpty()) {
                            return identifierValues;
                        } else {
                            throw new NoIdentifiers();
                        }
                    } else {
                        throw new NoWaggonMatches();
                    }
                } else {
                    throw new NoTrainMatches();
                }
            } catch (XPathExpressionException | ParserConfigurationException | SAXException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new XMLNotFound();
        }
    }



    /**
     * Method findXMLFile gets the XML-File matching the users ril100 input.
     *
     * @param ril100 holds a string with the ril100 value from the user input
     * @return input stream with the xml file, if found
     **/

    public InputStream findXMLFile(String ril100) {

        try {
            String pattern = "classpath:Wagenreihungsplan_RawData_201712112/" + ril100 + "_*.xml";
            Resource[] resources = resourcePatternResolver.getResources(pattern);

            if (resources.length > 0) {
                Resource resource = resources[0];
                return resource.getInputStream();

            } else {
                // Handle case when no matching XML file is found
                throw new XMLNotFound();
            }
        } catch (IOException e) {
            // Handle exceptions when the XML file cannot be accessed
            throw new PathNotFound();
        }
    }
}





