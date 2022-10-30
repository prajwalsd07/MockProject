package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.dto.DiagnosticDTO;
import com.demo.spring.entity.Diagnostic;
import com.demo.spring.exceptions.DiagnosticNotFoundException;
import com.demo.spring.exceptions.DiagnosticTestExistsException;
import com.demo.spring.repositories.DiagnosticRepository;
import com.demo.spring.util.Message;

@Service
public class DiagnosticService {
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	@Autowired
	DiagnosticRepository diagnosticRepository;
	
	public List<Diagnostic> listAllDiagnosticService(){
		logger.info("all diagnostic services returned succefully");
		return diagnosticRepository.findAll();
	}
	
	public Message addTestService(DiagnosticDTO diagnosticDTO) throws DiagnosticTestExistsException {
		if(diagnosticRepository.existsById(diagnosticDTO.getDiagnosticID())) {
			logger.error("Exception : Diagnostic Exists Exception thrown");
			throw new DiagnosticTestExistsException();
		}else {
			Diagnostic diagnostic = new Diagnostic(diagnosticDTO.getDiagnosticID(), diagnosticDTO.getDiagnosticName());
			diagnosticRepository.save(diagnostic);
			logger.info("new Diagnostic test added succefully");
			return new Message("New test added");
		}
	}
	
	public Message deleteTestService(Integer id) throws DiagnosticNotFoundException {
		Optional<Diagnostic> diagnostic = diagnosticRepository.findById(id);
		if(diagnostic.isPresent()) {
			diagnosticRepository.delete(diagnostic.get());
			logger.info(" diagnostic test deleted succefully");
			return new Message("Test deleted");
		}else {
			logger.error("Exception : Diagnostic not found Exception thrown");
			throw new DiagnosticNotFoundException();
		}
	}

}
