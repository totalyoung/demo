package ja;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangst on 2018/8/1.
 */

public class XmlUtil {

    public static void readXml(){
        try(InputStream in = new FileInputStream("E:\\svn\\doc\\export\\protocol\\xml\\race.xml")){
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(in);
            while(reader.hasNext()){
                int event = reader.next();
                if(event == XMLStreamConstants.START_ELEMENT){
                    String explain = reader.getAttributeValue(null, "explain");
                    StringBuffer sb = new StringBuffer();
                    sb.append(reader.getLocalName());
                    for(int i = 0; i<reader.getAttributeCount();i++){
                        sb.append(" ").append(reader.getAttributeName(i)).append("=").append(reader.getAttributeValue(i));
                    }
                    System.out.println(sb);
                }
                if(event ==XMLStreamConstants.END_ELEMENT){
                    System.out.println(reader.getLocalName());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String str = "tip(String.format(\"重置比赛需要 %d 荣誉点\", resetNeedHonor))";
//        String pattern = "tip\\(.*\\);";
        String[] split = str.split("\"");
        String pattern ="\"((?!\").)+\"";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }
}
