package advancedprogramming;



import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author barnabe
 */

public class NetInterface{


public static void main(String args[]) throws SocketException
    {
        IpUser();
    }


static ArrayList<String> GetIp() throws SocketException
    {


        //grab the network interface and store as a list

        ArrayList<NetworkInterface> NiList = Collections.list(NetworkInterface.getNetworkInterfaces());

        // network interface is a class it s made up of names and list of IP addresses

        ArrayList<String> data = new ArrayList<>();

        for (NetworkInterface Ips1 : NiList)
        {
             /*InetAddress is a method used to get IP address of any hostname which in
               this case would be the IPs of where the code run*/

            Enumeration<InetAddress> ipList = Ips1.getInetAddresses();

                while (ipList.hasMoreElements())
                {
                    // here i am changing all the data from ipList which are of type InetAddress and changing
                    //them to string as it is easier for me to manipulate

                    String Intel = ipList.nextElement().toString();

                    // and then add them to an arraylist
                    //System.out.println("Intel:"+Intel);
                    data.add(Intel);
                }
        }

        int SizeList = data.size();
        int i = 0;

        /*the minus one here is used to avoid an outof bound error though i wish to make
        LoopBackAdre = /127.0.0.1 to remove the loopbackaddress from the list
        but it appear to not recogonize it when i do so  (will improve when i can )*/

        String LoopBackAdre=data.get(SizeList - 1);

        ArrayList<String> ipArray = new ArrayList<String>();

        /*this while loop should be removing all the wrong addresses and the loopback address
        by removing all address with "." in and by removing the last item of the list data
        (which is technically the loopback though i need to check*/

        while(i != SizeList)
        {
            String itemChecked = data.get(i);

            if(itemChecked.contains(".") && itemChecked != LoopBackAdre)
            {
                ipArray.add(data.get(i));

            }

            i++;
        }
        
        if(ipArray.contains("/127.0.0.1"))
        {
            //System.out.println("before loopback: "+ipArray);
            int LoopBak = ipArray.indexOf("/127.0.0.1");  
            ipArray.remove(LoopBak);
            //System.out.println("after loopback: "+ipArray);
        }
        
        //System.out.println("ipArray: "+ ipArray);
        return ipArray;



    }


static String IpUser()
     /* this method as for purpose to remove the "/" character and
        to turn the arraylist string into just a string */
    {
        ArrayList<String> yolo = new ArrayList<String>() ;
        try {
            yolo = NetInterface.GetIp();
        } catch (SocketException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(yolo);
        String UsIp = yolo.get(0);
        //System.out.println(UsIp);
        UsIp = UsIp.replace("/", "");
        //System.out.println(UsIp);
        return UsIp;
    }



    
}
