import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class GUI {
    public static void mainScene(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(GUI.class.getResource("mainscene.fxml"));
        Parent root = loader.load();
        Controler controller = loader.getController();
        controller.setMainwindow(primaryStage);
        primaryStage.setTitle("Car Rental System");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
    public static void loginPage(Stage mainwindow) throws IOException {
        FXMLLoader loader = new FXMLLoader(GUI.class.getResource("loginView.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        controller.setMainwindow(mainwindow);
        mainwindow.setScene(new Scene(root, 800, 600));
        mainwindow.show();
    }
    public static void signupPage(Stage mainwindow) throws IOException {
        FXMLLoader loader = new FXMLLoader(GUI.class.getResource("signupView.fxml"));
        Parent root = loader.load();
        SignupController controller = loader.getController();
        controller.setMainwindow(mainwindow);
        mainwindow.setScene(new Scene(root, 800, 600));
        mainwindow.show();
    }
    public static void CustomerDashboard(Stage mainwindow, String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(GUI.class.getResource("customerDashboardView.fxml"));
        Parent root = loader.load();
        CustomerDashboardController controller = loader.getController();
        controller.setMainwindow(mainwindow);
        controller.setUserName(username);
        mainwindow.setScene(new Scene(root, 800, 600));
        mainwindow.show();
    }
    public static void AdminDashboard(Stage mainwindow) throws IOException {
        FXMLLoader loader = new FXMLLoader(GUI.class.getResource("adminDashboardView.fxml"));
        Parent root = loader.load();
        AdminDashboardController controller = loader.getController();
        controller.setMainwindow(mainwindow);
        mainwindow.setScene(new Scene(root, 800, 600));
        mainwindow.show();
    }
    public static void AdminLogin(Stage mainwindow) throws IOException {
        FXMLLoader loader = new FXMLLoader(GUI.class.getResource("adminLogin.fxml"));
        Parent root = loader.load();
        AdminLoginController controller = loader.getController();
        controller.setMainwindow(mainwindow);
        mainwindow.setScene(new Scene(root, 800, 600));
        mainwindow.show();
    }
    public static void AvailableCarsList(Stage mainwindow, String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(GUI.class.getResource("availableCarsListView.fxml"));
        Parent root = loader.load();
        AvailableCarsListController controller = loader.getController();
        controller.setMainwindow(mainwindow);
        controller.setUsername(username);
        mainwindow.setScene(new Scene(root, 800, 600));
        mainwindow.show();
        System.out.println("reached");
    }
    public static void RentedCarsList(Stage mainwindow, String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(GUI.class.getResource("rentedCarsView.fxml"));
        Parent root = loader.load();
        RentedCarsController controller = loader.getController();
        controller.setMainwindow(mainwindow);
        controller.setUsername(username);
        mainwindow.setScene(new Scene(root, 800, 600));
        mainwindow.show();
        System.out.println("reached");
    }
}