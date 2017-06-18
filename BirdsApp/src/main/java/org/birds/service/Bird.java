package org.birds.service;
import java.util.ArrayList;
import java.util.Collections;
/*
POJO - Plain Old JAva object class for modelling the Bird
 */
public class Bird implements Cloneable{
    private String _id;
    private String name;
    private String family;
    private String added;
    private ArrayList<String> continents;
    private boolean visible;

    public void setId(String  id){
        this._id = id;
    }
    public String getId(){
        return this._id;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setFamily(String fam){
        this.family = fam;
    }
    public String getFamily(){
        return this.family;
    }

    public void setAdded(String added){
        this.added = added;
    }
    public String getAdded(){
        return this.added;
    }

    public void setContinents(ArrayList<String> conts){
        this.continents = new ArrayList<String>(conts.size());

        for(String cont : conts){
            this.continents.add(cont);
        }
        Collections.copy(this.continents, conts);
    }
    public ArrayList<String> getContinents(){
        if(null == this.continents){
            this.continents = new ArrayList<String>();
        }
        return this.continents;
    }

    public void setVisible(boolean  vis){
        this.visible = vis;
    }
    public boolean getVisible(){
        return this.visible;
    }


    public Object clone()throws CloneNotSupportedException {
        return super.clone();
    }


}
