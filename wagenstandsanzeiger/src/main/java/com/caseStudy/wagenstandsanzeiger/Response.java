package com.caseStudy.wagenstandsanzeiger;

import java.util.List;


/**
 * Class Response provides a structure for the JSON response.
 **/

public class Response {
    private String sections;

    public String getSections() {return sections;}
    public void setSections(String sections) {this.sections = sections;}

    public Response(List<String> result) {
        setSections(result.toString());
    }
}
