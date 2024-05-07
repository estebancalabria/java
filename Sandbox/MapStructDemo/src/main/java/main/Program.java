package main;

import org.mapstruct.factory.Mappers;

import models.*;

import mappers.SimpleSourceDestinationMapper;

public class Program {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleSourceDestinationMapper mapper = Mappers.getMapper(SimpleSourceDestinationMapper.class);
		Source s = new models.Source();
		s.setName("asa");
		s.setDescription("asa");
		
		Destination d = mapper.sourceToDestination(s);
		System.out.println(d.getName());
	}

}
