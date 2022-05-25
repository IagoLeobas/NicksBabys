package br.unitins.nicks.report;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;

import br.unitins.nicks.application.JDBCUtil;

@WebServlet("/produtoreportservlet")
public class ProdutoReportServlet extends ReportServlet {

	private static final long serialVersionUID = -2694551539315919270L;

	@Override
	public String getArquivoJasper() {
		return "Produto.jasper";
	}

	@Override
	public HashMap<String, Class<?>> getParametros() {
		HashMap<String, Class<?>> param = new HashMap<String, Class<?>>();
		param.put("categoria", Integer.class);
		return param;
	}

	@Override
	public Connection getConnection() {
		return JDBCUtil.getConnection();
	}

}