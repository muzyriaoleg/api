package com.cruidvat.utils;

import java.io.File;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

public class TemplateResolver {

    private TemplateResolver() {
    }

    public static String set(String template, Map<String, String> values) {
        StringSubstitutor stringSubstitutor = new StringSubstitutor(values);
        return stringSubstitutor.replace(template);
    }

    public static String set(File fileTemplate, Map<String, String> values) {
        String template = FileConverter.fileToString(fileTemplate);
        StringSubstitutor stringSubstitutor = new StringSubstitutor(values);
        return stringSubstitutor.replace(template);
    }

}
