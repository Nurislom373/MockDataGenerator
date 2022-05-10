package uz.jl.mockdata.mockdatagenerator.data;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.jl.mockdata.mockdatagenerator.data.dto.DataCreateDTO;

import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/data/*")
public class DataController {
    private final DataService service;

    @RequestMapping(value = "generate", method = RequestMethod.POST)
    public ResponseEntity<String> generateData(@RequestBody DataCreateDTO dto) {
        return new ResponseEntity<>(service.generate(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "get/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource getData(@PathVariable String name) throws MalformedURLException {
        return new FileUrlResource("src/main/resources/file/" + name);
    }
}
