package org.birds.service.dao;
import org.birds.service.Bird;
import java.util.ArrayList;
import java.util.List;

/*
Interface for  retrieving and Persisting Bird Data into Database
 */
public interface BirdDao {
    public Bird getBird(String Id);
    public List<String> getBirds();
    public Bird createBird(Bird bird);
    public boolean deleteBird(String Id);
}
