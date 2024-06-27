
package VentasOnline.Ventas.Repository;

import VentasOnline.Ventas.Entity.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {
    @Query("SELECT df FROM DetalleFactura df WHERE df.id = :id")
    Optional<DetalleFactura> findDetalleFacturaById(@Param("id") Long id);
}