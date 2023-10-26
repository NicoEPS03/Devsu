package com.devsu.prueba.repository;

import com.devsu.prueba.entity.Cuenta;
import com.devsu.prueba.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMovimientoRepo  extends JpaRepository<Movimiento, Integer> {

    @Query(value = "SELECT m.* FROM Movimiento m WHERE (m.fecha  >= TO_DATE(:has, 'YYYY-MM-DD')  AND m.fecha <= TO_DATE(:des, 'YYYY-MM-DD')) And m.numero_cuenta = ANY (SELECT c.numero_cuenta FROM Cuenta c WHERE c.cliente_id = :clienteId) ",  nativeQuery = true)
    public List<Movimiento> buscarEstadoCuenta(@Param("des") String des, @Param("has")String has, @Param("clienteId") int clienteId);
}
