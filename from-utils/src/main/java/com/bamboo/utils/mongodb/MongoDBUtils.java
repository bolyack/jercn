package com.bamboo.utils.mongodb;

import com.bamboo.utils.io.FileOperUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MongoDB Utils
 * Created by admin on 2017/3/14.
 */
public class MongoDBUtils {

    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;

    static {
        // connection mongodb server
        mongoClient = new MongoClient( "192.168.83.50" , 27017 );

        // connection the db
        mongoDatabase = mongoClient.getDatabase("akweb");
        System.out.println("Connect to database successfully");
    }

    /**
     * save common document to db
     */
    public static void saveCommonSingleDocument(String collectionName, Map<String ,Object> map) {
        // select a category to store document from db
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

        Document document = new Document();
        if (null != map && map.size()> 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                document.append(entry.getKey(), entry.getValue());
            }
        }

        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);
        System.out.println("insert document successful !");
    }

    /**
     *  save file document
     * @param dbName
     * @param collectionName
     * @param fileName
     * @param aliases
     * @param map attribute map(key-value pair)
     * @return  GridFSInputFile
     * @throws Exception
     */
    public static GridFSInputFile saveFileDocument(String dbName, String collectionName, String fileName, String aliases, Map<String, Object> map) throws Exception {
        DB db = new DB(mongoClient, dbName);
        GridFS gridFS = new GridFS(db, collectionName);

        File file = new File(fileName);

        GridFSInputFile gfs = gridFS.createFile(file);

        if (null != map && map.size()> 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                gfs.put(entry.getKey(), entry.getValue());
            }
        }

        gfs.put("filename", fileName);
        gfs.put("aliases", aliases);
        gfs.put("path", fileName);
        gfs.put("contentType", fileName.substring(fileName.lastIndexOf(".") + 1));
        gfs.put("prefixName", fileName.substring(fileName.lastIndexOf(File.separator) + 1, fileName.lastIndexOf(".")));
        gfs.save();
        System.out.println(gfs.getId());
        return gfs;
    }

    /**
     * query the file from mongoDB by file name
     *  If coverFileName is empty, then use the default file name; otherwise use the newly wrapped file path and name
     * @param dbName
     * @param collectionName
     * @param fileName
     * @param coverFileName
     * @throws Exception
     */
    public static void queryFileByFileName(String dbName, String collectionName, String fileName, String coverFileName) throws Exception {
        DB db = new DB(mongoClient, dbName);
        // 获取fs的根节点
        GridFS gridFS = new GridFS(db, collectionName);
        GridFSDBFile dbfile = gridFS.findOne(fileName);

        if (dbfile != null) {
            fileExce(dbfile, coverFileName);
        }

    }

    /**
     * query the file from mongoDB by ObjectId
     *  If coverFileName is empty, then use the default file name; otherwise use the newly wrapped file path and name
     * @param dbName
     * @param collectionName
     * @param objectId
     * @param coverFileName
     * @throws Exception
     */
    public static void queryFileByObjectId(String dbName, String collectionName, String objectId, String coverFileName) throws Exception {
        DB db = new DB(mongoClient, dbName);
        // 获取fs的根节点
        GridFS gridFS = new GridFS(db, collectionName);

        ObjectId oID = new ObjectId(objectId);
        GridFSDBFile dbfile = gridFS.findOne(oID);

        if (dbfile != null) {
            fileExce(dbfile, coverFileName);
        }

    }

    /**
     * query the file from mongoDB by Properties
     *  If coverFileName is empty, then use the default file name; otherwise use the newly wrapped file path and name
     * @param dbName
     * @param collectionName
     * @param map
     * @param coverFileName
     * @throws Exception
     */
    public static void queryFileByProperties(String dbName, String collectionName, Map<String, Object> map, String coverFileName) throws Exception {
        DB db = new DB(mongoClient, dbName);
        // 获取fs的根节点
        GridFS gridFS = new GridFS(db, collectionName);

        DBObject object = new BasicDBObject();
        if (null != map && map.size()> 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                object.put(entry.getKey(), entry.getValue());
            }
        }
        GridFSDBFile dbfile = gridFS.findOne(object);

        if (dbfile != null) {
            fileExce(dbfile, coverFileName);
        }

    }


    private static void fileExce(GridFSDBFile dbfile, String coverFileName) throws Exception {
        InputStream in = dbfile.getInputStream();

        File outFile = null;
        if (null != coverFileName && !"".equals(coverFileName)) {
            outFile = FileOperUtils.createFile(coverFileName);
        } else {
            outFile = FileOperUtils.createFile(dbfile.getFilename());
        }

        FileOperUtils.writeFile(in, outFile);
    }


    public static void main(String[] args) throws Exception {
       /* Map map = new HashMap();
        map.put("key1", "va1");
        map.put("key2", 10);
        map.put("key3", "val3");
        saveCommonSingleDocument("te",map);*/

        /*String fileName = "C:\\Users\\admin\\Desktop\\1\\tmp\\20170314-103701-1e84\\mydocker\\home\\dockerlog\\10101.log";
        Map map = new HashMap();
        map.put("key1", "va1");
        map.put("key2", 20);
        map.put("key3", "val3");
        GridFSInputFile f = saveFileDocument("akweb", "teColl" , fileName, "testfile", map);
        System.out.println("File ID:" + f.getId() + ", File MD5:" + f.getMD5());*/


//        queryFileByFileName("akweb", "test1", "D:\\software\\elasticsearch-2.3.3.zip", "E:\\test\\el.zip");
    }

}
