package droid.zaeem.notifierx.database;

/**
 * Created by Droid on 8/1/2016.
 */
public class Message {
    private int id;
    private String title;
    private String body;
    public Message()
    {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }





    // constructor

    public Message (int id,String title,String body)
    {
        this.id=id;
        this.title=title;
        this.body=body;

    }

    public Message (String title,String body)
    {
        this.title=title;
        this.body=body;

    }

}
