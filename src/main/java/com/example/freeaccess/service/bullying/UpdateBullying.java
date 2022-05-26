package com.example.freeaccess.service.bullying;

import com.example.freeaccess.domain.bullying.Bullying;
import com.example.freeaccess.domain.bullying.BullyingDTO;
import com.example.freeaccess.repository.BullyingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UpdateBullying {

    private final BullyingRepository repository;
    private final ModelMapper modelMapper;

    public UpdateBullying(BullyingRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public BullyingDTO execute(BullyingDTO bullyingDTO) {
        Bullying bullying = this.repository.findAll().stream().findAny().orElse(new Bullying());

        bullyingDTO.setId(bullying.getId());
        this.modelMapper.map(bullyingDTO, bullying);

        bullying = this.repository.save(bullying);

        return this.modelMapper.map(bullying, BullyingDTO.class);
    }

}
