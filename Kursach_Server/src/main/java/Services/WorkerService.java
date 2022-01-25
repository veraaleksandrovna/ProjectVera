package Services;

import DataAccessObjects.WorkerDao;
import Interfaces.Service;
import Models.Entities.Batch;
import Models.Entities.Produce;
import Models.Entities.Worker;

import java.util.List;
import java.util.Set;

public class WorkerService implements Service<Worker> {
    private WorkerDao workerDao = new WorkerDao();

    @Override
    public Worker findEntity(int id) {
        Worker worker = workerDao.findById(id);
        return worker;
    }

    @Override
    public void saveEntity(Worker entity) {
        workerDao.save(entity);
    }

    @Override
    public void deleteEntity(Worker entity) {
        workerDao.delete(entity);
    }

    @Override
    public void updateEntity(Worker entity) {
        workerDao.update(entity);
    }

    @Override
    public List<Worker> findAllEntities() {
        return workerDao.findAll();
    }
}
