package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.repository.IBrendJpaRepo;
import model.Brend;

@Service("brendService")
public class BrendService implements IBrendService {
	
	private IBrendJpaRepo bJR;
	
	@Override
	public List<Brend> getAllBrendoviAutomobila() {
		List<Brend> brendoviAutomobila = bJR.findAll(); 
		return brendoviAutomobila;
	}
	
	@Autowired
	public void settJR(IBrendJpaRepo bJR) {
		this.bJR = bJR;
	}

}