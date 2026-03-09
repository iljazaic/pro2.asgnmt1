package a1.impl.View;

import java.io.PrintWriter;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientManager {
    private static final CopyOnWriteArrayList<PrintWriter> clients = new CopyOnWriteArrayList<>();

    public static void addClient(PrintWriter writer) {
        clients.add(writer);
    }

    public static void removeClient(PrintWriter writer) {
        clients.remove(writer);
    }

public static void broadcast(String message, String eventname) {
    for (PrintWriter client : clients) {
        client.write("event: "+eventname+"\n");
        client.write("data: " + message + "\n\n");
        client.flush();
    }
}
}