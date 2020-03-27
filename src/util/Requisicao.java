package util;

import java.io.File;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;

public class Requisicao {
	
	private Arquivo arquivo = new Arquivo();
	
	public File get(String url) {
		try {
			try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
				HttpGet httpGet = new HttpGet(url);
				try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
					HttpEntity entity = response.getEntity();
					return arquivo.gerarArquivo(entity.getContent(), "answer.json");
				} 
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void post(String urlPost, String nomeArquivo) {
		try {
			try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
				HttpPost httpPost = new HttpPost(urlPost);
				FileBody fileBody = new FileBody(new File(nomeArquivo), ContentType.APPLICATION_OCTET_STREAM);
				HttpEntity httpEntity = MultipartEntityBuilder.create()
						.addPart("answer", fileBody)
						.build();
				httpPost.setEntity(httpEntity);
				System.out.println("Executando a requisição: " + httpPost);
				try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
					System.out.println(response.getCode());
					System.out.println(response.getReasonPhrase());
					System.out.println(response);
				} 
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
