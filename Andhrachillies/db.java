import com.mongodb.client.*;
import org.bson.Document;

public class db {
    private static MongoCollection<Document> users;
    private static MongoCollection<Document> orders;

    static {
        MongoClient client = MongoClients.create(
            "mongodb+srv://ppraveen2150_db_user:3ugTieTt1dY5DjlY@cluster0.ikfdg4a.mongodb.net/?retryWrites=true&w=majority"
        );
        MongoDatabase database = client.getDatabase("andhra_chillies");
        users = database.getCollection("users");
        orders = database.getCollection("orders");
    }

    public static boolean registerUser(String name, String pass) {
        if (users.find(new Document("name", name)).first() != null) return false;
        users.insertOne(new Document("name", name).append("pass", pass));
        return true;
    }

    public static boolean loginUser(String name, String pass) {
        return users.find(new Document("name", name).append("pass", pass)).first() != null;
    }

    public static void placeOrder(String name, String food, String quantity) {
        orders.insertOne(new Document("name", name)
            .append("food", food)
            .append("quantity", quantity)
            .append("status", "Ordered"));
    }
}
