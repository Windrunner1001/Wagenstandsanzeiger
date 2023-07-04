package com.caseStudy.wagenstandsanzeiger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;



@RestController
@RequestMapping("/station")
public class AnzeigerController {

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    @GetMapping({"/{ril100}/train/{trainNumber}/waggon/{number}"})
    public Response compareData(@PathVariable String ril100, @PathVariable String trainNumber, @PathVariable String number) throws IOException {

        //list holding all matches
        List<String> result;

        //if the user input does not match the specifications throw error
        if (ril100.length() < 2 || ril100.length() >5) {
            throw new WrongUserInputRil();
        }
        if (trainNumber.length() < 2 || trainNumber.length() > 4 || !Helper.isPositiveWholeNumber(trainNumber)){
            throw new WrongUserInputTrainNumber();
        }
        if (number.length() < 1 || number.length() > 2 || !Helper.isPositiveWholeNumber(number)){
            throw new WrongUserInputWaggonNumber();
        }

        //Get the XML File and return the identifier matching the user input
        XMLHandler xmlHandler = new XMLHandler(resourcePatternResolver);
        result = xmlHandler.getDataFromXML(ril100,trainNumber,number);

        //if there are no matches, throw error
        if (result.isEmpty()) {
            throw new NoMatches();
        } else {
            //instantiate response-class to build custom json response
            return new Response(result);
        }
    }
}
