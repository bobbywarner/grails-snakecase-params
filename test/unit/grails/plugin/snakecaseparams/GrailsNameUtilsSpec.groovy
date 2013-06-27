package grails.plugin.snakecaseparams

import spock.lang.Specification

import grails.plugin.snakecaseparams.GrailsNameUtils

import javax.servlet.http.HttpServletRequest

class GrailsNameUtilsSpec extends Specification {

    def "test happy path"() {
        setup:
        def snake = "snake_case_param"
        def propertyName = GrailsNameUtils.getPropertyNameForSnakeCaseName(snake)

        expect:
        assert "snakeCaseParam" == propertyName
    }

    def "test beginning underscore"() {
        setup:
        def snake = "_snake_case_param"
        def propertyName = GrailsNameUtils.getPropertyNameForSnakeCaseName(snake)

        expect:
        assert "snakeCaseParam" == propertyName
    }

    def "test ending underscore"() {
        setup:
        def snake = "snake_case_param_"
        def propertyName = GrailsNameUtils.getPropertyNameForSnakeCaseName(snake)

        expect:
        assert "snakeCaseParam" == propertyName
    }

    def "test beginning and ending underscore"() {
        setup:
        def snake = "_snake_case_param_"
        def propertyName = GrailsNameUtils.getPropertyNameForSnakeCaseName(snake)

        expect:
        assert "snakeCaseParam" == propertyName
    }

    def "test no underscore"() {
        setup:
        def snake = "none"
        def propertyName = GrailsNameUtils.getPropertyNameForSnakeCaseName(snake)

        expect:
        assert "none" == propertyName
    }

    def "test camel case"() {
        setup:
        def snake = "snakeCaseParam"
        def propertyName = GrailsNameUtils.getPropertyNameForSnakeCaseName(snake)

        expect:
        assert "snakeCaseParam" == propertyName
    }

}
