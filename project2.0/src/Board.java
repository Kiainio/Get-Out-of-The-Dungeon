import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.net.URL;

import static javafx.application.Application.launch;

public class Board extends Application {
    private Score score = new Score();
    private PlayerStage playerStage = new PlayerStage();
    public void start(Stage primaryStage) throws AWTException {
        URL url_background = this.getClass().getClassLoader().getResource("background.mp3");
        URL url_main = this.getClass().getClassLoader().getResource("main.mp3");
        URL url_move = this.getClass().getClassLoader().getResource("move.mp3");
        URL url_attack = this.getClass().getClassLoader().getResource("attack.mp3");
        URL url_attack_2 = this.getClass().getClassLoader().getResource("attack_2.mp3");
        URL url_attack_3 = this.getClass().getClassLoader().getResource("attack_3.mp3");
        URL url_hurt = this.getClass().getClassLoader().getResource("hurt.mp3");
        URL url_monster = this.getClass().getClassLoader().getResource("monster.mp3");
        URL url_victory = this.getClass().getClassLoader().getResource("victory.mp3");
        URL url_defeat = this.getClass().getClassLoader().getResource("defeat.mp3");
        URL url_click = this.getClass().getClassLoader().getResource("click.mp3");
        URL url_getInto = this.getClass().getClassLoader().getResource("getInto.mp3");

        assert url_background != null;
        assert url_main != null;
        Media media_background = new Media(url_background.toExternalForm());
        Media media_main = new Media(url_main.toExternalForm());
        MediaPlayer mediaPlayer_background = new MediaPlayer(media_background);
        MediaPlayer mediaPlayer_main = new MediaPlayer(media_main);
        mediaPlayer_main.setVolume(0.25);

        assert url_move != null;
        assert url_attack != null;
        assert url_attack_2 != null;
        assert url_attack_3 != null;
        assert url_hurt != null;
        assert url_monster != null;
        assert url_victory != null;
        assert url_defeat != null;
        assert url_click != null;
        assert url_getInto != null;
        AudioClip audioClip_move = new AudioClip(url_move.toExternalForm());
        AudioClip audioClip_attack = new AudioClip(url_attack.toExternalForm());
        AudioClip audioClip_attack_2 = new AudioClip(url_attack_2.toExternalForm());
        AudioClip audioClip_attack_3 = new AudioClip(url_attack_3.toExternalForm());
        AudioClip audioClip_hurt = new AudioClip(url_hurt.toExternalForm());
        AudioClip audioClip_monster = new AudioClip(url_monster.toExternalForm());
        AudioClip audioClip_victory = new AudioClip(url_victory.toExternalForm());
        AudioClip audioClip_defeat = new AudioClip(url_defeat.toExternalForm());
        AudioClip audioClip_click = new AudioClip(url_click.toExternalForm());
        AudioClip audioClip_getInto = new AudioClip(url_getInto.toExternalForm());
        audioClip_move.setVolume(7.5);

        GamePlay gamePlay = new GamePlay();
        Player player = new Player();
        Monster monster = new Monster();

        Group root = new Group();
        Scene gamePlaying = new Scene(root);
        primaryStage.setScene(gamePlaying);

        primaryStage.setTitle("GET OUT OF THE DUNGEON");
        primaryStage.getIcons().add(new Image("icon.jpg"));
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);
        primaryStage.setResizable(false);

        AnchorPane startPane = new AnchorPane();
        startPane.setPrefSize(600,500);
        startPane.setLayoutX(0);
        startPane.setLayoutY(0);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(140,500);
        anchorPane.setStyle("-fx-background-image: url('board_140_500.png');");
        anchorPane.setLayoutX(0);
        anchorPane.setLayoutY(0);

        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(360, 360);
        gridPane.setLayoutX(140);
        gridPane.setLayoutY(0);

        AnchorPane victoryPane = new AnchorPane();
        victoryPane.setPrefSize(360, 360);
        victoryPane.setLayoutX(140);
        victoryPane.setLayoutY(0);
        victoryPane.setStyle("-fx-background-image: url('board_360_360.png');");

        AnchorPane defeatPane = new AnchorPane();
        defeatPane.setPrefSize(360, 360);
        defeatPane.setLayoutX(140);
        defeatPane.setLayoutY(0);
        defeatPane.setStyle("-fx-background-image: url('board_360_360.png');");

        AnchorPane player_data_Pane = new AnchorPane();
        player_data_Pane.setPrefSize(100, 500);
        player_data_Pane.setStyle("-fx-background-image: url('board_100_500.png');" +
                "-fx-background-size: 100, 500");
        player_data_Pane.setLayoutX(500);
        player_data_Pane.setLayoutY(0);

        AnchorPane monster_data_Pane = new AnchorPane();
        monster_data_Pane.setPrefSize(360, 140);
        monster_data_Pane.setStyle("-fx-background-image: url('board_360_140.png');" +
                "-fx-background-size: 360, 140");
        monster_data_Pane.setLayoutX(140);
        monster_data_Pane.setLayoutY(360);

        Image image_board = new Image("board.png");
        ImageView imageView_board_140_500 = new ImageView(image_board);
        imageView_board_140_500.setFitWidth(140);
        imageView_board_140_500.setFitHeight(500);
        anchorPane.getChildren().add(imageView_board_140_500);

        gridPane.setVisible(false);
        victoryPane.setVisible(false);
        defeatPane.setVisible(false);
        anchorPane.setVisible(false);
        player_data_Pane.setVisible(false);
        monster_data_Pane.setVisible(false);
        root.getChildren().addAll(startPane, anchorPane, gridPane, victoryPane, defeatPane, player_data_Pane, monster_data_Pane);

        try {
            primaryStage.show();
            mediaPlayer_background.play();

            Image image_start = new Image("background.jpg");
            ImageView imageView_start = new ImageView(image_start);
            imageView_start.setFitWidth(600);
            imageView_start.setFitHeight(500);
            startPane.getChildren().add(imageView_start);

            Label title = new Label();
            title.setText("Get Out of The Dungeon");
            title.setTextFill(Paint.valueOf("#D3D3D3"));
            title.setFont(Font.font(40));
            title.setLayoutX(60);
            title.setLayoutY(100);
            startPane.getChildren().add(title);

            Label author = new Label();
            author.setText("@Kiainio");
            author.setTextFill(Paint.valueOf("#D3D3D3"));
            author.setFont(Font.font(20));
            author.setLayoutX(250);
            author.setLayoutY(250);
            startPane.getChildren().add(author);

            BackgroundFill bgf = new BackgroundFill(Paint.valueOf("#D3D3D3"), new CornerRadii(5), new Insets(0));
            Background bg = new Background(bgf);
            BackgroundFill bgf_pressed = new BackgroundFill(Paint.valueOf("#B5B5B5"), new CornerRadii(5), new Insets(0));
            Background bg_pressed = new Background(bgf_pressed);
            BorderStroke borderStroke = new BorderStroke(Paint.valueOf("#000000"), BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1));
            Border border = new Border(borderStroke);

            Button new_game = new Button();
            new_game.setText("New Game");
            new_game.setPrefWidth(100);
            new_game.setLayoutX(250);
            new_game.setLayoutY(350);
            new_game.setBackground(bg);
            new_game.setBorder(border);

            Button restart = new Button();
            restart.setBackground(bg);
            restart.setBorder(border);
            restart.setText("Restart");
            restart.setLayoutX(160);
            restart.setLayoutY(200);
            restart.setVisible(false);

            int n = 10;

            Scope scope = new Scope(n);
            scope.createScope(n);
            scope.loadMap(n);
            scope.showMap(n);
            scope.printMap(n, gridPane);
            scope.addMonster(n);
            scope.showMonster(n);
            scope.printMonster(n, gridPane);
            scope.showPlayer(player, gridPane);

            Label HP_player = new Label();
            HP_player.setText("HP:" + player.getHP());
            HP_player.setTextFill(Paint.valueOf("#D3D3D3"));
            HP_player.setLayoutX(15);
            HP_player.setLayoutY(120);

            Label Mana = new Label();
            Mana.setText("Mana:" + player.getMana());
            Mana.setTextFill(Paint.valueOf("#D3D3D3"));
            Mana.setLayoutX(15);
            Mana.setLayoutY(170);

            Label Attack_player = new Label();
            Attack_player.setText("Attack:" + player.getAttack());
            Attack_player.setTextFill(Paint.valueOf("#D3D3D3"));
            Attack_player.setLayoutX(15);
            Attack_player.setLayoutY(220);

            Label player_score = new Label();
            player_score.setText("Score:" + score.getScore());
            player_score.setTextFill(Paint.valueOf("#D3D3D3"));
            player_score.setLayoutX(15);
            player_score.setLayoutY(270);

            Label player_stage = new Label();
            player_stage.setText("Stage:" + playerStage.getStage());
            player_stage.setTextFill(Paint.valueOf("#D3D3D3"));
            player_stage.setLayoutX(15);
            player_stage.setLayoutY(100);

            Label FightTip = new Label();
            FightTip.setTextFill(Paint.valueOf("#D3D3D3"));
            FightTip.setText("A monster is around\nFight or not?");
            FightTip.setLayoutX(10);
            FightTip.setLayoutY(350);

            Button Yes = new Button();
            Yes.setText("YES");
            Yes.setPrefWidth(50);
            Yes.setLayoutX(45);
            Yes.setLayoutY(400);
            Yes.setBackground(bg);
            Yes.setBorder(border);

            Button No = new Button();
            No.setText("NO");
            No.setPrefWidth(50);
            No.setLayoutX(45);
            No.setLayoutY(425);
            No.setBackground(bg);
            No.setBorder(border);

            Yes.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    audioClip_click.play();
                    Yes.setBackground(bg_pressed);
                }
            });
            Yes.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Yes.setBackground(bg);
                    gamePlay.goon = true;
                    gamePlay.chooseToFight = true;
                    FightTip.setVisible(false);
                    Yes.setVisible(false);
                    No.setVisible(false);
                }
            });

            No.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    audioClip_click.play();
                    No.setBackground(bg_pressed);
                }
            });
            No.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    No.setBackground(bg);
                    monster.setHP(0);
                    monster.isDead = true;
                    monster.setAttack(0, playerStage);
                    gamePlay.goon = true;
                    gamePlay.chooseToFight = false;
                    FightTip.setVisible(false);
                    Yes.setVisible(false);
                    No.setVisible(false);
                }
            });

            Label Tip = new Label();
            Tip.setText("You can step\n on to lava\n and strikes,\nthough...\n(HP-5)");
            Tip.setTextFill(Paint.valueOf("#D3D3D3"));
            Tip.setLayoutX(5);
            Tip.setLayoutY(300);

            player_data_Pane.getChildren().addAll(player_stage, HP_player, Mana, Attack_player, player_score, Tip);

            Label HP_monster = new Label();
            HP_monster.setText("HP:0");
            HP_monster.setTextFill(Paint.valueOf("#D3D3D3"));
            HP_monster.setLayoutX(75);
            HP_monster.setLayoutY(45);

            Label Attack_monster = new Label();
            Attack_monster.setText("Attack:0");
            Attack_monster.setTextFill(Paint.valueOf("#D3D3D3"));
            Attack_monster.setLayoutX(150);
            Attack_monster.setLayoutY(45);

            monster_data_Pane.getChildren().addAll(HP_monster, Attack_monster);

            Label victory = new Label();
            victory.setLayoutX(50);
            victory.setLayoutY(150);

            Label defeat = new Label();
            defeat.setText("You died in the dark and dangerous dungeon...");
            defeat.setTextFill(Paint.valueOf("#D3D3D3"));
            defeat.setLayoutX(50);
            defeat.setLayoutY(150);

            Button continue_game = new Button();
            continue_game.setBackground(bg);
            continue_game.setBorder(border);
            continue_game.setText("Go deeper");
            continue_game.setLayoutX(150);
            continue_game.setLayoutY(250);
            continue_game.setVisible(false);

            Button play_again = new Button();
            play_again.setBackground(bg);
            play_again.setBorder(border);
            play_again.setText("Play again");
            play_again.setLayoutX(160);
            play_again.setLayoutY(200);
            play_again.setVisible(false);

            victoryPane.getChildren().addAll(victory, play_again, continue_game);
            defeatPane.getChildren().addAll(defeat, restart);

            Tooltip clickToContinue = new Tooltip("Click to continue");
            Tooltip clickToStart = new Tooltip("Click anywhere to start");
            Tooltip a = new Tooltip("Attack:" + player.getAttack());
            Tooltip b = new Tooltip("Attack:" + 2 * player.getAttack() + "\n" + "Mana:5");
            Tooltip c = new Tooltip("怪物将被眩晕一回合" + "\n" + "Mana:5");
            Tooltip d = new Tooltip("HP-15，离开战斗回到战斗前位置");

            Button start = new Button();
            start.setVisible(false);
            start.setText("Start");
            start.setFont(Font.font(40));
            start.setMinSize(600, 500);
            start.setTextFill(Paint.valueOf("#D3D3D3"));
            start.setStyle("-fx-background-image: url('background.jpg');" +
                    "-fx-background-size: 600, 500");
            start.setTooltip(clickToStart);
            start.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    audioClip_click.play();
                    audioClip_getInto.play();
                    start.setBackground(bg_pressed);
                }
            });
            start.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // TODO Auto-generated method stub
                    anchorPane.setVisible(true);
                    gridPane.setVisible(true);
                    player_data_Pane.setVisible(true);
                    monster_data_Pane.setVisible(true);
                    start.setVisible(false);
                    FightTip.setVisible(false);
                    Yes.setVisible(false);
                    No.setVisible(false);
                    mediaPlayer_background.stop();
                    mediaPlayer_main.play();
                }
            });
            root.getChildren().add(start);

            Button background = new Button();
            background.setVisible(false);
            startPane.getChildren().add(background);
            background.setText("YOU:Ahh...");
            background.setFont(Font.font(20));
            background.setMinSize(600, 500);
            background.setTextFill(Paint.valueOf("#D3D3D3"));
            background.setStyle("-fx-background-image: url('background.jpg');" +
                    "-fx-background-size: 600, 500");
            background.setTooltip(clickToContinue);
            background.setOnAction(new EventHandler<ActionEvent>() {
                int count_background = 0;
                @Override
                public void handle(ActionEvent actionEvent) {
                    audioClip_click.play();
                    switch (count_background) {
                        case 0:
                            background.setText("YOU:Where am I?");
                            break;
                        case 1:
                            background.setText("UNKNOWN SOUND:You finally wake up.");
                            break;
                        case 2:
                            background.setText("YOU:(looked around with fear)");
                            break;
                        case 3:
                            background.setText("YOU:Where are you? Who are you?");
                            break;
                        case 4:
                            background.setText("UNKNOWN SOUND:You dropped up from\n the hole up there.");
                            break;
                        case 5:
                            background.setText("YOU:(looked up only to see a drop\n of light high above)");
                            break;
                        case 6:
                            background.setText("UNKNOWN SOUND:No one had ever\n gotten out upwards...");
                            break;
                        case 7:
                            background.setText("YOU:What?(interrupted)");
                            break;
                        case 8:
                            background.setText("UNKNOWN SOUND:I knew it's hard\n to accept. But there's still one way out...");
                            break;
                        case 9:
                            background.setText("YOU:How? Tell me!");
                            break;
                        case 10:
                            background.setText("UNKNOWN SOUND:You are really\n impatience, young man...\nGet into the dungeon, \nand you can get out...\nbut the dungeon is really...");
                            break;
                        case 11:
                            background.setText("YOU:(got into the dungeon\n without listening to\n one more word)");
                            break;
                        case 12:
                            background.setText("UNKNOWN SOUND:...dangerous.\n Aww, what an impatience\n young man...");
                            break;
                        default:
                            background.setVisible(false);
                            start.setVisible(true);
                    }
                    count_background++;
                }
            });

            play_again.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    audioClip_click.play();
                    play_again.setBackground(bg_pressed);
                }
            });
            play_again.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    gamePlay.isEnd = false;
                    scope.createScope(n);
                    scope.loadMap(n);
                    scope.showMap(n);
                    scope.printMap(n, gridPane);
                    scope.addMonster(n);
                    scope.showMonster(n);
                    scope.printMonster(n, gridPane);
                    player.setPosition(new Posi(0, 0));
                    player.setHP(500);
                    player.setMana(100);
                    score.setScore(0);
                    playerStage.setStage(1);
                    scope.showPlayer(player, gridPane);
                    player_data_Pane.getChildren().remove(player_score);
                    victoryPane.setVisible(false);
                    player_score.setText("Score:" + score.getScore());
                    gridPane.setVisible(true);
                    player_data_Pane.getChildren().add(player_score);
                    player_data_Pane.getChildren().remove(player_stage);
                    player_stage.setText("Stage:" + playerStage.getStage());
                    player_stage.setTextFill(Paint.valueOf("#D3D3D3"));
                    player_data_Pane.getChildren().add(player_stage);
                    gamePlay.updateData(player, monster, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster);
                }
            });

            restart.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    audioClip_click.play();
                    restart.setBackground(bg_pressed);
                }
            });
            restart.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    gamePlay.isEnd = false;
                    monster.isDead = true;
                    monster.setHP(0);
                    scope.createScope(n);
                    scope.loadMap(n);
                    scope.showMap(n);
                    scope.printMap(n, gridPane);
                    scope.addMonster(n);
                    scope.showMonster(n);
                    scope.printMonster(n, gridPane);
                    player.setPosition(new Posi(0, 0));
                    player.setHP(500);
                    player.setMana(100);
                    score.setScore(0);
                    playerStage.setStage(1);
                    scope.showPlayer(player, gridPane);
                    player_data_Pane.getChildren().remove(player_score);
                    defeatPane.setVisible(false);
                    player_score.setText("Score:" + score.getScore());
                    gridPane.setVisible(true);
                    player_data_Pane.getChildren().add(player_score);
                    player_data_Pane.getChildren().remove(player_stage);
                    player_stage.setText("Stage:" + playerStage.getStage());
                    player_stage.setTextFill(Paint.valueOf("#D3D3D3"));
                    player_data_Pane.getChildren().add(player_stage);
                    gamePlay.updateData(player, monster, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster);
                }
            });

            Button button_a = new Button();
            button_a.setText("普通攻击");
            initial_attack_button(audioClip_click, bg, bg_pressed, border, a, button_a);
            button_a.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // TODO Auto-generated method stub
                    button_a.setBackground(bg);
                    if(gamePlay.chooseToFight && !monster.isDead) {
                        audioClip_attack.play();
                        gamePlay.fightOption = 1;
                        attack_move(player, monster, scope, gamePlay, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster, audioClip_monster, player_score, gridPane, defeatPane, mediaPlayer_main, audioClip_defeat, Tip, restart);
                    }
                    else {
                        player_data_Pane.getChildren().remove(Tip);
                        Tip.setText("No monster...");
                        Tip.setTextFill(Paint.valueOf("#D3D3D3"));
                        Tip.setLayoutX(5);
                        Tip.setLayoutY(300);
                        player_data_Pane.getChildren().add(Tip);
                    }
                }
            });

            Button button_b = new Button();
            button_b.setText("重击");
            initial_attack_button(audioClip_click, bg, bg_pressed, border, b, button_b);
            button_b.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    button_b.setBackground(bg);
                    // TODO Auto-generated method stub
                    if(gamePlay.chooseToFight && !monster.isDead) {
                        audioClip_attack_2.play();
                        gamePlay.fightOption = 2;
                        attack_move(player, monster, scope, gamePlay, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster, audioClip_monster, player_score, gridPane, defeatPane, mediaPlayer_main, audioClip_defeat, Tip, restart);
                    }
                    else {
                        player_data_Pane.getChildren().remove(Tip);
                        Tip.setText("No monster...");
                        Tip.setTextFill(Paint.valueOf("#D3D3D3"));
                        Tip.setLayoutX(5);
                        Tip.setLayoutY(300);
                        player_data_Pane.getChildren().add(Tip);
                    }
                }
            });

            Button button_c = new Button();
            button_c.setText("盾击");
            initial_attack_button(audioClip_click, bg, bg_pressed, border, c, button_c);
            button_c.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // TODO Auto-generated method stub
                    button_c.setBackground(bg);
                    if(gamePlay.chooseToFight && !monster.isDead) {
                        audioClip_attack_3.play();
                        gamePlay.fightOption = 3;
                        attack_move(player, monster, scope, gamePlay, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster, audioClip_monster, player_score, gridPane, defeatPane, mediaPlayer_main, audioClip_defeat, Tip, restart);
                    }
                    else {
                        player_data_Pane.getChildren().remove(Tip);
                        Tip.setText("No monster...");
                        Tip.setTextFill(Paint.valueOf("#D3D3D3"));
                        Tip.setLayoutX(5);
                        Tip.setLayoutY(300);
                        player_data_Pane.getChildren().add(Tip);
                    }
                }
            });

            Button button_d = new Button();
            button_d.setText("逃脱");
            initial_attack_button(audioClip_click, bg, bg_pressed, border, d, button_d);
            button_d.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    button_d.setBackground(bg);
                    // TODO Auto-generated method stub
                    if(gamePlay.chooseToFight && !monster.isDead) {
                        audioClip_hurt.play();
                        gamePlay.fightOption = 4;
                        if (!monster.isDead & player.getHP() > 0) {
                            try {
                                gamePlay.fight(gamePlay.fightOption, player, monster, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster, Tip);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (player.getHP() <= 0) {//defeat
                            gridPane.setVisible(false);
                            defeatPane.setVisible(true);
                            gamePlay.isEnd = true;
                            mediaPlayer_main.stop();
                            audioClip_defeat.play();
                        }
                        monster.setHP(0);
                    }
                    else {
                        player_data_Pane.getChildren().remove(Tip);
                        Tip.setText("No monster...");
                        Tip.setTextFill(Paint.valueOf("#D3D3D3"));
                        Tip.setLayoutX(5);
                        Tip.setLayoutY(300);
                        player_data_Pane.getChildren().add(Tip);
                    }
                }
            });

            anchorPane.getChildren().addAll(button_a, button_b, button_c, button_d, FightTip, Yes, No);

            AnchorPane.setLeftAnchor(button_a, 30.0);
            AnchorPane.setTopAnchor(button_a, 80.0);
            AnchorPane.setRightAnchor(button_a, 30.0);
            AnchorPane.setLeftAnchor(button_b, 30.0);
            AnchorPane.setTopAnchor(button_b, 120.0);
            AnchorPane.setRightAnchor(button_b, 30.0);
            AnchorPane.setLeftAnchor(button_c, 30.0);
            AnchorPane.setTopAnchor(button_c, 160.0);
            AnchorPane.setRightAnchor(button_c, 30.0);
            AnchorPane.setLeftAnchor(button_d, 30.0);
            AnchorPane.setTopAnchor(button_d, 200.0);
            AnchorPane.setRightAnchor(button_d, 30.0);

            Button button_up = new Button();
            button_up.setText("UP");
            initial_move_button(bg, bg_pressed, border, button_up);
            button_up.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    button_up.setBackground(bg);
                    if (monster.isDead && !gamePlay.isEnd) {
                        audioClip_move.play();
                        scope.changePlayerPosition(player, gridPane, 1, gamePlay, monster, FightTip, Yes, No, playerStage);
                        move(gamePlay, player, monster, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster, n, gridPane, victory, victoryPane, mediaPlayer_main, audioClip_victory, playerStage, play_again, continue_game, defeatPane, restart, audioClip_defeat);
                    }
                }
            });

            Button button_down = new Button();
            button_down.setText("DOWN");
            initial_move_button(bg, bg_pressed, border, button_down);
            button_down.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    button_down.setBackground(bg);
                    if (monster.isDead && !gamePlay.isEnd) {
                        audioClip_move.play();
                        scope.changePlayerPosition(player, gridPane, 2, gamePlay, monster, FightTip, Yes, No, playerStage);
                        move(gamePlay, player, monster, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster, n, gridPane, victory, victoryPane, mediaPlayer_main, audioClip_victory, playerStage, play_again, continue_game, defeatPane, restart, audioClip_defeat);
                    }
                }
            });

            Button button_left = new Button();
            button_left.setText("LEFT");
            initial_move_button(bg, bg_pressed, border, button_left);
            button_left.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    button_left.setBackground(bg);
                    if (monster.isDead && !gamePlay.isEnd) {
                        audioClip_move.play();
                        scope.changePlayerPosition(player, gridPane, 3, gamePlay, monster, FightTip, Yes, No, playerStage);
                        move(gamePlay, player, monster, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster, n, gridPane, victory, victoryPane, mediaPlayer_main, audioClip_victory, playerStage, play_again, continue_game, defeatPane, restart, audioClip_defeat);
                    }
                }
            });

            Button button_right = new Button();
            button_right.setText("RIGHT");
            initial_move_button(bg, bg_pressed, border, button_right);
            button_right.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    button_right.setBackground(bg);
                    if (monster.isDead && !gamePlay.isEnd) {
                        audioClip_move.play();
                        scope.changePlayerPosition(player, gridPane, 4, gamePlay, monster, FightTip, Yes, No, playerStage);
                        move(gamePlay, player, monster, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster, n, gridPane, victory, victoryPane, mediaPlayer_main, audioClip_victory, playerStage, play_again, continue_game, defeatPane, restart, audioClip_defeat);
                    }
                }
            });

            anchorPane.getChildren().addAll(button_up, button_down, button_left, button_right);
            AnchorPane.setLeftAnchor(button_up, 40.0);
            AnchorPane.setTopAnchor(button_up, 250.0);
            AnchorPane.setLeftAnchor(button_down, 40.0);
            AnchorPane.setTopAnchor(button_down, 300.0);
            AnchorPane.setLeftAnchor(button_left, 10.0);
            AnchorPane.setTopAnchor(button_left, 275.0);
            AnchorPane.setLeftAnchor(button_right, 70.0);
            AnchorPane.setTopAnchor(button_right, 275.0);

            continue_game.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    continue_game.setBackground(bg_pressed);
                }
            });
            continue_game.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    gamePlay.isEnd = false;
                    continue_game.setBackground(bg);
                    audioClip_victory.stop();
                    audioClip_getInto.play();
                    audioClip_click.play();
                    mediaPlayer_main.play();
                    if(playerStage.getStage() <= 5) {
                        scope.createScope(n);
                        scope.loadMap(n);
                        scope.showMap(n);
                        scope.printMap(n, gridPane);
                        scope.addMonster(n);
                        scope.showMonster(n);
                        scope.printMonster(n, gridPane);
                        player.setPosition(new Posi(0, 0));
                        scope.showPlayer(player, gridPane);
                        victoryPane.setVisible(false);
                        gridPane.setVisible(true);
                        player_data_Pane.getChildren().remove(player_stage);
                        player_stage.setText("Stage:" + playerStage.getStage());
                        player_stage.setTextFill(Paint.valueOf("#D3D3D3"));
                        player_data_Pane.getChildren().add(player_stage);
                        gamePlay.updateData(player, monster, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster);
                    }
                }
            });

            new_game.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    audioClip_click.play();
                    new_game.setBackground(bg_pressed);
                }
            });
            new_game.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    new_game.setBackground(bg);
                    background.setVisible(true);
                    new_game.setVisible(false);
                }
            });

            startPane.getChildren().add(new_game);
            }catch(Exception e){
                e.printStackTrace();
            }
    }

    private void attack_move(Player player, Monster monster, Scope scope, GamePlay gamePlay, AnchorPane player_data_Pane, AnchorPane monster_data_Pane, Label HP_player, Label mana, Label attack_player, Label HP_monster, Label attack_monster, AudioClip audioClip_monster, Label player_score, GridPane gridPane, AnchorPane defeatPane, MediaPlayer mediaPlayer_main, AudioClip audioClip_defeat, Label tip, Button restart) {
        Posi temp_posi = player.getPosition();
        int temp_row = temp_posi.getRow();
        int temp_line = temp_posi.getLine();
        Posi monster_posi = monster.getPosition();
        int monster_row = monster_posi.getRow();
        int monster_line = monster_posi.getLine();
        if (temp_line - monster_line == 1) {
            Timeline timeline = new Timeline();

            KeyValue keyValue_player_1 = new KeyValue(scope.view_player_stand_forward.translateXProperty(), 0);
            KeyFrame keyFrame_player_1 = new KeyFrame(Duration.millis(0), "keyFrame_player_1", new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                }
            }, keyValue_player_1);

            KeyValue keyValue_player_2 = new KeyValue(scope.view_player_stand_forward.translateXProperty(), -30);
            KeyFrame keyFrame_player_2 = new KeyFrame(Duration.millis(500), "keyFrame_player_2", new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                }
            }, keyValue_player_2);

            KeyValue keyValue_player_3 = new KeyValue(scope.view_player_stand_forward.translateXProperty(), 0);
            KeyFrame keyFrame_player_3 = new KeyFrame(Duration.millis(1000), "keyFrame_player_3", new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                }
            }, keyValue_player_3);

            timeline.getKeyFrames().addAll(keyFrame_player_1, keyFrame_player_2, keyFrame_player_3);

            timeline.play();
        }

        if (temp_row - monster_row == 1) {
            Timeline timeline = new Timeline();

            KeyValue keyValue_player_1 = new KeyValue(scope.view_player_stand_forward.translateYProperty(), 0);
            KeyFrame keyFrame_player_1 = new KeyFrame(Duration.millis(0), "keyFrame_player_1", new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                }
            }, keyValue_player_1);

            KeyValue keyValue_player_2 = new KeyValue(scope.view_player_stand_forward.translateYProperty(), -30);
            KeyFrame keyFrame_player_2 = new KeyFrame(Duration.millis(500), "keyFrame_player_2", new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                }
            }, keyValue_player_2);

            KeyValue keyValue_player_3 = new KeyValue(scope.view_player_stand_forward.translateYProperty(), 0);
            KeyFrame keyFrame_player_3 = new KeyFrame(Duration.millis(1000), "keyFrame_player_3", new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                }
            }, keyValue_player_3);

            timeline.getKeyFrames().addAll(keyFrame_player_1, keyFrame_player_2, keyFrame_player_3);

            timeline.play();
        }

        if (monster_line - temp_line == 1) {
            Timeline timeline = new Timeline();

            KeyValue keyValue_player_1 = new KeyValue(scope.view_player_stand_forward.translateXProperty(), 0);
            KeyFrame keyFrame_player_1 = new KeyFrame(Duration.millis(0), "keyFrame_player_1", new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                }
            }, keyValue_player_1);

            KeyValue keyValue_player_2 = new KeyValue(scope.view_player_stand_forward.translateXProperty(), 30);
            KeyFrame keyFrame_player_2 = new KeyFrame(Duration.millis(500), "keyFrame_player_2", new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                }
            }, keyValue_player_2);

            KeyValue keyValue_player_3 = new KeyValue(scope.view_player_stand_forward.translateXProperty(), 0);
            KeyFrame keyFrame_player_3 = new KeyFrame(Duration.millis(1000), "keyFrame_player_3", new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                }
            }, keyValue_player_3);

            timeline.getKeyFrames().addAll(keyFrame_player_1, keyFrame_player_2, keyFrame_player_3);

            timeline.play();
        }

        if (monster_row - temp_row == 1) {
            Timeline timeline = new Timeline();

            KeyValue keyValue_player_1 = new KeyValue(scope.view_player_stand_forward.translateYProperty(), 0);
            KeyFrame keyFrame_player_1 = new KeyFrame(Duration.millis(0), "keyFrame_player_1", new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                }
            }, keyValue_player_1);

            KeyValue keyValue_player_2 = new KeyValue(scope.view_player_stand_forward.translateYProperty(), 30);
            KeyFrame keyFrame_player_2 = new KeyFrame(Duration.millis(500), "keyFrame_player_2", new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                }
            }, keyValue_player_2);

            KeyValue keyValue_player_3 = new KeyValue(scope.view_player_stand_forward.translateYProperty(), 0);
            KeyFrame keyFrame_player_3 = new KeyFrame(Duration.millis(1000), "keyFrame_player_3", new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                }
            }, keyValue_player_3);

            timeline.getKeyFrames().addAll(keyFrame_player_1, keyFrame_player_2, keyFrame_player_3);

            timeline.play();
        }

        fight(monster, player, gamePlay, player_data_Pane, monster_data_Pane, HP_player, mana, attack_player, HP_monster, attack_monster, audioClip_monster, player_score, scope, gridPane, defeatPane, mediaPlayer_main, audioClip_defeat, tip, restart);
    }

    private void initial_move_button(Background bg, Background bg_pressed, Border border, Button button_up) {
        button_up.setPrefWidth(60);
        button_up.setBackground(bg);
        button_up.setBorder(border);
        button_up.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button_up.setBackground(bg_pressed);
            }
        });
    }

    private void initial_attack_button(AudioClip audioClip_click, Background bg, Background bg_pressed, Border border, Tooltip c, Button button_c) {
        button_c.setPrefWidth(80.0);
        button_c.setBackground(bg);
        button_c.setBorder(border);
        button_c.setTooltip(c);
        button_c.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                audioClip_click.play();
                button_c.setBackground(bg_pressed);
            }
        });
    }

    private void move(GamePlay gamePlay, Player player, Monster monster, AnchorPane player_data_Pane, AnchorPane monster_data_Pane, Label HP_player, Label mana, Label attack_player, Label HP_monster, Label attack_monster, int n, GridPane gridPane, Label victory, AnchorPane victoryPane, MediaPlayer mediaPlayer_main, AudioClip audioClip_victory, PlayerStage playerStage, Button play_again, Button continue_play, AnchorPane defeatPane, Button restart, AudioClip audioClip_defeat) {
        gamePlay.updateData(player, monster, player_data_Pane, monster_data_Pane, HP_player, mana, attack_player, HP_monster, attack_monster);
        if (player.getPosition().getRow() == n - 1 && player.getPosition().getLine() == n - 1) {//victory
            gridPane.setVisible(false);
            gamePlay.isEnd = true;
            if(playerStage.getStage() == 3) {
                continue_play.setVisible(false);
                victory.setText("You got out of the dungeon successfully!\nYour score:" + score.getScore());
                victory.setTextFill(Paint.valueOf("#D3D3D3"));
                play_again.setVisible(true);
            }
            else {
                victory.setText("Stage clear! You need to go deeper...\nit will be more dangerous\n(Monster Attack Up)");
                victory.setTextFill(Paint.valueOf("#D3D3D3"));
                playerStage.setStage(playerStage.getStage() + 1);
                continue_play.setVisible(true);
            }
            victoryPane.setVisible(true);
            mediaPlayer_main.stop();
            audioClip_victory.play();
        }
        if(player.getHP() < 0) {
            gridPane.setVisible(false);
            defeatPane.setVisible(true);
            restart.setVisible(true);
            gamePlay.isEnd = true;
            mediaPlayer_main.stop();
            audioClip_defeat.play();
        }
        gamePlay.updateData(player, monster, player_data_Pane, monster_data_Pane, HP_player, mana, attack_player, HP_monster, attack_monster);
    }

    private void fight(Monster monster, Player player, GamePlay gamePlay, AnchorPane player_data_Pane, AnchorPane monster_data_Pane, Label HP_player, Label mana, Label attack_player, Label HP_monster, Label attack_monster, AudioClip audioClip_monster, Label player_score, Scope scope, GridPane gridPane, AnchorPane defeatPane, MediaPlayer mediaPlayer_main, AudioClip audioClip_defeat, Label Tip, Button restart) {
        if (!monster.isDead && player.getHP() > 0) {
            try {
                gamePlay.fight(gamePlay.fightOption, player, monster, player_data_Pane, monster_data_Pane, HP_player, mana, attack_player, HP_monster, attack_monster, Tip);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (monster.isDead) {//monster is killed
            audioClip_monster.play();
            score.setScore(score.getScore() + monster.getInitialHP());
            player_data_Pane.getChildren().remove(player_score);
            player_score.setText("Score:" + score.getScore());
            player_score.setTextFill(Paint.valueOf("#D3D3D3"));
            player_data_Pane.getChildren().add(player_score);
            scope.removeMonster(monster, gridPane);
            scope.scope[1][monster.getPosition().getRow()][monster.getPosition().getLine()] = 0;
        }
        if (player.getHP() <= 0) {//defeat
            gridPane.setVisible(false);
            defeatPane.setVisible(true);
            restart.setVisible(true);
            gamePlay.isEnd = true;
            mediaPlayer_main.stop();
            audioClip_defeat.play();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}