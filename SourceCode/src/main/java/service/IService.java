package service;

import java.util.List;

public interface IService<T> {
   void saveData(T model) ;
    T getDataById(String id);
    List<T> getAllData();
    void updateData(T model);
     void deleteData(String id);

}
