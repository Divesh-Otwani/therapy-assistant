package haverford.therapy_assistant.data.resource;

import java.net.MalformedURLException;
import java.net.URL;

/* Right now, there is only one kind of resource: a url, which is not the design we will use.
This is just preliminary. We will make this an abstract class with a ResType for the resource type
and have several classes which implement this and are proper resources like an ArticleResource.
* */

public class Resource {
    private String mName;
    private int mUID;
    private String mTitle;
    private String mDescription; //unused
    private ResType mResType; //an enum - Article or Video

    public Resource(String name, int uid, String title, String des, ResType rt){

        mName = name;
        mUID = uid;
        mTitle = title;
        mDescription = des;
        mResType = rt;
    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getUID() {
        return mUID;
    }

    public void setUID(int UID) {
        mUID = UID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public ResType getRestype() {
        return mResType;
    }

    public void setRestype(ResType restype) {
        mResType = restype;
    }

    public URL getURL() {
        try {
            return new URL(this.getTitle());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String toString(){
        return getRestype().name() + ": " + getTitle() + " " + getName();
    }

}