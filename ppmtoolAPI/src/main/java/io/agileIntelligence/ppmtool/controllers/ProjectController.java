package io.agileIntelligence.ppmtool.controllers;

import io.agileIntelligence.ppmtool.domain.Project;
import io.agileIntelligence.ppmtool.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    //private FieldError fieldError;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) throws Exception {

         if (result.hasErrors()){
             Map<String, String> errorMap = new HashMap<>();
             for (FieldError f : result.getFieldErrors()) errorMap.put(f.getField(), f.getDefaultMessage());
             return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
         }
         Project project1 = projectService.saveOrUpdateProject(project);
         return new ResponseEntity<Project>(project, HttpStatus.CREATED);
     }

     @GetMapping("/{projectId}")
     public ResponseEntity<?> getProjectById(@PathVariable String projectId){
        Project project = projectService.findProjectByIdentifier(projectId.toUpperCase());
       return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProject(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId.toUpperCase());
        return new ResponseEntity<String>("Project with id: '"+projectId+"' was deleted", HttpStatus.OK);
    }






}
