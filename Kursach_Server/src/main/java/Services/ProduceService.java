package Services;

import DataAccessObjects.ProduceDao;
import Interfaces.Service;
import Models.Entities.Produce;

import java.util.List;

public class ProduceService implements Service<Produce> {
    private ProduceDao produceDao = new ProduceDao();

    @Override
    public Produce findEntity(int id) {
        Produce produce = produceDao.findById(id);
        return produce;
    }

    @Override
    public void saveEntity(Produce entity) {
        produceDao.save(entity);
    }

    @Override
    public void deleteEntity(Produce entity) {
        produceDao.delete(entity);
    }

    @Override
    public void updateEntity(Produce entity) {
        produceDao.update(entity);
    }

    @Override
    public List<Produce> findAllEntities() {
        return produceDao.findAll();
    }
}
