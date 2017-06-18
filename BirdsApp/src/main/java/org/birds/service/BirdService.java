package org.birds.service;

import org.birds.service.utils.ServiceUtils;
import org.codehaus.jackson.JsonProcessingException;

import javax.ws.rs.WebApplicationException;
import org.birds.service.dao.BirdDao;
import org.birds.service.dao.BirdDaoImpl;


import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/*
    Models and implements the Bird Service for various actions.
    The Web framework ie Jersey routes HTTP requests(methods) or REST methods to the
    functions in this class. This class uses the annotations in JAX-RS framework
    and keeps concise by reducing boilerplate code.
 */
@Path("/")
public class BirdService {
    //Logger
    private static final Logger serviceLogger =  Logger.getLogger(BirdService.class.getName());
    private BirdDao bdao ;
    public BirdService(){
        this.bdao = new BirdDaoImpl();

    }
    public void setBao(BirdDao dao){
        this.bdao = dao;
    }

    @GET
    @Path("/birds")
    @Produces(MediaType.APPLICATION_JSON)
    //Returns List of visible Bird Ids
    public List<String> getBirds(){

        List<String> bList = null;
        bList = this.bdao.getBirds();
        if(null == bList){
            return new ArrayList<String>(); //Empty if null, not NOT FOUND
        }
        return bList;
    }

    @GET
    @Path("/bird/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Bird getBird(@PathParam("id") String id){

       Bird bird = null;
       bird = bdao.getBird(id);
       if(null == bird ){
           serviceLogger.info("No Bird, 404 resp");
           throw  new WebApplicationException(Response.Status.NOT_FOUND);
       }
       return bird;

    }

    @POST
    @Path("/bird")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBird(Bird bird, @Context UriInfo uriInfo) throws EOFException, JsonProcessingException{

        String name = bird.getName();
        //Make necessary checks here itself - those which can be without consulting database
        if(name == null || name.trim().length() == 0){
            serviceLogger.info("DEBUG:name empty");
            throw  new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        String family = bird.getFamily();
        if(null == family  || family.trim().length() == 0){
            serviceLogger.info("DEBUG:family empty");
            throw  new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        ArrayList<String> conts = bird.getContinents();
        if(ServiceUtils.isListEmpty(conts)){
            serviceLogger.info("DEBUG:conitenents empty");
            throw  new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        //Also check if continents are  unique - didnt use Set instead of list as need to notify user wrong input
        //It is a bad request as 201 OK will not be appropriate in this situation.
        // Did not auto correct to unique items as it has no value and it maybe have been a client mistake
        if(!ServiceUtils.isListUnique(conts)){
            serviceLogger.info("DEBUG:conitenents duplicated");
            throw  new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Bird addedBird = this.bdao.createBird(bird);
        if(null == addedBird){
            serviceLogger.info("DEBUG:BIRD Not ADDED ");
            throw  new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        String id = addedBird.getId();
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(id);
        return Response.created(builder.build()).entity(addedBird).build();
    }

    @DELETE
    @Path("/bird/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBird(@PathParam("id") String id){

        if(this.bdao.deleteBird(id)){
            serviceLogger.finest(" DEBUG:Bird deleted successfully");
            return Response.ok().build();
        }
        else{
            serviceLogger.info("Bird doesnt exist");
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}
