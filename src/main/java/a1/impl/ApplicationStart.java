package a1.impl;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import a1.impl.Model.BorrowingManager;
import a1.impl.Model.User;
import a1.impl.Model.Vinyl;
import a1.impl.View.ClientManager;
import a1.impl.View.Controllers.UpdatingController;
import spark.Spark;
import static spark.Spark.*;

public class ApplicationStart {

    public ApplicationStart() {
    }

    public static void main(String[] args) {

        UpdatingController controller = new UpdatingController();
        BorrowingManager manager = new BorrowingManager(controller);


        for (int i = 1; i <= 10; i++) {
            manager.addVinyl(new Vinyl("V" + i));
        }



        Spark.port(1973);
        System.out.println("Web GUI running on http://localhost:1973");
        List<User> allUsers = new ArrayList<User>();

        get("/", (req, res) -> {
            res.type("text/html");
            return new String(Files.readAllBytes(Paths.get("src/main/java/a1/impl/View/public/index.html")));
        });


        get("/borrow", (req, res) -> {

            String userName = req.params().get("name");
            String vinylId = req.params().get("");

            for (User user : allUsers) {
                if (user.getName().equals(userName)) {
                    manager.attemptBorrowing(vinylId, user.getId());// this id is going on a journey all the way down to
                                                                    // the ReservedState class
                    break;
                }
            }

            return null;
        });

        get("/reserve", (req, res) -> {

            String userName = req.params().get("name");
            String vinylId = req.params().get("");

            for (User user : allUsers) {
                if (user.getName().equals(userName)) {
                    manager.attemptReservation(vinylId, userName);
                    break;
                }
            }

            return null;
        });

        get("/updates", (req, res) -> {
            String userName = req.params().get("name");// basically i create a user per connection. very inefficient but
                                                       // since security and persistence arent issues id rather write
                                                       // this in 5
                                                       // minutes than tinker with logins for an hour
            User user = new User(userName);
            allUsers.add(user);
            manager.addObservngUser(user);
            res.type("text/event-stream");
            res.header("Cache-Control", "no-cache");
            res.header("Connection", "keep-alive");
            res.header("Access-Control-Allow-Origin", "*");

            PrintWriter writer = res.raw().getWriter();
            ClientManager.addClient(writer);

            controller.setUserId(userName);
            controller.sendWhole(manager.getVinylList());
            try {
                while (!writer.checkError()) {
                    Thread.sleep(1000);// basically observer state pattern but as a web server; i need to keep the tcp
                                       // con alive until closed or crashed
                }
            } finally {
                ClientManager.removeClient(writer);
                allUsers.remove(user);// no memleaks
                manager.disconnectUser(user.getId());
                manager.removeObservingUser(user);
            }

            return null;
        });

    }
}