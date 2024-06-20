package br.dev.rodrigopinheiro.btgpactual.ordems.repository;

import br.dev.rodrigopinheiro.btgpactual.ordems.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    Page<OrderEntity> findAllByCustomerId(Long customerId, PageRequest pageRequest);
}
