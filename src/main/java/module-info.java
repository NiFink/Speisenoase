module SE2StartupProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.apache.logging.log4j;

    opens mainpackage;
    opens mainpackage.itempackage;
    opens mainpackage.ShoppingCart;
}
