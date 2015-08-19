package com.pradip.javamongo;

import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnknownHostException
    {
        System.out.println( "Hello World!" );
        
        
        // connect to mongodb
        MongoClient mongo=new MongoClient("localhost", 27017);
        System.out.println(mongo);
        
        // get database if not than create
        DB db= mongo.getDB("testdb");
        
        System.out.println(db);
        
        // get collection, if not than create it
        DBCollection table= db.getCollection("user");
        
        // create document insert
        BasicDBObject document=new BasicDBObject();
        
        document.put("name", "pradip");
        document.put("age", 10);	
        document.put("createdDate", new Date());
        
      //  table.insert(document);
        
        
        // find and display
        
        BasicDBObject searchQuery=new BasicDBObject();
        
        searchQuery.put("name", "pradip");
        
        DBCursor cursor= table.find(searchQuery);
        
        while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
        
        // update document
        
        
        //1. object for search
        BasicDBObject searchquery1=new BasicDBObject();
        searchquery1.put("name", "pradip");
        
        //2. object  for update document
        BasicDBObject newdocument=new BasicDBObject();
        newdocument.put("name","pradip-updated");
        
        //3. object for change
        
        BasicDBObject updateDocument=new BasicDBObject();
        updateDocument.put("$set", newdocument);
        
        // final update on table
        
        table.update(searchquery1, updateDocument);
        
        // updated value
        
        BasicDBObject updatedValue=new BasicDBObject();
        
        updatedValue.put("name", "pradip-updated");
        
        DBCursor curser2= table.find(updatedValue);
        
        while (curser2.hasNext()) {
			System.out.println(curser2.next());
		}
        
        BasicDBObject remove=new BasicDBObject();
        remove.put("name", "pradip-updated");
        
        table.remove(remove);
        
        table.insert(document);
        DBCursor curser3= table.find();
        
        if(curser3!=null){
        while (curser3.hasNext()) {
			System.out.println(curser3.next());
		}
        
       }
        else{
        	System.out.println("sorryy !!!");
        }
        
    }
}
