package com.jianwu.utils;

import com.jianwu.domain.request.PayResult;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * xml转化对象
 *
 * @Author chendong
 * @Create 2018/6/12
 * @Time 19:49
 **/

public class XStreamUtil {

    private static XStream xstream;
     static {
     xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
     }

    // object--->xml
    public static String simpleobject2xml(Object obj) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias(obj.getClass().getSimpleName(), obj.getClass());
        String xml = xStream.toXML(obj);
        return xml;
    }

    // xml--->object
    public static Object simplexml2object(String xml, Object obj) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias(obj.getClass().getSimpleName(), obj.getClass());
        Object reobj = xStream.fromXML(xml);
        return reobj;
    }

    public static Object xml2Object(String inputXml, Class[] types) throws Exception {
     if (null == inputXml || "".equals(inputXml)) {
     return null;
     }
     xstream.processAnnotations(types);
     return xstream.fromXML(inputXml);
 }

    public static void main(String [] arg){
        PayResult persons = new PayResult();
         //实体类转成xml
        String xml = "<PayResult><appid><![CDATA[wxfc11f07da4ef209f]]></appid>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<cash_fee><![CDATA[1]]></cash_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                "<mch_id><![CDATA[1484175972]]></mch_id>\n" +
                "<nonce_str><![CDATA[1528807399352]]></nonce_str>\n" +
                "<openid><![CDATA[o72Xb4or6nFwj2HrthOaHCyrfeUM]]></openid>\n" +
                "<out_trade_no><![CDATA[15288073992709376036945850504203]]></out_trade_no>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<sign><![CDATA[9895507B876DA5809489F510BC5CD072]]></sign>\n" +
                "<time_end><![CDATA[20180612204329]]></time_end>\n" +
                "<total_fee>1</total_fee>\n" +
                "<trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "<transaction_id><![CDATA[4200000136201806128780555670]]></transaction_id>\n" +
                "</PayResult>";
        String s = xml.substring(xml.indexOf("return_code"),xml.lastIndexOf("return_code"));
        String ss = s.substring(s.lastIndexOf("[")+1,s.lastIndexOf("]")-1);
        System.out.println("--->"+ss);
        PayResult person = (PayResult)XStreamUtil.simplexml2object(xml,persons);
        System.out.println("--->"+person.toString());


    }
}