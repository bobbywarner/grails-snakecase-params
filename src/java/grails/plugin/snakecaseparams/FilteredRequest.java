/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
