module com.benedict.minibank {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.commons;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;


    opens com.benedict.library to javafx.fxml;
    opens com.benedict.library.Controllers to javafx.fxml;
    exports com.benedict.library;
    exports com.benedict.library.Controllers;
    exports com.benedict.library.Models;
    exports com.benedict.library.Views;
    exports com.benedict.library.dao;
}