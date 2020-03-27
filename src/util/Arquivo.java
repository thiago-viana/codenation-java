package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;

public class Arquivo {
	
	public File gerarArquivo(InputStream is, String nomeArquivo) {
		try {
			Reader r = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(r);
			
			FileOutputStream os = new FileOutputStream(nomeArquivo);
			OutputStreamWriter w = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(w);
			
			String linha = br.readLine();
			while (linha != null) {
				bw.write(linha);
				linha = br.readLine();
			}
			
			File arquivo = new File(nomeArquivo);
			
			br.close();
			bw.close();
			
			return arquivo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public File atualizarArquivo(String json, String nomeArquivo) {
		try {
			FileOutputStream os = new FileOutputStream(nomeArquivo);
			OutputStreamWriter w = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(w);
			bw.write(json);
			File arquivo = new File(nomeArquivo);
			bw.close();
			return arquivo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
