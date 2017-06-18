package org.birds.service.dao;

import com.mongodb.*;
import org.birds.service.Bird;
import org.birds.service.utils.ServiceUtils;
import org.bson.types.ObjectId;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


import java.lang.Exception;
import java.util.List;
import java.util.logging.Logger;

/*
  Implementation of Data access Layer or the DAO
 */
public class BirdDaoImpl implements BirdDao {

    //Logger
    private static final  Logger daoLogger =  Logger.getLogger(BirdDaoImpl.class.getName());
    private final String connectionIP = "localhost";
    private final int connectionPort = 27017;
    private final String dbName = "birdsData";
    private final String collName = "birds";
    //MongoClient internally uses connection pooling
    private MongoClient mongoClient;
    private DB mongoDb;
    private DBCollection table;


    public BirdDaoImpl(){
        try{
            daoLogger.finest(" DEBUG:Setting up Database in DAO");
            this.mongoClient = new MongoClient(this.connectionIP, this.connectionPort);
            this.mongoDb = this.mongoClient.getDB(this.dbName);
            if(null != this.mongoClient && null != this.mongoDb){
                this.table = this.mongoDb.getCollection(this.collName);
            }
        }
        catch(Exception e){
            this.mongoClient =  null;
            this.mongoDb = null;
            e.printStackTrace();

        }

    }


    public Bird getBird(String Id){
        if(null == this.mongoClient || null == this.mongoDb){
            daoLogger.severe("DATABASE CONNECTION ERROR!!");
            return null;
        }
        Bird bird = null;
        DBObject dbo = null;
        try{

            BasicDBObject query=new BasicDBObject("_id",new ObjectId(Id));
            dbo = this.table.findOne(query);
            if(null == dbo){
                daoLogger.info("Bird nit found");
                return null;
            }
            bird = DaoUtils.getPojo(dbo, Bird.class);
        }

        catch (Exception e){
            daoLogger.severe(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return bird;


    }


    public List<String> getBirds(){

        if(null == this.mongoClient || null == this.mongoDb){
            daoLogger.severe("DATABASE CONNECTION ERROR!!");
            return null;
        }
        List<String> birdList = null;
        DBCollection table = null;
        List<DBObject> all = null;
        try{

             all = this.table.find().toArray();
            daoLogger.finest("DEBUG:Number of birds in DB: " + all.size());
             if (null != all){
                 birdList = new ArrayList<String>();
                 for(DBObject dbObj: all){
                     Bird bird = DaoUtils.getPojo(dbObj, Bird.class);
                     if(bird.getVisible()){
                         birdList.add(bird.getId());
                     }

                 }
             }

        }

        catch(IOException ie){
            daoLogger.severe(ie.getMessage());
            ie.printStackTrace();
            return new ArrayList<String>();
        }
        catch(Exception e){
            daoLogger.severe(e.getMessage());
            e.printStackTrace();
            return new ArrayList<String>();
        }
        if(birdList.size() == 0){
            daoLogger.info("NO BIRDS VISISBLE");
        }
        return  birdList;
    }


    public Bird createBird(Bird bird){

        Bird addedBird =  null;

        if(null == this.mongoClient || null == this.mongoDb){
            daoLogger.severe("DATABASE CONNECTION ERROR!!");
            return null;
        }

        try{
            BasicDBObject document = new BasicDBObject();
            //Can make a check for name uniqueness before adding.But it isnt a part of specification
            //and is not destructive to have name duplicates
            String name = bird.getName();
            String family = bird.getFamily();
            ArrayList<String> conts = bird.getContinents();

            document.put("name", name);
            document.put("family", family);
            document.put("continents", conts);
            document.put("visible", bird.getVisible());
            String date = bird.getAdded();
            //If date is provided well and good, else use current system date - Simple solution, Data synchronization ignored
            if(null == date  || date.length() == 0){
                date = DaoUtils.getDateStr(new Date());
            }
            else if(!DaoUtils.isDateProper(date, "yyyy-MM-dd")){
                daoLogger.info("Invalid date format");
                return null;
            }

            document.put("added", date);
            table.insert(document);

            //get Id of just inserted doc
            ObjectId id = (ObjectId)document.get( "_id" );
            //Recreate newly added bird and return
            addedBird = (Bird)bird.clone();
            addedBird.setId(id.toString());
            addedBird.setAdded(date);
        }

        catch(Exception e){
            daoLogger.severe(e.getMessage());
            e.printStackTrace();
            return null;
        }
        return addedBird;
    }


    public boolean deleteBird(String Id){
        if(null == this.mongoClient || null == this.mongoDb){
            return false;
        }

        WriteResult res = null;
        try{
            BasicDBObject document = new BasicDBObject();
            document.put("_id", new ObjectId(Id));
            res = table.remove(document);
            if(null == res || res.getN() == 0){
                daoLogger.info("BIRD DOES NOT EXIST");
                return false;
            }
            daoLogger.finest("REMOVED BIRD");
        }
        catch(Exception e){
            daoLogger.severe(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
