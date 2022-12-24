package uz.jl.mockdata.mockdatagenerator.data;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import uz.jl.mockdata.mockdatagenerator.data.dto.DataCreateDTO;
import uz.jl.mockdata.mockdatagenerator.data.new_version.NewDataCreateDTO;
import uz.jl.mockdata.mockdatagenerator.data.new_version.NewDataService;
import uz.jl.mockdata.mockdatagenerator.response.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/data/*")
public class DataController {

    private final DataService service;
    private final NewDataService newDataService;

    @RequestMapping(value = "generate", method = RequestMethod.POST)
    public ResponseEntity<Data<UUID>> generateData(@Valid @RequestBody DataCreateDTO dto) {
        return new ResponseEntity<>(new Data<>(service.generate(dto)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "new_generate", method = RequestMethod.POST)
    public ResponseEntity<Data<UUID>> newGenerateData(@Valid @RequestBody NewDataCreateDTO dto) {
        return new ResponseEntity<>(new Data<>(newDataService.generate(dto)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response, @PathVariable UUID id) throws IOException {
        File file = service.get(id);
        if (file.exists()) {
            String name = URLConnection.guessContentTypeFromName(file.getName());
            if (name == null) {
                name = "application/octet-stream";
            }
            response.setContentType(name);
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
            response.setContentLength((int) file.length());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(bufferedInputStream, response.getOutputStream());
        }
    }
}
