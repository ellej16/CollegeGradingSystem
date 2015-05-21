package bsu.cgs.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
/**
 * Created by Paul Justin on 05/21/2015.
 */
@Table(name="subjects")
public class Subject extends Model
{
    @Column(name="subjcode")
    public String subjcode;

    @Column(name="desc")
    public String description;

    public Subject(String subjcode, String description)
    {
        this.subjcode = subjcode;
        this.description = description;
    }
}
