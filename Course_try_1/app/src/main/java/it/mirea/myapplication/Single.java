package it.mirea.myapplication;

public class Single {

    private static final Single INSTANCE = new Single();

    public Single(){
        logic = false;
        name = "";
        email="";
        number="";
        text = "";
        type = 0;
    }

    Boolean logic;
    String name, email, number, text;
    int type;

    public static Single getInstance(){
        return INSTANCE;
    }

    public Boolean getLogic() {
        return logic;
    }

    public void setLogic(Boolean logic) {
        this.logic = logic;
    }
}