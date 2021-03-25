package practice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

class kuir {
	public static void main(String[] args)
			throws FileNotFoundException, TransformerException, ParserConfigurationException {
			 makeCollection collection = new makeCollection();
			 makeKeyword keyword = new makeKeyword();
			 
			 collection.makeCollection();
			 keyword.makeKeyword();
	}
}