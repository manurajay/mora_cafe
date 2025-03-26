package com.mora.booking.repo;

import com.mora.booking.pojo.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepo  extends JpaRepository<Consumer, Integer> {

    Consumer findConsumerById(int consumerId);
}
