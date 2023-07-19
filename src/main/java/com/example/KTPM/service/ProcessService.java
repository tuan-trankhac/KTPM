package com.example.ITSSBE.service;

import com.example.ITSSBE.converter.ProcessConverter;
import com.example.ITSSBE.dto.ProcessDTO;
import com.example.ITSSBE.entity.Process;
import com.example.ITSSBE.entity.Register;
import com.example.ITSSBE.repository.IProcessRepo;
import com.example.ITSSBE.repository.IRegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessService {
    @Autowired
    private IProcessRepo processRepo;
    @Autowired
    private IRegisterRepo registerRepo;
    @Autowired
    private ProcessConverter processConverter;

    public List<ProcessDTO> getAllProcess() {
        List<Process> processes = processRepo.findAll();
        return processes.stream().map(process -> processConverter.toDTO(process)).collect(Collectors.toList());

    }

    public ProcessDTO addProcess(ProcessDTO processDTO) {
        Register register = registerRepo.findFirstById(processDTO.getRegister_id());
        Process process = processConverter.toEntity( processDTO, register);
        Process savedProcess = processRepo.save(process);
        return processConverter.toDTO(savedProcess);
    }
}
