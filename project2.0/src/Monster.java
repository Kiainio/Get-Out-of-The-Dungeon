class Monster {
    private int InitialHP;
    private int HP;
    private int Attack;
    private Posi Position;
    boolean isDizzy;
    boolean isDead = true;

    int getHP() {
        return HP;
    }

    int getInitialHP() {
        return InitialHP;
    }

    void setHP(int HP) {
        this.HP = HP;
    }

    int getAttack() {
        return Attack;
    }

    void setAttack(int id, PlayerStage playerStage) {
        switch (id) {
            case 1:
                this.Attack = 10 + ((playerStage.getStage() - 1) * 5);
                break;
            case 2:
                this.Attack = 15 + ((playerStage.getStage() - 1) * 5);
                break;
            case 3:
                this.Attack = 20 + ((playerStage.getStage() - 1) * 5);
                break;
        }
    }

    void setName(int id) {
        switch (id) {
            case 1:
                String name = "Bat";
                break;
            case 2:
                name = "Skull";
                break;
            case 3:
                name = "Goblin";
                break;
        }
    }

    void setInitialHP(int id) {
        switch (id) {
            case 1: case 2:
                this.HP = 100;
                this.InitialHP = 100;
                break;
            case 3:
                this.HP = 200;
                this.InitialHP = 200;
                break;
        }
    }

    void beenAttacked(int Attack) {
        setHP(Math.max(0, HP - Attack));
    }

    Posi getPosition() {
        return this.Position;
    }

    void setPosition(Posi position) {
        this.Position = position;
    }

    void getDizzy(){
        this.isDizzy = true;
    }
}

