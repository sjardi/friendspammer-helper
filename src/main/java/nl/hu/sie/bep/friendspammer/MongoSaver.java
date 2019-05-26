package nl.hu.sie.bep.friendspammer;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import nl.hu.sie.bep.friendspammer.DTO.Message;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MongoSaver {

	public static boolean saveEmail(String to, String from, String subject, String text, Boolean html) {

		String database = "BEP";

		boolean success = true;

		try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://SDW:SDW@bep-ppqy9.azure.mongodb.net/test?retryWrites=true"))) {

			MongoDatabase db = mongoClient.getDatabase(database);

			MongoCollection<Document> c = db.getCollection("email");

			Document doc = new Document("to", to)
					.append("from", from)
					.append("subject", subject)
					.append("text", text)
					.append("asHtml", html);
			c.insertOne(doc);
		} catch (MongoException mongoException) {
			Logger logger = LoggerFactory.getLogger(MongoSaver.class);
			logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE SAVING TO MONGO XXXXXXXXXXXXXXXXXXXXXXXXXX");

			logger.info(mongoException.getMessage());
			success = false;
		}

		return success;

	}

	public List<Message> getMessages() {
	    List<Message> messages = new ArrayList<>();
	    Message actualMessage = new Message();
		String database = "BEP";
		Document email = null;
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://SDW:SDW@bep-ppqy9.azure.mongodb.net/test"));

		MongoDatabase db = mongoClient.getDatabase(database);

		MongoCollection<Document> c = db.getCollection("email");

		Iterator<Document> it = c.find().iterator();

		while (it.hasNext()) {
			 email = it.next();
			 System.out.println("in get message" + it.next());

		}
		actualMessage.to = "hoi";
        messages.add(actualMessage);
		mongoClient.close();
		return messages;
	}
}
