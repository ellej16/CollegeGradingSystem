package bsu.cgs.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
/**
 * Created by Paul Justin on 05/21/2015.
 */
@Table(name="Criterion")
public class Criterion extends Model {

    @Column(name="critName")
    public String CriterName;

    @Column(name="percent")
    public double percentage;

    @Column(name="items")
    public int items;

    public Criterion( String name, double percent, int items){
        this.CriterName = name;
        this.percentage = percent;
        this.items = items;
    }
    public Criterion(){}

}
