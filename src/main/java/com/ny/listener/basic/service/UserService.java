package com.ny.listener.basic.service;

import com.ny.listener.basic.entity.UserEntity;
import com.ny.listener.basic.publisher.Publisher;
import com.ny.listener.basic.repo.UserRepository;
import com.ny.listener.listener.listener.TransactionCompletionAdapter;
import com.ny.listener.listener.listener.TransactionCompletionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UserService {

    @Autowired
    Publisher publisher;
    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserService.class);


    @Transactional
    public void save(String name) {

        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userRepository.save(userEntity);
    }

    @Transactional
    public void getUser(String name) {
        UserEntity byName = userRepository.findByName(name);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> this.log("log info : " + name));
    }

    @Transactional
    public void getUserOld(String name) {
        UserEntity byName = userRepository.findByName(name);

        TransactionCompletionManager.register(TransactionCompletionAdapter.afterCommit(
                () -> this.log("after commit log : " + name))
                .async());
    }



    @Transactional
    public void remove(long id) {
        publisher.publishRunnableEvent(() -> this.log("hopefully event rollbacked after exception occur"));
        Optional<UserEntity> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new RuntimeException("You have not right to passive a user");
        }
    }

    @Transactional
    public void removeWithOld(Long id) {
        TransactionCompletionManager.register(TransactionCompletionAdapter.afterRollback(() -> this.log("hopefully event rollbacked after exception occur"))
                .withTransaction()
                .async());
        Optional<UserEntity> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new RuntimeException("You have not right to passive a user");
        }
    }

    public void log(String desc) {
        try {
            Thread.sleep(1000);
        }catch ( InterruptedException e) {
            logger.error(e.getMessage());
        }
        logger.error("time : {} , desc : {}", new Timestamp(System.currentTimeMillis()), desc);
        //MDC.getCopyOfContextMap().forEach((key, value) -> logger.info("RequestContext from MDC {}, val : {}", key, value));
    }
}
