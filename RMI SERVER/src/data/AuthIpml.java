package data;

import api.AuthService;
import model.Person;
import model.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class AuthIpml implements AuthService {

    public static ArrayList<Student> arrayList = new ArrayList<>();
    public static HttpURLConnection connect;

    @Override
    public Person login(String email, String password) throws RemoteException {


        URL url = null;
        try {
            url = new URL("http://localhost:3000/api/login");

            Map<String,Object> params = new LinkedHashMap<>();
            params.put("email", email);
            params.put("password", password);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            // read data
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;) {
                sb.append((char)c);
            }
            String response = sb.toString(); // json tra ve

            if (response.equals("\"error\"")) {
                return null;
            }else {
                JSONArray array = new JSONArray(response);

                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);

                    Person person = new Person(object.getString("key"), object.getString("name"), object.getString("email"),
                            object.getString("password"), object.getString("address"), object.getString("phone"));

                    return person;
                }
            }


        } catch (MalformedURLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public String register(String name, String email, String password, String address, String phone) throws RemoteException {
        URL url = null;
        try {
            url = new URL("http://localhost:3000/api/register");

            Map<String,Object> params = new LinkedHashMap<>();
            params.put("name", name);
            params.put("email", email);
            params.put("password", password);
            params.put("address", address);
            params.put("phone", phone);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);


            // read data return;
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;) {
                sb.append((char)c);
            }
            String response = sb.toString();

            return response;

        } catch (MalformedURLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public ArrayList<Student> getUsers() throws RemoteException {

        try {
            BufferedReader reader;
            String line;
            StringBuffer buffer = new StringBuffer();

            URL url = new URL("http://localhost:3000/home");
            connect = (HttpURLConnection) url.openConnection();

            connect.setRequestMethod("GET");
            connect.setConnectTimeout(2000);
            connect.setConnectTimeout(2000);

            int status = connect.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connect.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
            }else {
                reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                arrayList.clear();


                JSONArray jsonArray = new JSONArray(buffer.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Student student = new Student();
                    student.setName(jsonObject.getString("name"));
                    student.setEmail(jsonObject.getString("email"));
                    student.setPassword(jsonObject.getString("password"));

                    arrayList.add(student);
                }


                return arrayList;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
