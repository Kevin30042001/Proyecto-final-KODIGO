package VentasOnline.Ventas.Repository;
import VentasOnline.Ventas.Entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Factura.
 */
@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

}

