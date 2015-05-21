package bsu.cgs.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
/**
 * Created by Paul Justin on 05/21/2015.
 */
@Table(name="students")
public class Student extends Model
{
    @Column(name="snum")
    public String studNum;

    @Column(name="sname")
    public String studName;

    //TODO other details

}
