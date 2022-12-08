package com.example.kyrsach.server;
import com.example.kyrsach.database.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/*
Отче наш, иже еси на АйБиЭм ПиСи, код наш насущный дай нам!Помилуй нас от пэйпер джэма и фатал эрора!
Дай нам MD-ков неубываемых и паролей на них. Искупи нам десницей Своей комплектующих для Апгрейда ненасытного,
ибо душит нас Жаба великая и страдаем мы от недостатка ресурсов системных. Не допусти выполнения недопустимой
операции и скрой сведения от очей наших. Не дай нам впасть во искушение новыми релизами, ибо лукав Нуралиев-искуситель
сотоварищи. Удержи нас грешных от эф-диска и формата. Да минуют нас бэд-блоки и вирусы. Умерь гордыню нашу,
ибо не зашарим и крохи малой ближнему своему.

Избавь нас от Юзера глупого, ибо продвинут он стал и имя ему БУХ. Выправи программы наши кривые, ибо клинит башни нам от к
онфигураторов и отладчиков Открой нам знание, ибо истинно говоришь Ты нам, где тру и фолс. Откомментируй код наш многомег
абайтный, ибо погрязли мы в MD-шниках своих

Укроти ламеров, имя Твое всуе поминающих, ибо не ведают, что творят. Упаси нас от Галактик и Бэстов. Да приидет царствие од
инэса. Не заставь нас нырять в мануалы бездонные и хэлпы путанные, ибо ересь в них, а дай нам уразуметь ридмя крошечные, иб
о в них слово Господне.

Инкапсулируй объекты наши, ибо полиморфны они и наследуют Царствие Твое. Выведи нас из цикла бесконечного, ибо на перехо
д Твой безусловный уповаем. Укажи нам путь верный, ибо блуждаем мы средь интерфейсов и парадигм и несть им числа. Не п
ронеси мимо чашу сию неупиваемую, ибо пива
жаждем мы и коннекта алкаем.
 */
public class Main {

    public static void main(String args[]) throws IOException
    {
        Server server = new Server();
        return;
    }

}

class Server {

    private ServerSocket server = null;
    private Socket client = null;
    public static int  numberOfOnline = 0;

    public Server()
    {
        try {
            try {
                server = new ServerSocket(3456);
                System.out.println("Waiting...");
                while (true) {
                    client = server.accept();
                    numberOfOnline++;
                    System.out.println("Клиент присоединился к серверу");
                    System.out.println("К серверу на данный момент подключено " + numberOfOnline + " клиентов");
                    Runnable r = new ThreadEchoHandler(client);
                    Thread t =  new Thread(r);
                    t.start();
                }
            }
            finally {
                client.close();
                server.close();;
            }
        }

        catch (IOException ex)
        {
            ex.printStackTrace();
        }



    }

}

class ThreadEchoHandler implements Runnable {

    private Socket client = null;

    //private boolean stoped;

    //protected boolean isAuthorized = false;



    usersModel usersTable;
    productsModel productsTable;
    manufacturersModel manufacturersTable;

    public ThreadEchoHandler(Socket socket)
    {
        client = socket;
        usersTable = new usersModel();
        productsTable = new productsModel();
        manufacturersTable = new manufacturersModel();
    }

    /*public void setStop() {
        stoped = true;
    }*/

    public void run()
    {
        try{

            //Входной поток сервера
            InputStream inStream = client.getInputStream();
            BufferedReader inputLine = new BufferedReader(new InputStreamReader(inStream));

            //Выходной поток сервера
            OutputStream outStream = client.getOutputStream();
            PrintWriter out = new PrintWriter(outStream,true);

            //BufferedReader bRead = new BufferedReader(new InputStreamReader(System.in));
            String str = null;


            //Принимаем строку от клиента
            while (true) {

                str = inputLine.readLine();

                System.out.println(str);


                try {



                    //String m;
                    System.out.println("Ввод:");
                    //m = str;//bRead.readLine();




                    if (Objects.equals(str, "showproducts")){
                        out.println(productsTable.selectAllProducts());
                    } else if (Objects.equals(str, "showmanufacturers")){
                        out.println(manufacturersTable.selectAllManufacturers());
                    } else{


                        String[] dataSliced = str.split("\\+"); // нулевой элемент в массиве это команда
                        if (Objects.equals(dataSliced[0], "addproduct")) {
                            if (manufacturersTable.isSuchManufacturerExists(dataSliced[2])){ // manufacturer id
                                productsTable.insertProduct(dataSliced);
                                out.println("ok");
                            }else{
                                out.println("not");
                            }


                        }
                        if (Objects.equals(dataSliced[0], "addmanufacturer")) {
                            manufacturersTable.insertManufacturer(dataSliced);
                            out.println("ok");
                        }
                        if (Objects.equals(dataSliced[0], "deleteproduct")) {
                            String answer = productsTable.deleteProduct(dataSliced);
                            out.println(answer);
                        }
                        if (Objects.equals(dataSliced[0], "deletemanufacturer")) {
                            String answer = manufacturersTable.deleteManufacturer(dataSliced);
                            productsTable.deletManufacturerOfProducts(dataSliced[1]); // все таки решил удалять
                            out.println(answer);
                        }
                        if (Objects.equals(dataSliced[0], "findproduct")) {
                            String answer = productsTable.findProduct(dataSliced);
                            if (answer.isEmpty()){
                                answer = "not";
                            }
                            out.println(answer);
                        }
                        if (Objects.equals(dataSliced[0], "findmanufacturer")) {
                            String answer = manufacturersTable.findManufacturer(dataSliced);
                            if (answer.isEmpty()){
                                answer = "not";
                            }
                            out.println(answer);
                        }
                        if (Objects.equals(dataSliced[0], "editproduct")) {
                            String answer = productsTable.editProduct(dataSliced);
                            out.println(answer);
                        }
                        if (Objects.equals(dataSliced[0], "editmanufacturer")) {
                            String answer = manufacturersTable.editManufacturer(dataSliced);
                            out.println(answer);
                        }
                        if (Objects.equals(dataSliced[0], "login")) {
                            String[] answer = usersTable.login(dataSliced);
                            if (Objects.equals(answer[0], "ok")){
                                out.println(answer[1]);
                            }else{
                                out.println("not");
                            }

                        }
                        if (Objects.equals(dataSliced[0], "register")) {
                            if (!usersTable.isSuchUserExists(dataSliced)){ // если нету такого юзера
                                String[] answer = usersTable.register(dataSliced);
                                    if (Objects.equals(answer[0], "ok")){
                                        out.println(answer[1]);
                                    } else{
                                        out.println(answer[0]);
                                    }

                            }else{
                                out.println("not");
                            }

                        }

                    }


                } catch (NumberFormatException ex) {

                }
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        try {
            client.close();
            Server.numberOfOnline--;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }


}