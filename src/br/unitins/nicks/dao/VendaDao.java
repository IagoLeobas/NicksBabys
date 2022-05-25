package br.unitins.nicks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.unitins.nicks.model.Categoria;
import br.unitins.nicks.model.ItemVenda;
import br.unitins.nicks.model.Produto;
import br.unitins.nicks.model.Usuario;
import br.unitins.nicks.model.Venda;

public class VendaDao implements Dao {

	public boolean inserir(Venda obj) {
		Connection con = Dao.getConnection();
		boolean deuErro = false;
		try {
			con.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO venda ");
		sql.append(" (data, idusuario) ");
		sql.append("VALUES ");
		sql.append(" (?, ?) ");

		PreparedStatement stat = null;
		try {

			stat = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			stat.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			stat.setInt(2, obj.getUsuario().getId());
			stat.execute();
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next()) {
				obj.setId(rs.getInt("idvenda"));
			}
			salvarItens(obj, con);
			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			deuErro = true;
		} finally {
			try {
				stat.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (deuErro)
			return false;

		return true;
	}

	private void salvarItens(Venda venda, Connection conn) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO itemvenda ");
		sql.append(" (quantidade, valorunit, iditem, idvenda) ");
		sql.append("VALUES ");
		sql.append(" (?, ?, ?, ?) ");

		PreparedStatement stat = null;
		for (ItemVenda item : venda.getListaItemVenda()) {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, item.getQuantidade());
			stat.setDouble(2, item.getValorUnitario());
			stat.setInt(3, item.getProduto().getId());
			stat.setInt(4, venda.getId());

			stat.execute();
		}
	}

	public boolean alterar(Venda obj) {
		return false;
	}

	public List<Venda> obterTodos(Usuario usuario) {
		Connection con = Dao.getConnection();

		List<Venda> listaVenda = new ArrayList<Venda>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  v.idvenda, ");
		sql.append("  v.data, ");
		sql.append("  v.idusuario, ");
		sql.append("  v.total ");
		sql.append("FROM ");
		sql.append("  venda v ");
		sql.append("WHERE ");
		sql.append("  v.idusuario = ? ");

		PreparedStatement stat = null;
		try {
			stat = con.prepareStatement(sql.toString());
			stat.setInt(1, usuario.getId());

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getInt("idvenda"));
				venda.setTotal(rs.getFloat("total"));
				venda.setData(rs.getDate("data").toLocalDate());
				venda.setUsuario(usuario);
				venda.setListaItemVenda(obterItensVenda(venda));

				listaVenda.add(venda);
			}
		} catch (Exception e) {
			e.printStackTrace();
			listaVenda = null;
		} finally {
			try {
				stat.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (listaVenda == null || listaVenda.isEmpty())
			return null;

		return listaVenda;
	}

	private List<ItemVenda> obterItensVenda(Venda venda) {
		Connection con = Dao.getConnection();

		List<ItemVenda> listaItem = new ArrayList<ItemVenda>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  i.iditem, ");
		sql.append("  i.idvenda, ");
		sql.append("  i.quantidade, ");
		sql.append("  i.valorunit, ");
		sql.append("  p.id, ");
		sql.append("  p.descricao, ");
		sql.append("  p.idade, ");
		sql.append("  p.cor, ");
		sql.append("  p.preco, ");
		sql.append("  p.marca, ");
		sql.append("  p.qtdestoque, ");
		sql.append("  p.categoria ");
		sql.append("FROM ");
		sql.append("  itemvenda i, ");
		sql.append("  produto p ");
		sql.append("WHERE ");
		sql.append("  p.id = i.iditem ");
		sql.append("  AND i.idvenda = ? ");

		PreparedStatement stat = null;
		try {
			stat = con.prepareStatement(sql.toString());
			stat.setInt(1, venda.getId());

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				ItemVenda item = new ItemVenda();
				item.setId(rs.getInt("iditem"));
				item.setQuantidade(rs.getInt("quantidade"));
				item.setValorUnitario(rs.getDouble("valorunit"));
				item.setProduto(new Produto());
				item.getProduto().setDescricao(rs.getString("descricao"));
				item.getProduto().setCategoria(achaCategoria(rs.getString("categoria")));
				item.getProduto().setPreco(rs.getDouble("preco"));
				item.getProduto().setMarca(rs.getString("marca"));
				item.getProduto().setIdade(rs.getString("idade"));
				item.getProduto().setCor(rs.getString("cor"));
				item.getProduto().setQtdEstoque(rs.getInt("qtdestoque"));
		
				listaItem.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			listaItem = null;
		} finally {
			try {
				stat.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (listaItem == null || listaItem.isEmpty())
			return null;

		return listaItem;
	}

	public static Categoria achaCategoria(String categoria) {

		if (categoria == Categoria.CAMISA.getLabel())
			return Categoria.CAMISA;
		else if (categoria == Categoria.CALCA.getLabel())
			return Categoria.CALCA;
		else if (categoria == Categoria.SHORT.getLabel())
			return Categoria.SHORT;
		else if (categoria == Categoria.CALÇADO.getLabel())
			return Categoria.CALÇADO;
		else if (categoria == Categoria.BONE.getLabel())
			return Categoria.BONE;
		else if (categoria == Categoria.ACESSORIO.getLabel())
			return Categoria.ACESSORIO;
		return null;
	}

	public float somaVal(Connection con, int id, Venda venda) {

		float val = 0;

		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT valorunit,quantidade ");
		sql.append(" FROM ");
		sql.append(" itemvenda ");
		sql.append(" WHERE idvenda = ");
		sql.append(id);

		PreparedStatement stat = null;

		try {
			stat = con.prepareStatement(sql.toString());
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {

				int quant = rs.getInt("quantidade");
				val += rs.getFloat("valorunit") * quant;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(val);
		return val;
	}

	public void insereVal(Venda venda) {

		Connection con = Dao.getConnection();

		StringBuffer sql = new StringBuffer();

		sql.append(" UPDATE venda ");
		sql.append(" SET total = ? ");
		sql.append(" WHERE idvenda = ");
		sql.append(venda.getId());

		PreparedStatement stat = null;

		try {
			stat = con.prepareStatement(sql.toString());
			stat.setFloat(1, somaVal(con, venda.getId(), venda));

			stat.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
