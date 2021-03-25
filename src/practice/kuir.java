package practice;
import java.io.FileNotFoundException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

class kuir {
	public static void main(String[] args)
			throws FileNotFoundException, TransformerException, ParserConfigurationException {
			 makeCollection collection = new makeCollection();
			 makeKeyword keyword = new makeKeyword();
			 
			 collection.makeCollection();
			 keyword.makeKeyword();
	}
}