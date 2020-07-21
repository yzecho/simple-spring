package io.yzecho.ioc.xml;

import io.yzecho.ioc.BeanDefinition;
import io.yzecho.ioc.BeanDefinitionReader;
import io.yzecho.ioc.PropertyValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yzecho
 * @desc
 * @date 13/07/2020 16:26
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String, BeanDefinition> registry;

    public XmlBeanDefinitionReader() {
        registry = new HashMap<>();
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        FileInputStream inputStream = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }

    private void parseBeanDefinitions(Element root) {
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                parseBeanDefinition(ele);
            }
        }
    }

    private void parseBeanDefinition(Element ele) {
        String name = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        processProperty(ele, beanDefinition);
        registry.put(name, beanDefinition);
    }

    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNodes = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNodes.getLength(); i++) {
            Node propertyNode = propertyNodes.item(i);
            if (propertyNode instanceof Element) {
                Element propertyElement = (Element) propertyNode;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyElement.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("ref config error");
                    }

                }
            }
        }
    }
}
