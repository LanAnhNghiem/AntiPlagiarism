package com.smlteam.textsimilarity.services;

import ai.vitk.tok.Tokenizer;
import ai.vitk.type.Token;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lan Anh
 */
public class Indexer {

    public void indexer(String originStr, String testStr, String index){
        String indexPath = Constants.INDEX +"/index"+ index;

        String docsPath = Constants.DOCS;

        boolean create = true;

        if(docsPath == null){
            System.exit(1);
        }

        final Path docDir = Paths.get(docsPath);
        try{
            Directory dir = FSDirectory.open(Paths.get(indexPath));
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

            if (create) {
                // Create a new index in the directory, removing any
                // previously indexed documents:
                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            } else {
                // Add new documents to an existing index:
                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            }
            //set RAM size
            iwc.setRAMBufferSizeMB(256.0);

            IndexWriter writer = new IndexWriter(dir, iwc);

            List<String> documents = new LinkedList<>();
            documents.add(originStr);
            documents.add(testStr);
            indexDocs(writer, docDir, documents);
            writer.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //index một tập các văn bản
    public static void indexDocs(final IndexWriter writer, Path path, List<String> contents) throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for(String content: contents){
            indexDoc(writer, path, timestamp.getTime(), content);
        }
    }

    //index một văn bản
    public static void indexDoc(IndexWriter writer, Path file, long lastModified, String contents) throws IOException {
        Document doc = new Document();
        Field pathField = new StringField("path", file.toString(), Field.Store.YES);
        doc.add(pathField);
        doc.add(new LongPoint("modified", lastModified));
        FieldType myFieldType = new FieldType(TextField.TYPE_STORED);
        myFieldType.setStoreTermVectors(true);
        doc.add(new Field("contents", contents, myFieldType));
        if(writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE){
            writer.addDocument(doc);
        }else{
            writer.updateDocument(new Term("path", file.toString()), doc);
        }
    }
    public static void convertTime(double seconds){
        double min = seconds/60;
        double sec = seconds-min*60;
        System.out.print("\n"+ min +" minutes "+sec+" seconds");
    }
}
