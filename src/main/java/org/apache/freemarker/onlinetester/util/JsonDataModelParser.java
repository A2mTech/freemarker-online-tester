package org.apache.freemarker.onlinetester.util;


import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.type.TypeReference;

import freemarker.ext.dom.NodeModel;
import freemarker.template.utility.DateUtil;
import freemarker.template.utility.DateUtil.CalendarFieldsToDateConverter;
import freemarker.template.utility.DateUtil.DateParseException;
import freemarker.template.utility.DateUtil.TrivialCalendarFieldsToDateConverter;

public class JsonDataModelParser {


    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();


    public static Map<String, Object> parseValue(String value, TimeZone timeZone) throws DataModelParsingException {
        try {
            JsonNode jsonNode = JSON_MAPPER.readTree(value);
    
            if (jsonNode.isObject()) {
                return JSON_MAPPER.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {});
            }
        } catch (IOException e) {
            throw new DataModelParsingException("Failed to parse JSON value: " + e.getMessage(), e.getCause());
        }
    
        throw new DataModelParsingException("Invalid JSON data format: " + value);
    }
    
}
