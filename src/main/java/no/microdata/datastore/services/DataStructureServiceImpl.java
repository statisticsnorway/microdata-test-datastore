package no.microdata.datastore.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.microdata.datastore.DataStructureService;
import no.microdata.datastore.model.MetadataQuery;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataStructureServiceImpl implements DataStructureService {

    @Override
    public List<Map<String, Object>> find(MetadataQuery query) {
        try {
            Map<String, Object> metadataAll = new ObjectMapper().readValue(
                    Files.readString(Paths.get(
                            "src/test/resources/no_ssb_test_datastore/metadata/metadata-all-test__1_0_0.json")), Map.class);
            List<Map<String, Object>> datasets = (List<Map<String, Object>>) metadataAll.get("dataStructures");
            List<Map<String, Object>> found = new ArrayList<>();

            for (Map<String, Object> dataset: datasets) {
                if (query.getNames().contains(dataset.get("name"))) {
                    found.add(dataset);
                }
            }

            return found;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
