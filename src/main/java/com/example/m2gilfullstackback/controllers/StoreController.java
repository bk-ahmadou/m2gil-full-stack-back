package com.example.m2gilfullstackback.controllers;

import com.example.m2gilfullstackback.dtos.*;
import com.example.m2gilfullstackback.entities.Schedule;
import com.example.m2gilfullstackback.entities.Store;
import com.example.m2gilfullstackback.exceptions.ResourceNotFoundException;
import com.example.m2gilfullstackback.repositories.ProductRepository;
import com.example.m2gilfullstackback.repositories.ScheduleRepository;
import com.example.m2gilfullstackback.repositories.StoreRepository;
import com.example.m2gilfullstackback.repositories.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@RestController
@RequestMapping("/stores")
public class StoreController {
    private MapStructMapper mapStructMapper;
    private StoreRepository storeRepository;
    private ProductRepository productRepository;
    private ScheduleRepository scheduleRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public StoreController(
            MapStructMapper mapStructMapper,
            StoreRepository storeRepository,
            ProductRepository productRepository,
            ScheduleRepository scheduleRepository){
        this.mapStructMapper = mapStructMapper;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<Void> create(@RequestBody StorePostDto storePostDto) {

        Store store = mapStructMapper.shopPostDtoToShop(storePostDto);
        Store storeToReturn = storeRepository.save(store);

        List<Schedule> schedules = storeToReturn.getSchedules();
        schedules.forEach(schedule -> {
            schedule.setShop(storeToReturn);
        });

        scheduleRepository.saveAll(schedules);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = {"application/json"})
    public  ResponseEntity<Void> updateShop(@PathVariable UUID id, @RequestBody StoreUpdateDto storeUpdateDto){

        Store store = storeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Store does not exist"));

        mapStructMapper.updateShopFromDto(storeUpdateDto, store);

        storeRepository.save(store);

        if(!storeUpdateDto.getSchedules().isEmpty()){
            updateStoreSchedules(storeUpdateDto.getSchedules());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void updateStoreSchedules(@RequestBody List<ScheduleGetDto> dtos){

        dtos.forEach(dto -> {
            Schedule schedule = scheduleRepository.findById(dto.getId()).get();
            mapStructMapper.updateSchedulesFromDto(dto,schedule);
            scheduleRepository.save(schedule);
        });

    }


    @GetMapping()
    public ResponseEntity<List<StoreGetDto>> getAll(){

        List<StoreGetDto> list = new ArrayList<>();

        storeRepository.findAll().forEach(shop -> list.add(mapStructMapper.shopToShopGetDto(shop)));

        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StoreGetDto> getById(
            @PathVariable(value = "id") UUID id
    ) {
        return new ResponseEntity<>(
                mapStructMapper.shopToShopGetDto(
                        storeRepository.findById(id).get()
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStore(@PathVariable UUID id){

        storeRepository.deleteById(id);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
