package com.example.freeaccess.service.bullying;

import com.example.freeaccess.domain.bullying.BullyingDTO;
import com.example.freeaccess.repository.BullyingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FindBullying {

    private final BullyingRepository repository;
    private final ModelMapper modelMapper;

    public FindBullying(BullyingRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public BullyingDTO execute() {
        return this.modelMapper.map(this.repository.findAll().stream().findFirst().orElseThrow(), BullyingDTO.class);
    }

}
