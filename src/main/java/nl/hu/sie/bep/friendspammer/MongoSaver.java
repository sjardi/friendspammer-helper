package nl.hu.sie.bep.friendspammer;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

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

	public Document getMessages() {
		String database = "BEP";
		Document email = null;
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://SDW:SDW@bep-ppqy9.azure.mongodb.net/test"));

		MongoDatabase db = mongoClient.getDatabase(database);

		MongoCollection<Document> c = db.getCollection("email");

		Iterator<Document> it = c.find().iterator();

		while (it.hasNext()) {
			 email = it.next();

		}

		mongoClient.close();
		return email;
	}
}
