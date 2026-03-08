package a1.impl;

import java.io.PrintWriter;

import a1.impl.Model.BorrowingManager;
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

        Spark.port(8080);


        get("/login", (req,res)->{




            return null;
        });


        get("/updates", (req, res) -> {
            res.type("text/event-stream");
            res.header("Cache-Control", "no-cache");
            res.header("Connection", "keep-alive");
            res.header("Access-Control-Allow-Origin", "*");

            PrintWriter writer = res.raw().getWriter();
            ClientManager.addClient(writer);
            try {
                while (!writer.checkError()) {
                    Thread.sleep(1000);
                }
            } finally {
                ClientManager.removeClient(writer);
            }

            return null;
        });

    }
}