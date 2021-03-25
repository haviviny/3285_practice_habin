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

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class makeKeyword {
	void makeKeyword() throws ParserConfigurationException, FileNotFoundException, TransformerException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document document = docBuilder.newDocument();

		// document 안에 docs 안에 doc

		// docs element
		org.w3c.dom.Element docs = document.createElement("docs");
		document.appendChild((Node) docs);

		// doc element
		org.w3c.dom.Element doc = document.createElement("doc");
		((Node) docs).appendChild((Node) doc);

		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File dir = new File("html");
		File[] fileList = dir.listFiles();

		for (File file : fileList) {
			if (file.isFile()) {
				String fileName = file.getName();
				int id = 0;

				// 속성값 id
				doc.setAttribute("id", Integer.toString(id));

				// title
				org.w3c.dom.Element title = document.createElement("title");
				((Node) title).appendChild(document.createTextNode(fileName.replace(".html", "")));
				((Node) doc).appendChild((Node) title);

				// body
				org.w3c.dom.Element body = document.createElement("body");

				try {
					Scanner scan = new Scanner(file);
					while (scan.hasNextLine()) {
						KeywordExtractor ke = new KeywordExtractor(); 
						KeywordList kl = ke.extractKeyword(scan.nextLine(), true);
						for (int i = 0; i < kl.size(); i++) {
							Keyword kwrd = kl.get(i);
							((Node) body).appendChild(
									document.createTextNode(kwrd.getString() + " : " + kwrd.getCnt() + " # "));
						}
					}
				} catch (FileNotFoundException e) {
					// TODO: handle exception\
				} catch (IOException e) {
					System.out.println(e);
				}
				((Node) doc).appendChild((Node) body);
				id++;
			}
		}
		// 위 까지가 XML 구조 형성

		// XML 파일로 쓰기
		TransformerFactory transformerFactory = TransformerFactory.newInstance();

		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new FileOutputStream(new File("src/index.xml")));

		transformer.transform(source, result);
	}
}