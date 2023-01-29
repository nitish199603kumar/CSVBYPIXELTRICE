package in.nitish.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.nitish.entity.DeveloperTutorial;
import in.nitish.helper.CSVHelper;
import in.nitish.repository.DeveloperTutorialRepository;


@Service
public class CSVService {
	
	  @Autowired
	  DeveloperTutorialRepository repository;

	  public void upload(MultipartFile file) {
	    try {
	    	System.out.println("(Service) File Name : " + file.getOriginalFilename());
	    	System.out.println("(Service) File Name : " + file.getName());
	    	System.out.println("(Service) File Name : " + file.getContentType());
	    	System.out.println("(Service) File Name : " + file.getSize());
	    	
	      List<DeveloperTutorial> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
	      repository.saveAll(tutorials);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store csv data: " + e.getMessage());
	    }
	  }

	  public ByteArrayInputStream load() {
	    List<DeveloperTutorial> tutorials = repository.findAll();

	    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
	    return in;
	  }

	  public List<DeveloperTutorial> getAllTutorials() {
	    return repository.findAll();
	  }


}
