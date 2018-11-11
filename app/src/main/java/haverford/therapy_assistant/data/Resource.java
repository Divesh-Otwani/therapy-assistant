package haverford.therapy_assistant.data;

// Zach should work on this

public class Resource {
    private String Name;
    private int UID;
    private String Title;
    private String Description;
    private int ResType;

    public Resource(String name, int uid, String title, String des, int restype){
        Name = name;
        UID = uid;
        Title = title;
        Description = des;
        ResType = restype;

    }
}
