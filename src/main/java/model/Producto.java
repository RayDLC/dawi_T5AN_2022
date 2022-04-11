package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_productos")
@Data
public class Producto {
	
	@Id
	private String id_prod;
	private String des_prod;
	private int stk_prod;
	private double pre_prod;
	
	@ManyToOne
	@JoinColumn(name = "idcategoria", insertable = false, updatable = false)
	Categoria categorias;
	private int idcategoria;
	
	private int est_prod;
	
	@ManyToOne
	@JoinColumn(name = "idprovedor", insertable = false, updatable = false)
	Proveedor proveedores;
	private int idprovedor;
	
}
