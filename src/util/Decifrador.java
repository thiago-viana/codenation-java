package util;

public class Decifrador {
	
	String alfabeto = "abcdefghijklmnopqrstuvwxyz";
	
	public String decifrar(String cifrado, int numeroCasas) {
		
		int distancia = alfabeto.length() - numeroCasas;
		
		StringBuilder decifrado = new StringBuilder();
		
		for (int i = 0; i < cifrado.length(); i++) {
			char caractere = cifrado.charAt(i);
			int posicao = alfabeto.indexOf(caractere); 
			if (posicao == -1) {
				decifrado.append(caractere);
				continue;
			}
			if (((alfabeto.length() - 1) - posicao) >= distancia) {
				decifrado.append(alfabeto.charAt(posicao + distancia));
			} else {
				decifrado.append(alfabeto.charAt((distancia - 1) - ((alfabeto.length() - 1) - posicao)));				
			}
		}
		
		return decifrado.toString();
	}
}
