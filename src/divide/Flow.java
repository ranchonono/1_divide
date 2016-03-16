package divide;
import java.io.*;
import java.net.*;
/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Flow {
    public Flow() {
    }
    @SuppressWarnings("static-access")
	public void run(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("uniprot-resistance.fasta"));
          //  BufferedWriter bwm= new BufferedWriter(new FileWriter("m.txt"));
         //   BufferedWriter bws= new BufferedWriter(new FileWriter("s.txt"));
            BufferedWriter bwpfam= new BufferedWriter(new FileWriter("pfamID.txt"));
            String line = br.readLine();
            while (br.ready()) {
                if (line.length() != 0 && line.charAt(0) == '>') {
                    String name = line;
                    StringBuffer sb = new StringBuffer();
                    line = br.readLine();
                    while(br.ready()&&line.length()==0)
                        line = br.readLine();
                    while (line.length() != 0 && line.charAt(0) != '>') {
                        sb.append(line);
                        if (br.ready()) {
                            line = br.readLine();
                        } else {
                            break;
                        }
                    }
                    @SuppressWarnings("unused")
					String seq = sb.toString();
                    //*********************************************
                    String pfamID = name.substring(name.indexOf('|')+1);
                    pfamID = pfamID.substring(0,pfamID.indexOf('|'));
                    System.out.println(pfamID);
                    String site="http://www.uniprot.org/uniprot/"+pfamID;
                    URL url = new URL(site);
                    InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
                    Thread.currentThread().sleep(100);//锟斤拷锟斤拷锟斤拷俨锟斤拷玫幕锟斤拷锟斤拷锟斤拷鼙锟揭拷锟�
                    BufferedReader in = new BufferedReader(isr);
                    //boolean flag = true;
                    while(in.ready()){
                        String s = in.readLine();
                       
                         if(s.indexOf("http://pfam.xfam.org/family/")!=-1){

                             while(s.indexOf("http://pfam.xfam.org/family/")!=-1){
                                 s = s.substring(s.indexOf("http://pfam.xfam.org/family/")+1);
                                 s = s.substring(s.indexOf("PF"));
                                 System.out.println(s);
                                 bwpfam.write(s.substring(0,s.indexOf('"')));
                                 bwpfam.newLine();
                                 bwpfam.flush();
                             }
                             break;

                         }



                    }





                    //*********************************************

                } else {
                    line = br.readLine();
                }
            }

            br.close();

            bwpfam.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }
}
