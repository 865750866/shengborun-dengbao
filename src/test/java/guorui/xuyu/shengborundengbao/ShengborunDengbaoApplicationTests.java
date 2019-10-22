package guorui.xuyu.shengborundengbao;

import guorui.xuyu.shengborundengbao.util.xmlUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

@SpringBootTest
class ShengborunDengbaoApplicationTests {

    @Test
    void contextLoads() throws FileNotFoundException, ParserConfigurationException, TransformerException {
        Document d= xmlUtil.getDom("src/main/resources/xml/test.xml");

        // 获得DocumentBuilderFactory工场对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 通过工场创建解析器
        DocumentBuilder db = factory.newDocumentBuilder();

        Document do1=db.newDocument();

        Element e1=do1.createElement("root");
        e1.setAttribute("name","0001");
        e1.setAttribute("val","111");
        Element e2=do1.createElement("root");
        e2.setAttribute("name","0001");
        e2.setAttribute("val","111");
        e1.appendChild(e2);
        do1.appendChild(e1);

        //创建转换工厂，然后将创建的document转换输出到文件中或控制台
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.transform(new DOMSource(do1), new StreamResult(new File("newXml.xml")));

        //将document中的信息转换为字符串输出到控制台中
        StringWriter stringWriter = new StringWriter();
        transformer.transform(new DOMSource(do1), new StreamResult(stringWriter));
        System.out.println(stringWriter.toString());

        try {
            StreamResult r=xmlUtil.saveDOM(do1);

            System.out.println("aa");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedOutputStream b=new BufferedOutputStream();
        StringReader reader;


    }

}
