module com.benedict.minibank {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.commons;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;


    opens com.benedict.minibank to javafx.fxml;
    exports com.benedict.minibank;
    exports com.benedict.minibank.Controllers;
    exports com.benedict.minibank.Models;
    exports com.benedict.minibank.Views;
}