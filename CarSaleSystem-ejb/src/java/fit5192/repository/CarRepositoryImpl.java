/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.repository;

import fit5192.repository.entities.Car;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Administrator
 */
@Stateless
public class CarRepositoryImpl implements CarRepository{
    private static final String PERSISTENCE_UNIT = "CarSaleSystemPU";
    
    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private EntityManager entityManager;
    
    //private final List<Car> cars;
    
    public CarRepositoryImpl(){
        //cars = new ArrayList<>();
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    
    @Override
    public void addCar(Car car) throws Exception {
        System.out.println("*************************** addCar executed");
        entityManager.persist(car);
//        EntityTransaction transaction = entityManager.getTransaction();
//        try{
//            transaction.begin();
//            entityManager.persist(car);
//            transaction.commit();
//        } catch(Exception e){
//            transaction.rollback();
//        }
    }

    @Override
    public Car searchCarById(Long id) throws Exception {
//        for(Car car : this.cars){
//            if(car.getCarId() == id){
//                return car;
//            }
//        }
//        return null;
        return entityManager.find(Car.class, id);
    }

    @Override
    public List<Car> getAllCars() throws Exception {
//        List<Car> cars = new ArrayList<>(this.cars.size());
//        for(Car car : this.cars){
//            cars.add(new Car(car));
//        }
//        return cars;
        return entityManager.createNamedQuery("Car.findAll").getResultList();
        
    }
    
    @Override
    public List<Car> getCarsByModelNoAndModelNameAndMakerAndType(int modelNo, String modelName, String maker, String type) throws Exception{
//        List<Car> carList = new ArrayList<Car>();
//        for(Car car : this.cars){
//            if(car.getModelNo()==modelNo && car.getModelName().equals(modelName) && car.getMaker().equals(maker) && car.getType().equals(type)){
//                carList.add(car);
//            }
//        }
//        if(!carList.isEmpty())   return carList;
//        else    return null;
//        Query query = entityManager.createNamedQuery("Car.findByModelNoAndModelNameAndMakerAndType");
//        query.setParameter(param, type)
//        return entityManager.createNamedQuery("Car.findByModelNoAndModelNameAndMakerAndType").getResultList();
          TypedQuery<Car> q = entityManager.createQuery("SELECT c FROM Car c WHERE c.modelNo='"+modelNo+"' AND c.modelName='"+modelName+"' AND c.maker='"+maker+"' AND c.type='"+type+"'", Car.class);
          return q.getResultList();
          
    }
    
    @Override
    public List<Car> getCarsByModelNo(int modelNo) throws Exception{
        TypedQuery<Car> q = entityManager.createQuery("SELECT c FROM Car c WHERE c.modelNo='"+modelNo+"'", Car.class);
        return q.getResultList();
    }
    
    @Override
    public List<Car> getCarsByModelName(String modelName) throws Exception{
        TypedQuery<Car> q = entityManager.createQuery("SELECT c FROM Car c WHERE c.modelName='"+modelName+"'", Car.class);
        return q.getResultList();
    }
    
    @Override
    public List<Car> getCarsByMaker(String maker) throws Exception{
        TypedQuery<Car> q = entityManager.createQuery("SELECT c FROM Car c WHERE c.maker='"+maker+"'", Car.class);
        return q.getResultList();
    }
    
    @Override
    public List<Car> getCarsByType(String type) throws Exception{
        TypedQuery<Car> q = entityManager.createQuery("SELECT c FROM Car c WHERE c.type='"+type+"'", Car.class);
        return q.getResultList();
    }
    
    @Override
    public List<Car> getCarsBySellState(String sellState) throws Exception {
        TypedQuery<Car> q = entityManager.createQuery("SELECT c FROM Car c WHERE c.sellState='"+sellState+"'", Car.class);
        return q.getResultList();
    }
    
    @Override
    public void deleteCar(Long carId) throws Exception{
        Car car = searchCarById(carId);
        if(car != null){
            entityManager.remove(car);
        }
    }

    @Override
    public void updateCar(Car car) throws Exception {
        System.out.println("*************************************");
//        Long carId = car.getCarId();
//        int modelNo = car.getModelNo();
//        String VIN = car.getVIN();
//        String modelName = car.getModelName();
//        String maker = car.getMaker();
//        String type = car.getType();
//        String description = car.getDescription();
//        String previewUrl = car.getPreviewUrl();
//        String sellState = car.getSellState();
//        TypedQuery<Car> q = entiryManager.createQuery("UPDATE Car c SET c.modelNo='"+modelNo+"' WHERE u.userreportPK.userId='"+userId+"' AND u.userreportPK.date='"+date+"'", Car.class);
//        q.executeUpdate();
            if(car != null){
                System.out.println("dhedadf");
                entityManager.merge(car);
            }
    }

    @Override
    public void updateSellStateById(Long id, String sellState) throws Exception {
        TypedQuery<Car> q = entityManager.createQuery("UPDATE Car c SET c.sellState='"+sellState+"' WHERE c.carId='"+id+"'", Car.class);
        q.executeUpdate();
    }

    
}

