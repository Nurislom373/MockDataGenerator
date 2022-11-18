package uz.jl.mockdata.mockdatagenerator.data;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.jl.mockdata.mockdatagenerator.data.dto.DataCreateDTO;
import uz.jl.mockdata.mockdatagenerator.response.Data;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/data/*")
public class DataController {
    private final DataService service;

    @RequestMapping(value = "generate", method = RequestMethod.POST)
    public ResponseEntity<Data<UUID>> generateData(@RequestBody DataCreateDTO dto) {
        return new ResponseEntity<>(new Data<>(service.generate(dto)), HttpStatus.OK);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> get(@PathVariable UUID id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }
}
