package bsu.cgs.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
/**
 * Created by Paul Justin on 05/21/2015.
 */
@Table(name="User")
public class User extends Model{
    @Column(name="username")
    public String uname;

    @Column(name="password")
    public String pword;

    public User(String uname,String pw)
    {
        super();
        this.uname = uname;
        this.pword = pw;
    }
}
