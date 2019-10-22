package guorui.xuyu.shengborundengbao.util;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class xmlUtil {

    /**
     * 得到路径的Document
     *
     * @param path
     * @return
     */
    public static Document getDom(String path) {
        try {

            // 获得DocumentBuilderFactory工场对象
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // 通过工场创建解析器
            DocumentBuilder db = factory.newDocumentBuilder();
            //返回Document
            return db.parse(path);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //保存文件方法,一般通过TransformerFactory转换工场进行转换为所需要的格式
    //设置保存格式,保存为我们需要的文件格式    
    public static StreamResult saveDOM(Document document) throws TransformerConfigurationException, IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // 获得TransformerFactory转换工场
            TransformerFactory tff = TransformerFactory.newInstance();
            // 通过工场创建转换器
            Transformer tf = tff.newTransformer();
            // 设定转换格式
            tf.setParameter(OutputKeys.ENCODING, "UTF-8");
            // 设置换行
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            // 拿到doc文件
            DOMSource source = new DOMSource(document);
            // 执行保存路径,如果保存路径和原来路径一样,覆盖原来文件
            StreamResult srl = new StreamResult(byteArrayOutputStream);
            // 保存
            tf.transform(source, srl);
            // 返回
            return srl;
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

//    //向xml文件中添加元素
//    public void addXML() throws TransformerConfigurationException {
//        // 使用DOM添加元素的方法,添加文件的五个元素
//        Element book = doc.createElement("book");
//        Element name = doc.createElement("name");
//        Element zuozhe = doc.createElement("zuozhe");
//        Element zyrw = doc.createElement("zyrw");
//        Element guojia = doc.createElement("guojia");
//        // 给name/zuozhe/zyrw/guojia元素赋值
//        name.setTextContent("三国演义");
//        zuozhe.setTextContent("罗贯中");
//        zyrw.setTextContent("赵子龙");
//        guojia.setTextContent("China");
//        // 想节点中插入元素
//        book.appendChild(name);
//        book.appendChild(zuozhe);
//        book.appendChild(zyrw);
//        book.appendChild(guojia);
//        // 将节点插入
//        doc.getElementsByTagName("books").item(0).appendChild(book);
//        // 保存文件
//        saveDOM();
//        System.out.println("保存成功!");
//
//    }
}

