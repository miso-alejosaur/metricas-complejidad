package shipping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;

public class Shipping {
	public static Map<String, Integer> multiplicadores = new HashMap<>();
	public static Integer precioBase = 100;
	public static Integer pesoTope = 5;
	public static Integer precioAdicional = 10;

	static {
		multiplicadores.put("argentina", 1);
		multiplicadores.put("brasil", 2);
		multiplicadores.put("chile", 3);
		multiplicadores.put("uruguay", 4);
	}

	public Shipping() {
		super();
	}

	public int calcularCostoEnvio(String pais, int peso) {
		String paisMinusculas = pais.toLowerCase();
		int costo = obtenerCosto(peso, paisMinusculas);

		return costo;
	}

	private int obtenerCosto(int peso, String paisMinusculas) {
		if (!multiplicadores.containsKey(paisMinusculas))
			return -1;
			
		int multiplicador = multiplicadores.get(paisMinusculas);

		int costo = precioBase * multiplicador;
		
		if (peso > pesoTope)
			costo += (peso - pesoTope) * 10 * multiplicador;

		return costo;
	}

	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			int peso = Integer.parseInt(in.readLine());
			String pais = in.readLine();

			Shipping ship = new Shipping();
			int costo = ship.calcularCostoEnvio(pais, peso);

			mostrarCosto(costo);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void mostrarCosto(int costo) {
			if (costo == -1) {
				System.out.println("El pais ingresado no es valido");
			} else {
				System.out.println("El costo del envio es: " + costo);
			}
	}
}
