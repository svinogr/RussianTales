package info.upump.russianapp.model;

import java.util.List;

/**
 * Created by explo on 29.01.2018.
 */

public class Author {
    private long id;
    private String name;
    private String email;
    private int rate;
    private List<Cover> coverList;

    public Author() {
    }

    public Author(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public List<Cover> getCoverList() {
        return coverList;
    }

    public void setCoverList(List<Cover> coverList) {
        this.coverList = coverList;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", rate=" + rate +
                ", coverList=" + coverList.size() +
                '}';
    }
}
