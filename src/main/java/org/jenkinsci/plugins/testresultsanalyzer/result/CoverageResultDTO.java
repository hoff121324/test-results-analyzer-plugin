package org.jenkinsci.plugins.testresultsanalyzer.result;

import net.sf.json.JSONObject;

/**
 * Coverage Result Data Transfer Object for sending as JSON object to client side.
 */
public class CoverageResultDTO {
    private String packages;
    private String files;
    private String classes;
    private String methods;
    private String lines;
    private String conditionals;

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }

    public String getConditionals() {
        return conditionals;
    }

    public void setConditionals(String conditionals) {
        this.conditionals = conditionals;
    }

    /**
     * Get's the JSON representation of the CoverageResult.
     *
     * @return CoverageResult (packages, files, classes, methods, lines and conditions) as a JSON Object.
     */
    public JSONObject getJsonObject() {
        JSONObject json = new JSONObject();
        json.put("packages", packages);
        json.put("files", files);
        json.put("classes", classes);
        json.put("methods", methods);
        json.put("lines", lines);
        json.put("conditionals", conditionals);

        return json;
    }


}
