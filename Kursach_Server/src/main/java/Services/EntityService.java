package Services;

import Models.Entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EntityService<T> {
    final Class<T> ClassType;
    private BatchService batchService = new BatchService();
    private StandartService standartService = new StandartService();
    private WorkerService workerService = new WorkerService();
    private ProduceService produceService = new ProduceService();
    private UserService userService = new UserService();

    public EntityService(Class<T> ClassType) {

        this.ClassType = ClassType;
    }

    public boolean AddEntity(T entity) {
        try {
            if (User.class.equals(ClassType)) {
                User user = (User) entity;
                userService.saveEntity(user);
            }
            if (Produce.class.equals(ClassType)) {
                Produce produce = (Produce) entity;
                produce.setSeriesWorkersWorkerId(workerService.findEntity(produce.getSeriesWorkersWorkerId().getWorkerId()));
                produce.setBaseProductsBaseProductId(standartService.findEntity(produce.getBaseProductsBaseProductId().getBaseProductId()));
                produce.setSeriesSeriesId(batchService.findEntity(produce.getSeriesSeriesId().getSeriaId()));
                produceService.saveEntity(produce);
            }
            if (Worker.class.equals(ClassType)) {
                Worker worker = (Worker) entity;
                workerService.saveEntity(worker);
            }
            if (Batch.class.equals(ClassType)) {
                Batch batch = (Batch) entity;
                batch.setUsersUserId(userService.findEntity(batch.getUsersUserId().getUserId()));
                batch.setWorkersWorkerId(workerService.findEntity(batch.getWorkersWorkerId().getWorkerId()));
                batchService.saveEntity(batch);
            }
            if (Standart.class.equals(ClassType)) {
                Standart standart = (Standart) entity;
                standartService.saveEntity(standart);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean UpdateEntity(T entity) {
        try {
            if (User.class.equals(ClassType)) {
                User user = (User) entity;
                userService.updateEntity(user);
            }
            if (Produce.class.equals(ClassType)) {
                Produce produce = (Produce) entity;
                produceService.updateEntity(produce);
            }
            if (Worker.class.equals(ClassType)) {
                Worker worker = (Worker) entity;
                workerService.updateEntity(worker);
            }
            if (Batch.class.equals(ClassType)) {
                Batch batch = (Batch) entity;
                batchService.updateEntity(batch);
            }
            if (Standart.class.equals(ClassType)) {
                Standart standart = (Standart) entity;
                standartService.updateEntity(standart);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public T GetEntity(int id) {
        if (User.class.equals(ClassType)) {
            return (T) userService.findEntity(id);
        }
        if (Produce.class.equals(ClassType)) {
            return (T) produceService.findEntity(id);
        }
        if (Worker.class.equals(ClassType)) {
            return (T) workerService.findEntity(id);
        }
        if (Batch.class.equals(ClassType)) {
            return (T) batchService.findEntity(id);
        }
        if (Standart.class.equals(ClassType)) {
            return (T) standartService.findEntity(id);
        }
        return null;
    }

    public boolean DeleteEntity(T entity) {
        try {
            if (User.class.equals(ClassType)) {
                User user = (User) entity;
                user = userService.findEntity(user.getUserId());
                userService.deleteEntity(user);
            }
            if (Produce.class.equals(ClassType)) {
                Produce produce = (Produce) entity;
                produce = produceService.findEntity(produce.getProductId());
                produceService.deleteEntity(produce);
            }
            if (Worker.class.equals(ClassType)) {
                Worker worker = (Worker) entity;
                worker = workerService.findEntity(worker.getWorkerId());
                if (Worker.class.equals(ClassType)) {
                    workerService.deleteEntity(worker);
                }
            }
            if (Batch.class.equals(ClassType)) {
                Batch batch = (Batch) entity;
                batch = batchService.findEntity(batch.getSeriaId());
                batchService.deleteEntity(batch);
            }
            if (Standart.class.equals(ClassType)) {
                Standart standart = (Standart) entity;
                standart = standartService.findEntity(standart.getBaseProductId());
                standartService.deleteEntity(standart);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<T> GetEntities() {
        if (User.class.equals(ClassType)) {
            List<User> users = new ArrayList<>();
            for (User user :
                    userService.findAllEntities()) {
                users.add(userService.findEntity(user.getUserId()));
            }
            return (List<T>) users;
        }
        if (Produce.class.equals(ClassType)) {
            List<Produce> produces = new ArrayList<>();
            for (Produce produce :
                    produceService.findAllEntities()) {
                produces.add(produceService.findEntity(produce.getProductId()));
            }
            return (List<T>) produces;
        }
        if (Worker.class.equals(ClassType)) {
            List<Worker> workers = new ArrayList<>();
            for (Worker worker :
                    workerService.findAllEntities()) {
                workers.add(workerService.findEntity(worker.getWorkerId()));
            }
            return (List<T>) workers;
        }
        if (Batch.class.equals(ClassType)) {
            List<Batch> batches = new ArrayList<>();
            for (Batch batch :
                    batchService.findAllEntities()) {
                batches.add(batchService.findEntity(batch.getSeriaId()));
            }
            return (List<T>) batches;
        }
        if (Standart.class.equals(ClassType)) {
            List<Standart> standarts = new ArrayList<>();
            for (Standart standart :
                    standartService.findAllEntities()) {
                standarts.add(standartService.findEntity(standart.getBaseProductId()));
            }
            return (List<T>) standarts;
        }
        return null;
    }
}
