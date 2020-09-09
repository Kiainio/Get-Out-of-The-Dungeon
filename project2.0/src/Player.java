class Player{
    private int HP = 500;
    private int Mana = 100;
    private Posi Position = new Posi(0, 0);

    int getHP() {
        return HP;
    }

    void setHP(int hp) {
        HP = hp;
    }

    int getAttack() {
        return 20;
    }

    int getMana() {
        return Mana;
    }

    void setMana(int mana) {
        Mana = mana;
    }

    Posi getPosition() {
        return this.Position;
    }

    void setPosition(Posi position) {
        this.Position = position;
    }
}