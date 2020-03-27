package principal;

import java.io.File;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.Arquivo;
import util.Decifrador;
import util.Requisicao;

public class Aplicacao {
	
	public static void main(String[] args) throws Exception {
		
		String token = "coloque-aqui-o-seu-token";
		String urlGet = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=" + token;
		String urlPost = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=" + token;
		
		Requisicao requisicao = new Requisicao();
		Arquivo arquivo = new Arquivo();
		ObjectMapper mapper = new ObjectMapper();
		Decifrador decifrador = new Decifrador();
		
		File arquivoJson = requisicao.get(urlGet);
		
		Map<String,Object> map = mapper.readValue(arquivoJson, new TypeReference<Map<String,Object>>() {});
		String cifrado = (String) map.get("cifrado");
		Integer numeroCasas = (Integer) map.get("numero_casas");
		String decifrado = decifrador.decifrar(cifrado, numeroCasas);
		String sha1 = DigestUtils.shaHex(decifrado.toString());
		
		map.put("numero_casas", numeroCasas);
		map.put("decifrado", decifrado);
		map.put("resumo_criptografico",sha1);
		
		String json = mapper.writeValueAsString(map);
		
		arquivo.atualizarArquivo(json, "answer.json");
		
		requisicao.post(urlPost, "answer.json");
	}
}
