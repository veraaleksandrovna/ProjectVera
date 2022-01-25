package Services;

import DataAccessObjects.StandartDao;
import Interfaces.Service;
import Models.Entities.Produce;
import Models.Entities.Standart;

import java.util.List;
import java.util.Set;

public class StandartService implements Service<Standart> {
    private StandartDao standartDao = new StandartDao();

    @Override
    public Standart findEntity(int id) {
        Standart standart = standartDao.findById(id);
       /* Set<Produce> produces = standart.getProduces();
        for (Produce produce :
                produces) {
            produce.setfWorker(null);
            produce.setfStandart(null);
            produce.setfBatch(null);
        }
        standart.setProduces(produces);*/
        return standart;
    }

    @Override
    public void saveEntity(Standart entity) {
        standartDao.save(entity);
    }

    @Override
    public void deleteEntity(Standart entity) {
        standartDao.delete(entity);
    }

    @Override
    public void updateEntity(Standart entity) {
        standartDao.update(entity);
    }

    @Override
    public List<Standart> findAllEntities() {
        return standartDao.findAll();
    }
}
