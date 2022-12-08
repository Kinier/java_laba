package com.example.kyrsach.client;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;



public class Client {
    private static Client instance;

    Socket client = null;
    PrintWriter out;
    BufferedReader inputLine;
    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }
    public Client()
    {
        try {

            client = new Socket(InetAddress.getLocalHost().getLocalHost(), 3456);

            //Создаем выходной поток клиента
            OutputStream outStream = client.getOutputStream();
            out = new PrintWriter(outStream, true);

            //Создаем входной поток клиента
            InputStream inStream = client.getInputStream();
            inputLine = new BufferedReader(new InputStreamReader(inStream));

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public String Run(String request)
    {
    String str2;
    str2 = "";
        try {


                out.println(request);

                out.flush();


                str2 = inputLine.readLine();





        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return str2;
    }

    public void Stop()
    {
        try {
            out.close();
            inputLine.close();
            client.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }


}