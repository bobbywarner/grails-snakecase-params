package grails.plugin.snakecaseparams

import org.springframework.mock.web.MockHttpServletRequest
import spock.lang.Specification

import javax.servlet.http.HttpServletRequest

/**
 * User: danielwoods
 * Date: 6/6/13
 */
class FilteredRequestTestSpec extends Specification {

    def "test filtered request parameter"() {
        setup:
        HttpServletRequest request = new MockHttpServletRequest()
        request.setParameter("snake_case_param", ["value"] as String[])
        FilteredRequest filteredRequestInstance = new FilteredRequest(request)

        expect:
        assert filteredRequestInstance.getParameter("snakeCaseParam")
    }

    def "test parameter map"() {
        setup:
        HttpServletRequest request = new MockHttpServletRequest()
        request.setParameter("snake_case_param", ["value"] as String[])
        FilteredRequest filteredRequestInstance = new FilteredRequest(request)

        expect:
        assert filteredRequestInstance.parameterMap == Collections.unmodifiableMap(["snakeCaseParam": ["value"] as String[]])
    }

    def "test enumerable parameter keys"() {
        setup:
        HttpServletRequest request = new MockHttpServletRequest()
        request.setParameter("snake_case_param", ["value"] as String[])
        FilteredRequest filteredRequestInstance = new FilteredRequest(request)

        expect:
        assert filteredRequestInstance.parameterNames.collect{it} == Collections.enumeration(["snakeCaseParam"]).collect{it}
    }

    def "test parameter values"() {
        setup:
        HttpServletRequest request = new MockHttpServletRequest()
        request.setParameter("snake_case_param", ["value"] as String[])
        FilteredRequest filteredRequestInstance = new FilteredRequest(request)

        expect:
        assert filteredRequestInstance
                .parameterNames
                .collect{filteredRequestInstance.getParameterValues(it)}?.flatten() == (["value"] as String[])
    }
}
