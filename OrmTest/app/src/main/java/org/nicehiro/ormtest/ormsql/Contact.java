package org.nicehiro.ormtest.ormsql;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by hiro on 16-11-15.
 */

@Entity
public class Contact {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String uId;
    private String yId;
    private int gender;
    private String mobile;
    private String photourl;

    @Property(nameInDb = "nickname")
    private String name;

    @Generated(hash = 1768738186)
    public Contact(Long id, @NotNull String uId, String yId, int gender,
            String mobile, String photourl, String name) {
        this.id = id;
        this.uId = uId;
        this.yId = yId;
        this.gender = gender;
        this.mobile = mobile;
        this.photourl = photourl;
        this.name = name;
    }

    @Generated(hash = 672515148)
    public Contact() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotourl() {
        return this.photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getYId() {
        return this.yId;
    }

    public void setYId(String yId) {
        this.yId = yId;
    }

    public String getUId() {
        return this.uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
