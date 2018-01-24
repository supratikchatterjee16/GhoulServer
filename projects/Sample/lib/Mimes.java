package projects.Sample.lib;

import java.util.Hashtable;

public class Mimes{
    private static Hashtable<String,String> mimes=new Hashtable<String,String>();
    public static String get(String x){
        mimes.put("html","text/html");
        mimes.put("css","text/css");
        mimes.put("js","application/javascipt");
        mimes.put("jpg","image/jpeg");
        mimes.put("jpeg","image/jpeg");
        mimes.put("gif","image/gif");
        mimes.put("bin","application/octet-stream");
        mimes.put("bz","application/x-bzip");
        mimes.put("bz2","application/x-bzip2");
        mimes.put("doc","application/msword");
        mimes.put("ico","image/x-icon");
        mimes.put("ics","text/calendar");
        mimes.put("mpeg","video/mpeg");
        mimes.put("oga","audio/ogg");
        mimes.put("ogv","video/ogg");
        mimes.put("otf","font/otf");
        mimes.put("png","image/png");
        mimes.put("pdf","application/pdf");
        mimes.put("rar","x-rar-compressed");
        mimes.put("svg","image/svg+xml");
        mimes.put("sqf","application/x-shockwave-flash");
        mimes.put("tar","x-tar");
        mimes.put("ttf","font/ttf");
        mimes.put("wav","audio/x-wav");
        mimes.put("weba","audio/webm");
        mimes.put("webm","video/webm");
        mimes.put("webp","image/webp");
        mimes.put("woff","font/woff");
        mimes.put("woff2","font/woff2");
        mimes.put("xhtml","application/xhtml+xml");
        mimes.put("xml","application/xml");
        mimes.put("zip","application/zip");
        mimes.put("7z","application/x-7z-compressed");
        return mimes.get(x);
    }
}
