package com.akbank.hw1.summarize;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.maven.artifact.repository.metadata.Plugin;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Developer;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "summarize", defaultPhase = LifecyclePhase.COMPILE)
public class Summarize extends AbstractMojo{

	@Parameter(defaultValue="${project}",required=true, readonly = true)
	MavenProject project;
	
	@Parameter(property = "fileName") // Parameter for getting file name from user to print all information.
	String fileName;
	
	@SuppressWarnings("unchecked")
	public void execute() throws MojoExecutionException, MojoFailureException{
		
		// Getting project details by using MavenProject class' object.

		String projectVersion = project.getVersion();
		String groupId = project.getGroupId();
		String artifactId = project.getArtifactId();
		List<Dependency> dependencies = project.getDependencies();
		List<Developer> developers = project.getDevelopers();
		Set<Plugin> plugins = project.getPluginArtifacts();
		String releaseDate = project.getProperties().getProperty("release.date");
		

		getLog().info("Project info :"+ groupId + "."+ artifactId +"." + projectVersion); // Printing project info
		
		getLog().info("---Developers---"); // Declaration of starting print section for the developer names.
		for(int i = 0 ; i < developers.size() ; i++) {
			getLog().info("Developer: " + (i+1) + " Name: " + developers.get(i).getName()); // Printing the developers' names.
		}
		
		
		getLog().info("Release Date: " + releaseDate); // Printing the release date of the project.
		
		getLog().info("---Dependencies---"); // Declaration of starting print section for the dependencies' information.
		for(int i = 0 ; i < dependencies.size();i++) {
			getLog().info("Dependency: "+dependencies.get(i).getGroupId()+"."+dependencies.get(i).getArtifactId());  // Printing the dependencies.
		}
		
		getLog().info("---Plugins---"); // Declaration of starting print section for the plugins' information.
		Iterator<Plugin> itr = plugins.iterator();
		while(itr.hasNext()){
			getLog().info("Plugin: "+ itr.next()); // Printing the plugins.
		}
		
		
		// Trying to open a file and if the file does not exist, create a new one with the 'fileName'.
		try {
		      FileWriter myWriter = new FileWriter("./target/"+fileName+".txt");
		      myWriter.flush(); // To prevent unnecessary fulliness, before printing the summarize of the project, cleaning the file.
		      
		      // Writing project info to the file.
		      myWriter.write("Project info :"+ groupId + "."+ artifactId +"." + projectVersion);
		      myWriter.write("\n"); // The reason why I put \n is that provide a clear understanding in the file.
		      
		      
		      myWriter.write("---Developers---"); 
		      myWriter.write("\n");
		      for(int i = 0 ; i < developers.size();i++) {
		    	  myWriter.write("Developer: " + (i+1) + " Name: " + developers.get(i).getName()); // Writing developers' information to the file.
		    	  myWriter.write("\n");
		      }
		      
		      
		      myWriter.write("Release Date: " + releaseDate);
		      myWriter.write("\n");
		      
		      myWriter.write("---Dependencies---");
		      for(int i = 0 ; i < dependencies.size();i++) {
					myWriter.write("Dependency: "+dependencies.get(i).getGroupId()+"."+dependencies.get(i).getArtifactId()); // Writing dependencies' information to the file.
					myWriter.write("\n");
		      }
		      
		      
		      myWriter.write("---Plugins---");
		      myWriter.write("\n");
		      Iterator<Plugin> itrFile = plugins.iterator();
		      while(itrFile.hasNext()){
		    	  myWriter.write("Plugin: "+ itrFile.next()); // Writing plugins' information to the file.
		    	  myWriter.write("\n");
		      }
		      
		      
		      myWriter.close(); // Closing the file after writing part is over.
		} catch (IOException e) {
		      System.out.println("An error occurred."); // Throwing an Exception if there will be some problems while creating or opening the file.
		      e.printStackTrace();
		}
		
	}
	
}
