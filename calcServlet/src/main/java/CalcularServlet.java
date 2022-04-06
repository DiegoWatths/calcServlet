

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;

/**
 * Servlet implementation class CalcularServlet
 */

@WebServlet("/CalcularServlet")
public class CalcularServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcularServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public float Sumar(float x, float y) {
    	return x + y;
    }
    
    public float Restar(float x, float y) {
    	return x - y;
    }
    
    public float Multiplicar(float x, float y) {
    	return x * y;
    }
    
    public float Dividir(float x, float y) {
    	return x / y;
    }
    
    public float Porc(float x, float y) {
    	return y * (x / 100);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String body = request.getReader().readLine();
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(body);
			Float value = null;
			
			System.out.println(obj.get("number1").toString());
			System.out.println(obj.get("number2").toString());
			System.out.println(obj.get("operation").toString());
			String op = obj.get("operation").toString();
			
			switch(op) {
				case "+":
					value = this.Sumar(Float.parseFloat(obj.get("number1").toString()), Float.parseFloat(obj.get("number2").toString()));
					break;
				case "-":
					value = this.Restar(Float.parseFloat(obj.get("number1").toString()), Float.parseFloat(obj.get("number2").toString()));
					break;
				case "X":
					value = this.Multiplicar(Float.parseFloat(obj.get("number1").toString()), Float.parseFloat(obj.get("number2").toString()));
					break;
				case "÷":
					if(Float.parseFloat(obj.get("number2").toString()) != 0){
						value = this.Dividir(Float.parseFloat(obj.get("number1").toString()), Float.parseFloat(obj.get("number2").toString()));
					}else{
						value = 0F;
					}
					break;
				case "%":
					if(Float.parseFloat(obj.get("number2").toString()) != null){
						value = this.Porc(Float.parseFloat(obj.get("number1").toString()), Float.parseFloat(obj.get("number2").toString()));
					}else{
						value = this.Porc(Float.parseFloat(obj.get("number1").toString()), 1F);
					}
					break;
				default: System.out.println("INVALID OPERATION >:c"); break;
			}
			
			response.getWriter().println("{\"value\" : \""+ value +"\"}");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
