package grails.plugin.snakecaseparams

import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import spock.lang.Specification

import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

/**
 * User: danielwoods
 * Date: 6/6/13
 */
class SnakecaseParamsFilterTestSpec extends Specification {

    def "test filtering"() {
        setup:
        MockHttpServletRequest request = Spy()
        MockHttpServletResponse response = Spy()
        FilterChain chain = Spy()
        SnakecaseParamsFilter filter = new SnakecaseParamsFilter()
        def requestFiltered = false
        chain.doFilter(_,_) >> { ServletRequest req, ServletResponse res ->
            requestFiltered = req instanceof FilteredRequest
        }

        when:
        filter.doFilterInternal(request, response, chain)

        then:
        assert requestFiltered
    }
}
