module com.example.schoolmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.example.schoolmanagementsystem to javafx.fxml;
    exports com.example.schoolmanagementsystem;
}