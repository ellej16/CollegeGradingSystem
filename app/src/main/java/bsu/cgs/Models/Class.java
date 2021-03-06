package bsu.cgs.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/*
 * Created by Paul justin on 05/21/2015.
 */
@Table(name="Class")
public class Class extends Model
{
    @Column(name="cSubject")
    public Subject cSubject;

    @Column(name="cName")
    public String cName;

    @Column(name="Students")
    public List<Student> students;

    @Column(name="Criteria")
    public List<Criterion> criteria;




    public Class()
    {
        super();
    }

}
