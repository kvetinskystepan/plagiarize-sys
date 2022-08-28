package com.thenarbox.api.colors.patterns;

import com.thenarbox.api.colors.ColorAPI;

import java.awt.*;
import java.util.regex.Matcher;

public class GradientPattern implements Pattern {

    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<GRADIENT:([0-9A-Fa-f]{6})>(.*?)</GRADIENT:([0-9A-Fa-f]{6})>");

    public String process(String string) {
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String start = matcher.group(1);
            String end = matcher.group(3);
            String content = matcher.group(2);
            string = string.replace(matcher.group(), ColorAPI.color(content, new Color(Integer.parseInt(start, 16)), new Color(Integer.parseInt(end, 16))));
        }
        return string;
    }

}
