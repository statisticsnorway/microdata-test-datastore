package no.microdata.datastore.adapters.api.dto;

import no.microdata.datastore.exceptions.BadRequestException;
import no.microdata.datastore.exceptions.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputFixedQuery extends InputQuery{
    final static Logger log = LoggerFactory.getLogger(InputFixedQuery.class);

    /**
     * Validates the input query.
     *
     * @return true if fixed query is valid, otherwise throws BadRequestException or UnauthorizedException
     * @throws BadRequestException if not valid
     * @throws UnauthorizedException if authentication fails
     */
    public boolean validate() {
        super.validate();
        return true;
    }

    @Override
    public String toString(){
        String string = String.format("{ dataStructureName: %1$s, version: %2$s", dataStructureName, version);
        if(hasValueFilter()){
            string.concat(String.format(", values.size(): %1$d", values.size()));
        }
        if(hasUnitIdFilter()){
            string.concat(String.format(", population filter size:  %1$d", populationFilter().size()));
        }
        if (hasIntervalFilter()){
            string.concat(String.format(", interval filter: %1$s", intervalFilter));
        }
        if (includeAttributes){
            string.concat(String.format(", includeAttributes: %1$b", includeAttributes));
        }
        return string.concat(" }");
    }
}