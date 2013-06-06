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
import grails.plugin.snakecaseparams.SnakecaseParamsFilter

class SnakecaseParamsGrailsPlugin {
    def version = "0.1"
    def grailsVersion = "2.0 > *"

    def title = "Snakecase Params Plugin"
    def description = "Converts parameters from snake_case to camelCase."
    def documentation = "http://grails.org/plugin/snakecase-params"
    def license = "APACHE"
    def issueManagement = [ system: "GITHUB", url: "http://github.com/bobbywarner/grails-snakecase-params/issues" ]
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
