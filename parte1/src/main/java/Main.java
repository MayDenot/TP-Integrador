import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;
import DTO.ClienteDTO;
import DTO.ProductoDTO;
import factory.AbstractFactory;
import utils.HelperMySQL;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        HelperMySQL helperMySQL = new HelperMySQL();

        helperMySQL.dropTables();
        helperMySQL.createTables();
        helperMySQL.populateDB();
        helperMySQL.closeConnection();

        AbstractFactory factory = AbstractFactory.getAbstractFactory(1);
        ClienteDAO clienteDAO = factory.getClienteDAO();
        FacturaDAO facturaDAO = factory.getFacturaDAO();
        ProductoDAO productoDAO = factory.getProductoDAO();
        Factura_ProductoDAO factura_productoDAO = factory.getFPDAO();

        System.out.println("Producto que más recaudó: ");
        ProductoDTO productoDTO = productoDAO.getTopProductDTO();
        System.out.println(productoDTO);

        System.out.println("////////////////////////////////////////////////////////////////////");

        System.out.println("Lista de clientes ordenados por facturacion: ");
        List<ClienteDTO> clientesDTO = clienteDAO.getClientsBilled();
        System.out.println(clientesDTO);


    }
}