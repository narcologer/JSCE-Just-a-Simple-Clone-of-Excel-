
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class MathThingyes {
    
    public int sum_list(ArrayList numba) {
    int res=0;
    for (int i=0; i<numba.size(); i++)
    {
        res+=Integer.parseInt((String)numba.get(i));
    }
    return res;
}
    
    public double avg_list(ArrayList numba) {
    double res=0;
    for (int i=0; i<numba.size(); i++)
    {
        res+=Double.parseDouble((String)numba.get(i));
    }
    res=res/numba.size();
    return res;
}
    
    String calc(String fieldcont, DefaultTableModel dtm) {
        String res="";
            ArrayList cell_values=new ArrayList();
        try {
            if (fieldcont.contains("SUMCOL("))
            {
                int col=Integer.parseInt(fieldcont.substring(fieldcont.indexOf('(')+1, fieldcont.indexOf(')')));
                for (int i=0; i<dtm.getRowCount(); i++)
                    cell_values.add((String)dtm.getValueAt(i,col));
                int res2=sum_list(cell_values);
                res=String.valueOf(res2);
                return res;
            }
            else if (fieldcont.contains("SUMROW("))
            {
                int row=Integer.parseInt(fieldcont.substring(fieldcont.indexOf('(')+1, fieldcont.indexOf(')')));
                for (int i=0; i<dtm.getColumnCount(); i++)
                    cell_values.add((String)dtm.getValueAt(row,i));
                int res2=sum_list(cell_values);
                res=String.valueOf(res2);
                return res;
            }
            else
            {
            String numbers=fieldcont.substring(fieldcont.indexOf('(')+1, fieldcont.indexOf(')'));
            String[] numarr=numbers.split(";");
            List<String> n=Arrays.asList(numarr);
            for (int i=0; i<n.size(); i++) {           
                int str_num = Integer.parseInt(n.get(i).substring(0,(n.get(i).indexOf('-'))));
                int elem_num = Integer.parseInt(n.get(i).substring(n.get(i).indexOf('-')+1,n.get(i).length()));
                cell_values.add((String)dtm.getValueAt(str_num, elem_num));
            }
            if (fieldcont.contains("SUM("))
            {
                int res2=sum_list(cell_values);
                res=String.valueOf(res2);
            }
            else if (fieldcont.contains("AVG("))
            {
                double res2=avg_list(cell_values);
                res=String.valueOf(res2);
            }
            else throw new NullPointerException("fap-fap, nya");
        }
        }
        catch (Exception e) {res="ERROR";}
        return res;
}
    
}
