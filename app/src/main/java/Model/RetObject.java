package Model;

//This class packs the boolean value and the message string together, use as return value
public class RetObject {
    boolean bool;
    String msg;
    public RetObject() {
        bool = false;
        msg = " ";
    }
    public boolean getBool() {
        return bool;
    }

    public void setBool(boolean _bool) {
        bool = _bool;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String _msg) {
        msg = _msg;
    }
}
