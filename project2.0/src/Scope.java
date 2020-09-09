import com.sun.management.GcInfo;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import javax.sound.sampled.Line;
import javax.swing.plaf.basic.BasicTextAreaUI;
import java.awt.*;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.Random;

class Scope {

    int [][][]scope;
    private Random random = new Random(new Date().getTime());

    private Image player_stand_forward = new Image("player_stand_forward.png", 30, 30, true, true);
    ImageView view_player_stand_forward = new ImageView(player_stand_forward);
    private ImageView [][] bats = new ImageView[10][10];
    private ImageView [][] skulls = new ImageView[10][10];
    private ImageView [][] goblins = new ImageView[10][10];

    Scope(int n) {
        scope = new int [2][n][n];
    }

    void createScope(int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                scope[0][i][j] = 0;
                scope[1][i][j] = 0;
            }
        }
    }

    void loadMap(int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                scope[0][i][j] = random.nextInt(100);
            }
        }
    }

    void showMap(int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(scope[0][i][j] < 70)//地
                    scope[0][i][j] = 0;
                else if(scope[0][i][j] < 80)//岩浆
                    scope[0][i][j] = 1;
                else if(scope[0][i][j] < 90)//地刺
                    scope[0][i][j] = 2;
                else//墙
                    scope[0][i][j] = 3;
            }
        }
        scope[0][0][0] = 0;
        scope[0][n - 1][n - 1] = 0;
    }

    void addMonster(int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(scope[0][i][j] == 0) {
                    scope[1][i][j] = random.nextInt(100);
                }
            }
        }
    }

    void showMonster(int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(scope[0][i][j] != 0)
                    continue;
                if(scope[1][i][j] < 70)//无怪
                    scope[1][i][j] = 0;
                else if(scope[1][i][j] < 80)//蝙蝠
                    scope[1][i][j] = 1;
                else if(scope[1][i][j] < 90)//骷髅
                    scope[1][i][j] = 2;
                else//哥布林
                    scope[1][i][j] = 3;
            }
        }
        scope[1][0][0] = 0;
        scope[1][n - 1][n - 1] = 0;
    }

    void printMap(int n, GridPane gridPane) {
        Image edge = new Image("edge.png");
        Image ground = new Image("ground.png");
        Image lava = new Image("lava.png");
        Image strike = new Image("strike.png");
        Image wall = new Image("wall.png");
        Image gate = new Image("gate.png");
        Image gate_out = new Image("gate_out.png");

        for(int i = 0; i < n + 2; i++) {//上边
            ImageView view_edge = new ImageView(edge);
            gridPane.add(view_edge, i, 0);
        }
        for(int i = 0; i < n + 2; i++) {//下边
            ImageView view_edge = new ImageView(edge);
            gridPane.add(view_edge, i, n + 1);
        }
        for(int i = 1; i < n + 1; i++) {//左边
            ImageView view_edge = new ImageView(edge);
            gridPane.add(view_edge, 0, i);
        }
        for(int i = 1; i < n + 1; i++) {//右边
            ImageView view_edge = new ImageView(edge);
            gridPane.add(view_edge, n + 1, i);
        }
        for(int i = 0; i < n; i++) {//内部
            for(int j = 0; j < n; j++) {
                switch(scope[0][i][j]) {
                    case 0:
                        ImageView view_ground = new ImageView(ground);
                        gridPane.add(view_ground, j + 1, i + 1);
                        break;
                    case 1:
                        ImageView view_lava = new ImageView(lava);
                        gridPane.add(view_lava, j + 1, i + 1);
                        break;
                    case 2:
                        ImageView view_strike = new ImageView(strike);
                        gridPane.add(view_strike, j + 1, i + 1);
                        break;
                    case 3:
                        ImageView view_wall = new ImageView(wall);
                        gridPane.add(view_wall, j + 1, i + 1);
                        break;
                }
            }
        }

        ImageView view_gate = new ImageView(gate);
        gridPane.add(view_gate, 1, 1);
        ImageView view_gate_out = new ImageView(gate_out);
        gridPane.add(view_gate_out, n, n);
    }

    void printMonster(int n, GridPane gridPane) {
        for(int i = 0; i < n; i++) {//内部
            for(int j = 0; j < n; j++) {
                if(scope[0][i][j] == 0 && scope[1][i][j] != 0) {
                    switch (scope[1][i][j]) {
                        case 1:
                            bats[i][j] = new ImageView(new Image("bat.png"));
                            gridPane.add(bats[i][j], j + 1, i + 1);
                            break;
                        case 2:
                            skulls[i][j] = new ImageView(new Image("skull.png"));
                            gridPane.add(skulls[i][j], j + 1, i + 1);
                            break;
                        case 3:
                            goblins[i][j] = new ImageView(new Image("goblin.png"));
                            gridPane.add(goblins[i][j], j + 1, i + 1);
                            break;
                    }
                }
            }
        }
    }

    void showPlayer(Player player, GridPane gridPane) {
        Posi posi = player.getPosition();
        int i = posi.getRow();
        int j = posi.getLine();
        if(!gridPane.getChildren().contains(view_player_stand_forward))
            gridPane.add(view_player_stand_forward, j + 1, i + 1);
        else {
            gridPane.getChildren().remove(view_player_stand_forward);
            gridPane.add(view_player_stand_forward, j + 1, i + 1);
        }
    }

    private void move(Player player, int operation, GamePlay gamePlay, Monster monster, Label FightTip, Button Yes, Button No, PlayerStage playerStage) {
        int n = 10;

        URL url_hurt = this.getClass().getClassLoader().getResource("hurt.mp3");
        assert url_hurt != null;
        AudioClip audioClip_hurt = new AudioClip(url_hurt.toExternalForm());

        Posi posi = player.getPosition();
        int i = posi.getRow();
        int j = posi.getLine();
        int hp = player.getHP();
        switch(operation) {
            case 1:
                if(i - 1 >= 0) {
                    switch (scope[0][i - 1][j]) {
                        case 0:
                            if(scope[1][i - 1][j] != 0) {
                                gamePlay.monster_set(monster, scope[1][i - 1][j], new Posi(i - 1, j), playerStage);
                                FightTip.setVisible(true);
                                Yes.setVisible(true);
                                No.setVisible(true);
                                break;
                            }
                            else {
                                posi.setRow(i - 1);
                                break;
                            }
                        case 1: case 2:
                            posi.setRow(i - 1);
                            player.setHP(hp - 5);
                            audioClip_hurt.play();
                            break;
                        case 3:
                            break;
                    }
                }
                break;
            case 2:
                if(i + 1 < n) {
                    switch (scope[0][i + 1][j]) {
                        case 0:
                            if(scope[1][i + 1][j] != 0) {
                                gamePlay.monster_set(monster, scope[1][i + 1][j], new Posi(i + 1, j), playerStage);
                                FightTip.setVisible(true);
                                Yes.setVisible(true);
                                No.setVisible(true);
                                break;
                            }
                            else {
                                posi.setRow(i + 1);
                                break;
                            }
                        case 1: case 2:
                            posi.setRow(i + 1);
                            player.setHP(hp - 5);
                            audioClip_hurt.play();
                            break;
                        case 3:
                            break;
                    }
                }
                break;
            case 3:
                if(j - 1 >= 0) {
                    switch (scope[0][i][j - 1]) {
                        case 0:
                            if(scope[1][i][j - 1] != 0) {
                                gamePlay.monster_set(monster, scope[1][i][j - 1], new Posi(i, j - 1), playerStage);
                                FightTip.setVisible(true);
                                Yes.setVisible(true);
                                No.setVisible(true);
                                break;
                            }
                            else {
                                posi.setLine(j - 1);
                                break;
                            }
                        case 1: case 2:
                            posi.setLine(j - 1);
                            player.setHP(hp - 5);
                            audioClip_hurt.play();
                            break;
                        case 3:
                            break;
                    }
                }
                break;
           case 4:
                if(j + 1 < n) {
                    switch (scope[0][i][j + 1]) {
                        case 0:
                            if(scope[1][i][j + 1] != 0) {
                                gamePlay.monster_set(monster, scope[1][i][j + 1], new Posi(i, j + 1), playerStage);
                                FightTip.setVisible(true);
                                Yes.setVisible(true);
                                No.setVisible(true);
                                break;
                            }
                            else {
                                posi.setLine(j + 1);
                                break;
                            }
                        case 1: case 2:
                            posi.setLine(j + 1);
                            player.setHP(hp - 5);
                            audioClip_hurt.play();
                            break;
                        case 3:
                            break;
                    }
                }
                break;
        }
        player.setPosition(posi);
    }

    void changePlayerPosition(Player player, GridPane gridPane, int operation, GamePlay gamePlay, Monster monster, Label FightTip, Button Yes, Button No, PlayerStage playerStage) {
        removePlayer(gridPane);
        move(player, operation, gamePlay, monster, FightTip, Yes, No, playerStage);
        showPlayer(player, gridPane);
    }

    private void removePlayer(GridPane gridPane) {
        gridPane.getChildren().remove(view_player_stand_forward);
    }

    void removeMonster(Monster monster, GridPane gridPane) {
        Posi posi = monster.getPosition();
        int row = posi.getRow();
        int line = posi.getLine();
        switch (scope[1][row][line]) {
            case 1:
                gridPane.getChildren().remove(bats[row][line]);
                break;
            case 2:
                gridPane.getChildren().remove(skulls[row][line]);
                break;
            case 3:
                gridPane.getChildren().remove(goblins[row][line]);
                break;
        }
    }
}
