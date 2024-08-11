<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dynamic Weather App</title>
    <link rel="stylesheet" href="style.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
</head>
<body>
    <div class="mainContainer">
        <form action="MyServlet" method="post" class="searchInput">
            <input type="text" placeholder="Enter City Name" id="searchInput" value="Delhi" name="city"/>
            <button id="searchButton"><i class="fa-solid fa-magnifying-glass"></i></button>
        </form>
        <div id="loader" style="display:none;">Loading...</div>
        <div id="errorMessage" style="display:none; color: red;">City not found. Please try again.</div>
        <div class="weatherDetails">
            <div class="weatherIcon">
                <img src="" alt="Weather Icon" id="weather-icon">
                <h2>${temperature} °C</h2>
                <input type="hidden" id="wc" value="${weatherCondition}">
            </div>
            <div class="cityDetails">
                <div class="desc"><strong>${city}</strong></div>
                <div class="date">${date}</div>
            </div>
            <div class="windDetails">
                <div class="humidityBox">
                    <img src="humidity.png" alt="Humidity">
                    <div class="humidity">
                        <span>Humidity</span>
                        <h2>${humidity}%</h2>
                    </div>
                </div>
                <div class="windSpeed">
                    <img src="wind.png" alt="Wind Speed">
                    <div class="wind">
                        <span>Wind Speed</span>
                        <h2>${windSpeed} km/h</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="myScript.js"></script>
</body>
</html>
    