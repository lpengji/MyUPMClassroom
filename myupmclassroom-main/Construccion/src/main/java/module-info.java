module iwsit2106.myupmclassroom {
    requires javafx.controls;
    requires javafx.fxml;
    requires externals;
    requires junit;

    opens iwsit2106.myupmclassroom to javafx.fxml;

    exports iwsit2106.myupmclassroom;
    exports iwsit2106.myupmclassroom.controller;

    opens iwsit2106.myupmclassroom.controller to javafx.fxml;

    exports iwsit2106.myupmclassroom.model;

    opens iwsit2106.myupmclassroom.model to javafx.fxml;
}