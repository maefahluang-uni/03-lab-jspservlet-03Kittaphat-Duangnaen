package th.mfu;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calbmi")
public class BMICalculatorServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters from the request: "weight" and "height"
        double weight = Double.parseDouble(request.getParameter("weight"));
        double height = Double.parseDouble(request.getParameter("height"));

        // Calculate BMI
        double bmi = weight / ((height) * (height));
        int roundedBMI = (int) Math.round(bmi);

        // Determine the body type from BMI
        String builtType = "normal";
        if (bmi < 18.5) {
            builtType = "underweight";
        } else if (bmi < 25) {
            builtType = "normal";
        } else if (bmi < 30) {
            builtType = "overweight";
        } else if (bmi < 35) {
            builtType = "obese";
        } else {
            builtType = "extremely obese";
        }

        // Add BMI and body type to the request's attributes
        request.setAttribute("bmi", roundedBMI);
        request.setAttribute("builtType", builtType);

        // Forward to the JSP
        request.getRequestDispatcher("/bmi_result.jsp").forward(request, response);

    }

}
