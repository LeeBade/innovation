package com.titian.innovation.file.service;

import com.titian.innovation.file.domain.FilePathTag;
import com.titian.innovation.file.domain.LegalSuffix;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FileUtils {
    //本工具类是与kk集成的一部分，受限于kk接口而设计

    /**获取合法的文件名和后缀*/
    static String getFileName(String str){
        str=getLastComponentOfURL (str);
        String[] s=splitNameAndSuffix (str);
        return s!=null?s[0]+"."+s[1]:null;
    }

    /**传入httpRoot+filePath(demo/filename)组成的字符串转换为preview编码的字符串*/
    static String getBase64(String str){
        byte[] base64Bytes = Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8));
        return new String(base64Bytes, StandardCharsets.UTF_8);
    }
    /**返回合法的文件名和后缀，不合法返回null
     * 1.满足X.X
     * 2.后缀是合法的*/
    static String[] splitNameAndSuffix(String filename){
        if(filename==null)
            return null;
        String[] filenames = filename.split("\\.");
        if(filenames.length!=2||filenames[0].equals ("")||getTagBySuffix (filenames[1])==null){
            return null;
        }
        return filenames;
    }
    /**获取文件名*/
    static String getLastComponentOfURL(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        String separator = File.separator;
        int lastSeparatorIndex = input.lastIndexOf(separator);
        if (lastSeparatorIndex == -1) {
            return input;
        }
        return input.substring(lastSeparatorIndex + 1);
    }

    /**
     * 非法名字或路径返回null
     */
    static Integer getItemIdByNameOrPath (String filePath) {
        String regex = "(\\d+)&";
        Pattern pattern = Pattern.compile (regex);
        Matcher matcher = pattern.matcher (filePath);
        if (matcher.find ()) {
            String numberStr = matcher.group (1);
            return Integer.parseInt (numberStr);
        } else {
            return null;
        }
    }

    /**
     * 非法后缀或空文件名得到null
     */
    static FilePathTag getTagByNameOrPath (String filePath) {
        return getTagBySuffix (getSuffix (filePath));
    }

    /**
     * 非法后缀、null->null
     */
    static FilePathTag getTagBySuffix (String suffix) {
        if (suffix == null || suffix.isEmpty ())
            return null;
        if (LegalSuffix.File.getSuffixes ().contains (suffix))
            return LegalSuffix.File.getFilePathTag ();
        if (LegalSuffix.IMAGE.getSuffixes ().contains (suffix))
            return LegalSuffix.IMAGE.getFilePathTag ();
        if (LegalSuffix.Video.getSuffixes ().contains (suffix))
            return LegalSuffix.Video.getFilePathTag ();
        return null;
    }

    /**
     * 非法后缀或空字符串->null
     */
    static  String getSuffix (String filePath) {
        if (filePath == null || filePath.isEmpty ())
            return null;
        int lastDotIndex = filePath.lastIndexOf ('.');
        if (lastDotIndex != -1 && lastDotIndex < filePath.length () - 1) {
            return filePath.substring (lastDotIndex + 1);
        } else {
            return null;
        }
    }

}
