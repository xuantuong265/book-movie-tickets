package data;

import api.ProductService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.Person;
import model.Products;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductIpml implements ProductService {
    @Override
    public ArrayList<Products> getProduct() throws RemoteException {

        URL url = null;
        ArrayList<Products> list = null;
        try {
            url = new URL("http://localhost:3000/api/get-product");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            // read data
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;) {
                sb.append((char)c);
            }
            String response = sb.toString(); // json tra ve

            JSONArray array = new JSONArray(response);
            list = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);

                Products product = new Products(object.getString("key"), object.getString("name"), object.getString("img"),
                        object.getString("price_current"), object.getString("price_change"), object.getInt("amount"));
                list.add(product);

            }

            return list; // tra ve client


        } catch (MalformedURLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
