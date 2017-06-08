/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.example.Pooh.myapplication.backend_servlet;


import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {



    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {



        PrintWriter out = resp.getWriter();
        try {
            Class.forName("com.mysql.jdbc.GoogleDriver");
        } catch (ClassNotFoundException e) {
            out.println("1------------1"+e.getMessage());

        }

            try {
                Connection conn = DriverManager.getConnection("jdbc:google:mysql://unified-sensor-91603:youtoodb/youtoo?user=root");
                try {
                    String id=req.getParameter("id");
                    switch(id){
                        case "send_message":out.println(sendMessageQuery(req, conn));
                            break;
                        case "show_topic": {
                            ArrayList<String> topics = new ArrayList<>();
                            //topics.add("LIST OF TOPICS:");

                            topics = getTopics(topics, conn);


                            JSONObject obj=new JSONObject();
                            try {
                                obj.put("topics",new JSONArray(topics));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            out.println(obj);

                            break;
                        }

                        case "save_user_device":
                        {
                            out.println(saveDevice(req,conn));
                            break;
                        }
                        case "view_message": {
                            JSONObject json;
                            json = viewMessageQuery(req, conn);
                            if(json!=null)
                                  out.println(json);


                            break;
                        }
                        case "add_interest":out.println(addInterest(req, conn));
                            break;
                        case "accept_message": out.println(acceptMsg(req,conn));
                            break;
                        case "view_accept":{
                            JSONObject json;
                            json = viewConnection(req, conn);

                            out.println(json);


                            break;

                        }



                    }

                } finally {
                    conn.close();
                }
            } catch (SQLException e) {
                out.println("2-------2" + e.getMessage());


            }


    }



    public String acceptMsg(HttpServletRequest req, Connection conn){
        String response="";
        String username=req.getParameter("username");
        String sender=req.getParameter("sender");
        double lati=Double.parseDouble(req.getParameter("latitude"));
        double longi=Double.parseDouble(req.getParameter("longitude"));


        String statement = "insert into accept(sender,receiver,r_lati,r_longi) value (?,?,?,?)";
        try { PreparedStatement stmt = conn.prepareStatement(statement);
            stmt.setString(1, sender);
            stmt.setString(2, username);
            stmt.setDouble(3, lati);
            stmt.setDouble(4, longi);


            boolean success ;
            success = stmt.execute();
            if (success) {
                return     "Failure! Please try again! ";
            } else {
                return   " sent to sender";
            }

        } catch (SQLException e) {
            return "Error in sql"+e.getMessage();
        }

    }




    public ArrayList<String> getTopics(ArrayList<String> r, Connection conn){
        String query = "select * from topic";


            PreparedStatement stmnt = null;
        try {
            stmnt=conn.prepareStatement(query);
        } catch (SQLException e) {
            r.add(e.getMessage());
        }

        // create the java statement


            // execute the query, and get a java resultset
        ResultSet success = null;
        try {
            success = stmnt.executeQuery();
        } catch (SQLException e) {
            r.add(e.getMessage());
        }
        String name="";

        try {
            while(success.next()){
               name = success.getString(2);
                r.add(name);


            }
        } catch (SQLException e) {
            r.add(e.getMessage());
        }
        try {
            success.close();
        } catch (SQLException e) {
            r.add(e.getMessage());
        }
        try {
            stmnt.close();
        } catch (SQLException e) {
            r.add(e.getMessage());
        }


        return r;
    }

public String saveDevice(HttpServletRequest req, Connection conn){
    String deviceId=req.getParameter("deviceId");
    String regid=req.getParameter("regId");

    String username=req.getParameter("username");
    String password=req.getParameter("pwd");


            String statement = "insert into user_new(user_id,reg_id,device_id,username,password) value (null,?,?,?,?)";
            try { PreparedStatement stmt = conn.prepareStatement(statement);
                stmt.setString(1, regid);
                stmt.setString(2, deviceId);
                stmt.setString(3, username);
                stmt.setString(4,password);


                boolean success ;
                success = stmt.execute();
                if (success) {
                    return     "Failure! Please try again! ";
                } else {
                    return   "success";
                }

            } catch (SQLException e) {
                return "Error in sql"+e.getMessage();
            }

    }

    public JSONObject viewConnection(HttpServletRequest req, Connection conn){
        JSONObject response = new JSONObject();
        String statement = "select * from accept where sender=?";
        String username=req.getParameter("username");
        ArrayList<String> receiver=new ArrayList<>();
        ArrayList<Double> lati=new ArrayList<>();
        ArrayList<Double> longi=new ArrayList<>();
        ArrayList<String> errors=new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(statement);
            stmt.setString(1,username);


            ResultSet success = stmt.executeQuery();
            while(success.next()){

                    receiver.add(success.getString("receiver"));
                    lati.add(success.getDouble("r_lati"));
                    longi.add(success.getDouble("r_longi"));


            }

        } catch (SQLException e) {
            errors.add( "Error in sql" + e.getMessage());
        }

        try {
           response.put("receiver",new JSONArray(receiver));
           response.put("latitude",new JSONArray(lati));
           response.put("longitude",new JSONArray(longi));
            response.put("data","trial");
            response.put("errors",new JSONArray(errors));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;

    }

    public JSONObject viewMessageQuery(HttpServletRequest req, Connection conn)

    {
        String statement = "select * from interest where username=?";
        String username=req.getParameter("username");
        ArrayList<String> errors=new ArrayList<String>();

        ArrayList<String> interest=new ArrayList<String>();


        try {
            PreparedStatement stmt = conn.prepareStatement(statement);
            stmt.setString(1,username);


            ResultSet success = stmt.executeQuery();
            while(success.next()){
                if(!interest.contains(success.getString("interest"))){
                    interest.add(success.getString("interest"));}

            }

        } catch (SQLException e) {
            errors.add( "Error in sql" + e.getMessage());
        }

         String statement1 = "select * from messages where interest=? order by time desc limit 2";
        ArrayList<String> msgs=new ArrayList<>();
        ArrayList<String> sender=new ArrayList<>();
        ArrayList<String> lati=new ArrayList<>();
        ArrayList<String> longi=new ArrayList<>();
            for(int i=0;i<interest.size();i++) {

                try {
                    PreparedStatement stmt2 = conn.prepareStatement(statement1);
                    stmt2.setString(1,interest.get(i).toString());

                    ResultSet success = stmt2.executeQuery();
                    while (success.next()) {
                        msgs.add(success.getString("message"));
                        sender.add(success.getString("username"));
                        lati.add(success.getString("latitude"));
                        longi.add(success.getString("longitude"));

                    }

                } catch (SQLException e) {
                    errors.add( "Error in sql" + e.getMessage());
                }
            }
        JSONObject response=new JSONObject();
        try {
           response.put("errors", new JSONArray(errors));
            response.put("messages", new JSONArray(msgs));
            response.put("sender",new JSONArray(sender));
            response.put("interests",new JSONArray(interest));
            response.put("latitude", new JSONArray(lati));
            response.put("longitude", new JSONArray(longi));
        } catch (JSONException e) {

            e.printStackTrace();
        }

        return response;
        }

    public String sendMessageQuery(HttpServletRequest req, Connection conn){
        String username = req.getParameter("username");
        String interest=req.getParameter("interest");
        String message=req.getParameter("message");
        double latitude=Double.parseDouble(req.getParameter("latitude"));
        double longitude=Double.parseDouble(req.getParameter("longitude"));

        if (username == "" || message == "") {
            return  "You are missing either a Message or a name! Try again! ";
        } else {
            String statement = "insert into messages(username,interest,message,time,latitude,longitude) values (?,?,?,now(),?,?)";
            try { PreparedStatement stmt = conn.prepareStatement(statement);


            stmt.setString(1,username);
            stmt.setString(2,interest);
            stmt.setString(3, message);
                stmt.setDouble(4, latitude);
                stmt.setDouble(5, longitude);
            boolean success ;
            success = stmt.execute();
            if (success) {
               return     "Failure! Please try again! ";
            } else {
                String m=sendNotification(username,interest,conn);
                return   m;
            }
            } catch (SQLException e) {
                return "Error in sql"+e.getMessage();
            }
        }
    }

public String sendNotification(String username,String interest, Connection conn){

    //select usernames using interest
    //send array to get deviceId , regId and message
    String statement = "select username from interest where interest=? AND username <> ?";

    ArrayList<String> users=new ArrayList<String>();


    try {
        PreparedStatement stmt = conn.prepareStatement(statement);
        stmt.setString(1,interest);
        stmt.setString(2,username);


        ResultSet success = stmt.executeQuery();
        while(success.next()){
            if(!users.contains(success.getString("username")))
                 users.add(success.getString("username"));
        }

    } catch (SQLException e) {
        return "Error in sql" + e.getMessage();
    }
    int i;
    ArrayList<String> regId=new ArrayList<String>();
    ArrayList<String> deviceId=new ArrayList<>();
    if(users.size()!=0) {
        for (i = 0; i < users.size(); i++) {
            String statement2 = "select * from user_new where username=?";


            try {
                PreparedStatement stmt2 = conn.prepareStatement(statement2);
                stmt2.setString(1, users.get(i));


                ResultSet success = stmt2.executeQuery();
                while (success.next()) {
                    if(!deviceId.contains(success.getString("device_id"))) {
                        regId.add(success.getString("reg_id"));
                        deviceId.add(success.getString("device_id"));
                    }
                }
            } catch (SQLException e) {
                return "Error in sql" + e.getMessage();
            }
        }
       JSONObject innerdata=new JSONObject();
        try {
            innerdata.put("devices", new JSONArray(deviceId));
            innerdata.put("sender", username);
            innerdata.put("interest",interest);
            innerdata.put("message","NEW MESSAGE AVAILABLE");
        } catch (JSONException e) {
            return "Error in devices" + e.getMessage();
        }

        JSONObject sendtogcm = new JSONObject();
        try {
            sendtogcm.put("registration_ids",new JSONArray(regId));
            sendtogcm.put("data",innerdata);
        } catch (JSONException e) {
            return "Error in sendtogcm" + e.getMessage();
        }
        POST2GCM p=new POST2GCM(sendtogcm);
        String res=p.post();
        return res;
    }
    else return "NO receivers with same interest";

}

    public String addInterest(HttpServletRequest req, Connection conn){
        String username = req.getParameter("username");
        String interest=req.getParameter("interest");


        if (username == "" || interest == "") {
            return  "You are missing either a Interest or a name! Try again! ";
        } else {
            String statement = "insert into interest(interest,username) values (?,?)";
            try { PreparedStatement stmt = conn.prepareStatement(statement);

                stmt.setString(1,interest);

                stmt.setString(2,username);

                boolean success ;
                success = stmt.execute();
                if (success) {
                    return     "Failure! Please try again! ";
                } else {
                    return   "Interest Added";
                }
            } catch (SQLException e) {
                return "Error in sql"+e.getMessage();
            }
        }
    }
}

