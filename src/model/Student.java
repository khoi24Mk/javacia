package model;

public class Student {


    private String _name;
    private String _ID;
    private String _class;
    private int _age;

    public Student(){

    }

    public Student(String _name, String _ID, String _class, int _age) {
        this._name = _name;
        this._ID = _ID;
        this._class = _class;
        this._age = _age;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public int get_age() {
        return _age;
    }

    public void set_age(int _age) {
        this._age = _age;
    }

    public void getInfo(){
        System.out.println(get_ID() + " " + get_name() +" "+ get_class() + " " +get_age());
    }


    public Object[] getObjects() {
        return new Object[]{
                this.get_ID(),
                this.get_name(),
                this.get_class(),
                this.get_age()
        };
    }

    public static String [] getHeader(){
        return new String []{"ID", "Name","Class","Age"};
    }

}
