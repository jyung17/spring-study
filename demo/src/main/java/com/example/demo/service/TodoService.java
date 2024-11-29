package com.example.demo.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TodoService {

  @Autowired
  private TodoRepository repository;

  public String testService() {
    TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
    repository.save(entity);
    TodoEntity saveEntity = repository.findById(entity.getId()).get();
    return saveEntity.getTitle();
  }

  public List<TodoEntity> create(final TodoEntity entity) {
    validate(entity);
    repository.save(entity);

    log.info("Entity Id: {} is saved.", entity.getId());

    return repository.findByUserId(entity.getUserId());
  }

  private void validate(final TodoEntity entity) {
    if(entity == null) {
      log.warn("Entity cannot be null.");
      throw new RuntimeException("Entity cannot be null.");
    }

    if(entity.getUserId() == null) {
      log.warn("Unknown user.");
      throw new RuntimeException("Unknown user.");
    }
  }

  public List<TodoEntity> retrieve(final String userId) {
    return repository.findByUserId(userId);
  }

  public List<TodoEntity> update(final TodoEntity entity) {
    validate(entity);

    // (2) 넘겨받은 엔티티 id를 이용해 TodoEntity 를 가져온다. 존재하지 않는 엔티티는 업데이트 할 수 없기 때문
    final Optional<TodoEntity> original = repository.findById(entity.getId());

    original.ifPresent(todo -> {
      // (3) 반환된 TodoEntity 가 존재하면 값을 새 entity 의 값으로 덮어 씌운다.
      todo.setTitle(entity.getTitle());
      todo.setDone(entity.isDone());

      // (4) 데이터베이스에 새 값을 저장한다.
      repository.save(todo);
    });

    return retrieve(entity.getUserId());
  }

  public List<TodoEntity> delete(final TodoEntity entity) {
    validate(entity);

    try {
      repository.delete(entity);
    } catch(Exception e) {
      log.error("error deleting entity", entity.getId(), e);

      throw new RuntimeException("error deleting entity " + entity.getId());
    }

    return retrieve(entity.getUserId());
  }
}
