package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCódigo;
	private JComboBox cboCategorias;
	private JComboBox cboProveedores;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JLabel lblEstado;
	private JTextField txtEstado;
	private JLabel lblIdProveedor;
	private JTextField txtProveedor;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnBuscar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}	
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 223, 414, 166);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		txtSalida.setFont(new Font("Monospaced", Font.BOLD, 13));
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 393, 89, 23);
		contentPane.add(btnListado);
		
		txtCódigo = new JTextField();
		txtCódigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCódigo);
		txtCódigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 123, 110, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 127, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 73, 102, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 70, 77, 20);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 98, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 95, 77, 20);
		contentPane.add(txtPrecio);
		{
			lblEstado = new JLabel("Estado");
			lblEstado.setBounds(10, 164, 49, 14);
			contentPane.add(lblEstado);
		}
		{
			txtEstado = new JTextField();
			txtEstado.setBounds(122, 156, 96, 20);
			contentPane.add(txtEstado);
			txtEstado.setColumns(10);
		}
		{
			lblIdProveedor = new JLabel("Id. Proveedor");
			lblIdProveedor.setBounds(10, 198, 49, 14);
			contentPane.add(lblIdProveedor);
		}
		
		{
			btnActualizar = new JButton("Actualizar");
			btnActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnActualizar(e);
				}
			});
			btnActualizar.setBounds(324, 63, 89, 23);
			contentPane.add(btnActualizar);
		}
		{
			btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnEliminar(e);
				}
			});
			btnEliminar.setBounds(324, 94, 89, 23);
			contentPane.add(btnEliminar);
		}
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBuscar(e);
			}
		});
		btnBuscar.setBounds(218, 10, 89, 23);
		contentPane.add(btnBuscar);
		
		cboProveedores = new JComboBox();
		cboProveedores.setBounds(122, 195, 110, 20);
		contentPane.add(cboProveedores);
		
		llenaCombo();
	}

	void llenaCombo() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		TypedQuery<Categoria> consulta = em.createQuery("select c from Categoria c", Categoria.class);
		List<Categoria> lstCategorias = consulta.getResultList();

		cboCategorias.addItem("Seleccione...");
		for (Categoria c : lstCategorias) {
			cboCategorias.addItem(c.getIdcategoria() + " - " +c.getDescripcion());
		}
		
		TypedQuery<Proveedor> consulta2 = em.createQuery("select c from Proveedor c", Proveedor.class);
		List<Proveedor> lstProveedores = consulta2.getResultList();

		cboProveedores.addItem("Seleccione...");
		for (Proveedor p : lstProveedores) {
			cboProveedores.addItem(p.getIdprovedor() + " - " +p.getNombre_rs());
		}
		em.close();
		
	}
	
	void listado() {
		txtSalida.setText("");
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		TypedQuery<Producto> consulta =  em.createQuery("select p from Producto p", Producto.class);
		List<Producto> listadoProducto = consulta.getResultList();
		
		for (Producto p : listadoProducto) {
			imprimir("\n" + "============================" + "\n");
			imprimir("ID Producto:\t" + p.getId_prod() + "\n");
			imprimir("Descripcion:\t" + p.getDes_prod() + "\n");
			imprimir("Stock:\t\t" + p.getStk_prod() + "\n");
			imprimir("Precio:\t\tS/." + p.getPre_prod() + "\n");
			imprimir("ID Categoria:\t" + p.getIdcategoria() + "\n");
			imprimir("Nombre Cat:\t" + p.getCategorias().getDescripcion() + "\n");
			imprimir("Estado:\t\t " + p.getEst_prod() + "\n");
			imprimir("ID Proveedor:\t " + p.getIdprovedor() + "\n");
			imprimir("Nombre Prov:\t" + p.getProveedores().getNombre_rs());
			
		}
		em.close();
	}
	
	void registrar() {
		
		Producto p = new  Producto();
		
		p.setId_prod(leeIdProducto());
		p.setDes_prod(leeDescripcion());
		p.setStk_prod(leeStock());
		p.setPre_prod(leePrecio());
		p.setIdcategoria(leeCategoria());
		p.setEst_prod(leerEstado());
		p.setIdprovedor(leeIdProveedor());
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			txtSalida.setText("Producto Registrado con EXITO!");
			limpiarCajas();
		} catch(Exception e) {
			txtSalida.setText("Producto NO Registrado-->ERROR: " + e.getMessage());
			//em.getTransaction().rollback();
		}
		em.close();
	}
	
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		Producto u = em.find(Producto.class, leeIdProducto());
		if (u==null)
			txtSalida.setText("Producto NO existe...");
		else
			txtCódigo.setText(u.getId_prod());
			txtDescripcion.setText(u.getDes_prod());
			txtStock.setText(""+ u.getStk_prod());
			txtPrecio.setText("" + u.getPre_prod());
			cboCategorias.setSelectedIndex(u.getIdcategoria());
			txtEstado.setText("" + u.getEst_prod());
			cboProveedores.setSelectedIndex(u.getIdprovedor());
			txtSalida.setText("Producto Encontrado con Exito!");
		em.close();
	}
	
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		
		Producto p = new  Producto();
		
		p.setId_prod(leeIdProducto());
		p.setDes_prod(leeDescripcion());
		p.setStk_prod(leeStock());
		p.setPre_prod(leePrecio());
		p.setIdcategoria(leeCategoria());
		p.setEst_prod(leerEstado());
		p.setIdprovedor(leeIdProveedor());
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(p);
			em.getTransaction().commit();
			txtSalida.setText("Producto Actualizado con EXITO!");
			limpiarCajas();
		} catch(Exception a) {
			txtSalida.setText("Producto NO Actualizado-->ERROR: " + a.getMessage());
			//em.getTransaction().rollback();
		}
		em.close();
		
	}
	
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		em.getTransaction().begin();
		
		Producto u = new Producto();
		u.setId_prod(leeIdProducto());
		
		try {
			em.remove(em.contains(u) ? u : em.merge(u));
			em.getTransaction().commit();
			txtSalida.setText("Producto Eliminado con EXITO");
			limpiarCajas();
		} catch(Exception a) {
			txtSalida.setText("Producto NO Eliminado-->ERROR: " + a.getMessage());
			//em.getTransaction().rollback();
		}
		em.close();
	}
	
	private String leeIdProducto() {
		return txtCódigo.getText();
	}
	
	private String leeDescripcion() {
		return txtDescripcion.getText();
	}
	
	private int leeStock() {
		return Integer.parseInt(txtStock.getText());
	}
	
	private double leePrecio() {
		return Double.parseDouble(txtPrecio.getText());
	}
	
	private int leeCategoria() {
		return cboCategorias.getSelectedIndex();
	}
	
	private int leerEstado() {
		return Integer.parseInt(txtEstado.getText());
	}
	
	private int leeIdProveedor() {
		return cboProveedores.getSelectedIndex();
	}
	
	private void limpiarCajas() {
		txtCódigo.setText("");
		txtDescripcion.setText("");
		txtStock.setText("");
		txtPrecio.setText("");
		cboCategorias.setSelectedIndex(0);
		txtEstado.setText("");
		cboProveedores.setSelectedIndex(0);
	}
	
	private void imprimir(String cadena) {
		txtSalida.append("" + cadena);
	}

}












