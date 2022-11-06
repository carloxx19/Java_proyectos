module com.mycompany.juego_monster_hunter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.juego_monster_hunter to javafx.fxml;
    exports com.mycompany.juego_monster_hunter;
}
