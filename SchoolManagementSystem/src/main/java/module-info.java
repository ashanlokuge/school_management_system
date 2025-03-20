module com.example.schoolmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens com.example.schoolmanagementsystem to javafx.fxml;
    opens com.example.schoolmanagementsystem.Model to javafx.base;

    exports com.example.schoolmanagementsystem;
    exports com.example.schoolmanagementsystem.Model;

}