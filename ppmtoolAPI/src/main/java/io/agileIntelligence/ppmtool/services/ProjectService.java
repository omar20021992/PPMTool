package io.agileIntelligence.ppmtool.services;

import io.agileIntelligence.ppmtool.domain.Project;
import io.agileIntelligence.ppmtool.repositories.ProjectRepository;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//import static sun.net.www.http.KeepAliveCache.result;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    public Project saveOrUpdateProject(Project project) throws Exception {

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase()) ;
            return projectRepository.save(project);
        } catch (Exception e){
            throw new ProjectIdException("The Project with id: '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId);
        if (project == null) throw new ProjectIdException("Project with id: '"+projectId+ "' doesn't exist");
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = findProjectByIdentifier(projectId);

        if (project == null) throw new ProjectIdException("Project with id: '"+projectId+ "' doesn't exist, so can't delete it");
        projectRepository.delete(project);
    }
}
