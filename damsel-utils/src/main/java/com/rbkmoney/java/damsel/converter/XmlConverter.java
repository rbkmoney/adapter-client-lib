package com.rbkmoney.java.damsel.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class XmlConverter {

    public static <T> T xmlToObject(String content, Class<T> clazz) {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return u.unmarshal(new StreamSource(new StringReader(content)), clazz).getValue();
        } catch (JAXBException e) {
            throw new RuntimeException("Error interpreting XML response", e);
        }
    }

}
