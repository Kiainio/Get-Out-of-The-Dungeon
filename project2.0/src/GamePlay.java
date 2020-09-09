import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

class GamePlay {
    int fightOption;
    boolean chooseToFight;
    boolean goon;
    boolean isEnd;

    private void player_fight(int fightOption, Player player, Monster monster, AnchorPane player_data_Pane, AnchorPane monster_data_Pane, Label HP_player, Label Mana, Label Attack_player, Label HP_monster, Label Attack_monster, Label Tip) {
        switch (fightOption) {
            case 1://普通攻击
                monster.beenAttacked(player.getAttack());
                break;
            case 2://重击
                if(player.getMana() > 0) {
                    monster.beenAttacked(player.getAttack() * 2);
                    player.setMana(Math.max(player.getMana() - 5, 0));
                }
                else {
                    player_data_Pane.getChildren().remove(Tip);
                    Tip.setText("You have no\nmore mana to\ndo this...");
                    player_data_Pane.getChildren().add(Tip);
                }
                break;
            case 3://盾击
                if(player.getMana() > 0) {
                    monster.getDizzy();
                    player.setMana(Math.max(player.getMana() - 5, 0));
                }
                else {
                    player_data_Pane.getChildren().remove(Tip);
                    Tip.setText("You have no\nmore mana to\ndo this...");
                    player_data_Pane.getChildren().add(Tip);
                }
                break;
            case 4://逃走
                player.setHP(player.getHP() - 15);
                monster.isDead = true;
                break;
            }
        if(monster.getHP() <= 0)
            monster.isDead = true;

        updateData(player, monster, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster);
    }

    void updateData(Player player, Monster monster, AnchorPane player_data_Pane, AnchorPane monster_data_Pane, Label HP_player, Label Mana, Label Attack_player, Label HP_monster, Label Attack_monster) {
        player_data_Pane.getChildren().removeAll(HP_player, Mana, Attack_player);
        monster_data_Pane.getChildren().removeAll(HP_monster, Attack_monster);

        HP_player.setText("HP:" + player.getHP());

        Mana.setText("Mana:" + player.getMana());

        Attack_player.setText("Attack:" + player.getAttack());

        player_data_Pane.getChildren().addAll(HP_player, Mana, Attack_player);

        HP_monster.setText("HP:" + monster.getHP());

        Attack_monster.setText("Attack:" + monster.getAttack());
        Attack_monster.setLayoutX(150);

        monster_data_Pane.getChildren().addAll(HP_monster, Attack_monster);
    }

    private void monster_fight(Player player, Monster monster, AnchorPane player_data_Pane, AnchorPane monster_data_Pane, Label HP_player, Label Mana, Label Attack_player, Label HP_monster, Label Attack_monster) {
        if(!monster.isDizzy) {
            player.setHP(Math.max(player.getHP() - monster.getAttack(), 0));
        }

        updateData(player, monster, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster);
    }

    boolean startFight(int id){
        return id != 0;
    }

    void monster_set(Monster monster, int id, Posi posi, PlayerStage playerStage) {
        monster.setName(id);
        monster.setInitialHP(id);
        monster.setAttack(id, playerStage);
        monster.setPosition(posi);
        monster.isDead = false;
    }

    void fight(int fightOption, Player player, Monster monster, AnchorPane player_data_Pane, AnchorPane monster_data_Pane, Label HP_player, Label Mana, Label Attack_player, Label HP_monster, Label Attack_monster, Label Tip) throws InterruptedException {
        player_fight(fightOption, player, monster, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster, Tip);
        monster_fight(player, monster, player_data_Pane, monster_data_Pane, HP_player, Mana, Attack_player, HP_monster, Attack_monster);
        if(monster.isDizzy)
            monster.isDizzy = false;
    }
}
