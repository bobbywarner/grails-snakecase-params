package grails.plugin.snakecaseparams

class GrailsNameUtils extends grails.util.GrailsNameUtils {
    static String getPropertyNameForSnakeCaseName(String name) {
        return name?.contains("_") ? GrailsNameUtils.getPropertyNameForLowerCaseHyphenSeparatedName(name?.replace("_","-")) : name
    }
}