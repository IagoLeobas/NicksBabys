package br.unitins.nicks.application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;

import br.unitins.nicks.servlet.ImgProdutoServlet;

public class Util {

	public static void main(String[] args) {
		System.out.println(Util.hash("123").length());
	}
	
	public static String hash(String valor) {
		return DigestUtils.sha256Hex(valor);
		
	}
	
	public static void addErrorMessage(String msg) {
		addMessage(msg, FacesMessage.SEVERITY_ERROR, null);
	}
	
	public static void addErrorMessage(String msg, String clientId) {
		addMessage(msg, FacesMessage.SEVERITY_ERROR, clientId);
	}
	
	public static void addInfoMessage(String msg) {
		addMessage(msg, FacesMessage.SEVERITY_INFO, null);
	}
	
	public static void addWarnMessage(String msg) {
		addMessage(msg, FacesMessage.SEVERITY_WARN, null);
	}

	private static void addMessage(String msg, Severity severity, String clientId) {
		FacesContext.getCurrentInstance().
		addMessage(clientId, 
				new FacesMessage(severity, msg, null));
	}
	
	public static void redirect(String page) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean saveImageProduto(InputStream inputStream, String imageType, int idProduto) {
		// Exemplo da maquina do iago: /home/iago/images/usuario
		String diretorio = System.getProperty("user.home") + ImgProdutoServlet.PATH_IMAGES_PRODUTO;
		
		// Criando os diretorios caso nao exista
		File file = new File(diretorio);
		if (!file.exists()) {
			file.mkdirs(); // mkdirs - cria arquivo ou diretorio (caso o diretorio anterior nao exista ele cria tambem)
		}
		
		try {
			// criando o espaco de memoria para armazenamento de uma imagem
			// inputStream - eh o fluxo de dados de entrada 
			BufferedImage bImage = ImageIO.read(inputStream);
			
			// estrutura final do arquivo ex: /home/iago/images/produto/01.png
			File arquivoFinal = new File(diretorio + File.separator + idProduto + "." + imageType);
			// salvando a imagem
			// buffer da imagem, png/gif, 01.png
			ImageIO.write(bImage, imageType, arquivoFinal);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}