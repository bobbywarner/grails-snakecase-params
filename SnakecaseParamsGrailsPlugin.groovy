import grails.plugin.snakecaseparams.SnakecaseParamsFilter

class SnakecaseParamsGrailsPlugin {
    def version = "0.1"
    def grailsVersion = "2.0 > *"

    def title = "Snakecase Params Plugin"
    def description = "Converts parameters from snake_case to camelCase."
    def documentation = "http://grails.org/plugin/snakecase-params"
    def license = "APACHE"
    def issueManagement = [ system: "JIRA", url: "http://github.com/bobbywarner/grails-snakecase-params/issues" ]
    def scm = [ url: "http://github.com/bobbywarner/grails-snakecase-params" ]
    def developers = [[ name: "Bobby Warner", email: "bobbywarner@gmail.com" ]]

    def doWithWebDescriptor = { webXml ->
        def contextParam = webXml.'context-param'

        contextParam[contextParam.size() - 1] + {
            'filter' {
                'filter-name'('snakecaseParamsFilter')
                'filter-class'(SnakecaseParamsFilter.name)
            }
        }

        // Insert the filter after the Spring character encoding filter
        def filter = webXml.'filter-mapping'.find {
            it.'filter-name'.text() == "charEncodingFilter"
        }

        filter + {
            'filter-mapping'{
                'filter-name'('snakecaseParamsFilter')
                'url-pattern'('/*')
            }    
        }
    }
}
