package VentasOnline.Ventas.Service;
import VentasOnline.Ventas.Entity.Promocion;
import VentasOnline.Ventas.Repository.PromocionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PromocionServiceImpl implements PromocionService {
    @Autowired
    private PromocionRepository promocionRepository;

    @Override
    public List<Promocion> obtenerTodasLasPromociones() {
        return promocionRepository.findAll();
    }

    @Override
    public Promocion obtenerPromocionPorId(Long id) {
        return promocionRepository.findById(id).orElse(null);
    }

    @Override
    public Promocion guardarPromocion(Promocion promocion) {
        return promocionRepository.save(promocion);
    }

    @Override
    public Promocion actualizarPromocion(Promocion promocion) {
        return promocionRepository.save(promocion);
    }

    @Override
    public void eliminarPromocionPorId(Long id) {
        promocionRepository.deleteById(id);
    }
}

