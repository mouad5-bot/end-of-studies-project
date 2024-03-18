package com.youcode.come2play.web.rest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youcode.come2play.dtos.dto.response.StadiumResponseDto;
import com.youcode.come2play.dtos.mapper.StadiumMapper;
import com.youcode.come2play.entities.Stadium;
import com.youcode.come2play.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/stadium")
public class StadiumResource {
    private final StadiumService service;
    private final ObjectMapper objectMapper;
    private final StadiumMapper stadiumMapper;

    @PostMapping("/add")
    public ResponseEntity<Stadium> add(@RequestParam("stadium") String stadiumStr, @RequestPart("image") MultipartFile file) throws Exception {
        Stadium stadium = objectMapper.readValue(stadiumStr, Stadium.class);
        return ResponseEntity.ok(service.save(stadium, file));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Stadium> edit(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.edit(id));
    }

    @GetMapping("/getAll")
    public List<StadiumResponseDto> getAll(Pageable pageable) {
        return service.findAll(pageable)
                .stream().
                    map(stadiumMapper::toDto)
                    .toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok("The stadium is deleted successfully");
    }
}
