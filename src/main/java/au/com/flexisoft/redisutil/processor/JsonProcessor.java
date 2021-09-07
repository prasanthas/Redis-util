package au.com.flexisoft.redisutil.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonProcessor {

    public <T> List<T> retrieveData(String jsonPath, Class<T> typeClass) throws IOException {
        File file = new File(jsonPath);
        System.out.println(file.getAbsoluteFile());
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, typeClass);
        List<T> list = mapper.readValue(file, listType);
        return list;
    }

}