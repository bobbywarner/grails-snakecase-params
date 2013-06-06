package grails.plugin.snakecaseparams;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import grails.util.GrailsNameUtils;

public class FilteredRequest extends HttpServletRequestWrapper {
    private Map<String, String[]> newParams = null;

    public FilteredRequest(HttpServletRequest request) {
        super(request);
    }

    public Map<String, String[]> convertParams(Map<String, String[]> params) {
        newParams = new HashMap<String, String[]>();
        
        String originalKey = null;
        String newKey = null;

        for (Object key: params.keySet()) {
            originalKey = (String)key;
            System.out.println("Original Key: " + originalKey);
            newKey = GrailsNameUtils.getPropertyNameForLowerCaseHyphenSeparatedName(originalKey.replace("_","-"));
            System.out.println("New Key: " + newKey);
            newParams.put(newKey, params.get(originalKey));
        }
        
        return newParams;
    }
}
