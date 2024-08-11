package MyPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
    			
    			// TODO Auto-generated method stub
    			String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=7d71fc2ed9eabdfc0713e0671dbff0bd";
    			//7d71fc2ed9eabdfc0713e0671dbff0bd
    			//"https://api.openweathermap.org/data/2.5/forecast/daily?q={new%20delhi&cnt={cnt}&appid=d40d0b9c6eb99957b5dfba5434022730"

    			
    			//API integration perform an HTTP GET request using HttpURLConnection and read the response via an InputStream:
        
        try {
            // Create URL object and establish HTTP connection
            @SuppressWarnings("deprecation")
			URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            // Read API response
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);
            StringBuilder responseContent = new StringBuilder();
            
            while (scanner.hasNext()) {
                responseContent.append(scanner.nextLine());
            }
            scanner.close();
            
            // Parse JSON response
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);
            
            // Extract required data from JSON
            long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
            String date = new Date(dateTimestamp).toString();
            double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
            int temperatureCelsius = (int) (temperatureKelvin - 273.15);
            int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
            double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
            String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
            
            // Set request attributes to forward to index.jsp
            request.setAttribute("date", date);
            request.setAttribute("city", city);
            request.setAttribute("temperature", temperatureCelsius);
            request.setAttribute("weatherCondition", weatherCondition);
            request.setAttribute("humidity", humidity);
            request.setAttribute("windSpeed", windSpeed);
            request.setAttribute("weatherData", responseContent.toString());
            
            connection.disconnect();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Forward the request to the index.jsp page for rendering
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}



