package com.epam.osipchik.gym.service.impl;

import com.epam.osipchik.gym.dao.AbonementDao;
import com.epam.osipchik.gym.dao.AbonementTypeDao;
import com.epam.osipchik.gym.dao.ExerciseDao;
import com.epam.osipchik.gym.dao.UserExerciseDao;
import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.dao.impl.DaoFactory;
import com.epam.osipchik.gym.entity.abonement.Abonement;
import com.epam.osipchik.gym.entity.abonement.AbonementType;
import com.epam.osipchik.gym.entity.exercise.Exercise;
import com.epam.osipchik.gym.entity.exercise.UserExercise;
import com.epam.osipchik.gym.service.AbonementService;
import com.epam.osipchik.gym.utils.AbonementUtils;

import java.util.*;


public class AbonementServiceImp implements AbonementService {

    private final AbonementTypeDao abonementTypeDao = DaoFactory.getInstance().getAbonementTypeDao();
    private final ExerciseDao exerciseDao = DaoFactory.getInstance().getExerciseDao();
    private final AbonementDao abonementDao = DaoFactory.getInstance().getAbonementDao();
    private final UserExerciseDao userExerciseDao = DaoFactory.getInstance().getUserExerciseDao();


    @Override
    public Map<String, Object> getAbonementData(Long abonementTypeId) throws DaoException {
        Map<String, Object> abonementModel = new HashMap();
        AbonementType abonementType = abonementTypeDao.getAbonemenTypetById(abonementTypeId);
        abonementModel.put("name", abonementType.getName());
        abonementModel.put("price", abonementType.getPrice());
        abonementModel.put("exers", exerciseDao.getExercisesByAbonementType(abonementTypeId));
        return abonementModel;
    }

    @Override
    public List<Map<String, Object>> getAllAbonementsTypeData() throws DaoException {
        List<Map<String, Object>> abonementsData = new ArrayList<>();
        abonementTypeDao.getAllAbonementsTypeData().forEach(abonementType -> {
            Map<String, Object> abonement = new HashMap<>();
            try {
                abonement.put("typeId", abonementType.getId());
                abonement.put("name", abonementType.getName());
                abonement.put("price", abonementType.getPrice());
                abonement.put("exers", exerciseDao.getExercisesByAbonementType(abonementType.getId()));
            } catch (DaoException e) {
                e.printStackTrace();
            }
            abonementsData.add(abonement);
        });
        return abonementsData;
    }

    @Override
    public Map<String, Object> buyAbonementByAbonementTypeId(Long abonementTypeId) throws DaoException {
        Map<String, Object> abonementData = getAbonementData(abonementTypeId);

        String name = (String) abonementData.get("name");
        Integer rawPrice = (Integer) abonementData.get("price");
        List<Exercise> exercises = (ArrayList) abonementData.get("exers");
                //todo Взять пользователя из сесии
        Long userId = 85L;
        Abonement abonement = new Abonement();
        abonement.setUserId(userId);
        abonement.setTypeId(abonementTypeId);

        Date date = new Date();
        abonement.setStartDate(AbonementUtils.getSqlDate(date));
        abonement.setOrderDate(AbonementUtils.getSqlDate(date));
        abonement.setFinishDate(AbonementUtils.calculateFinishDate(date));

        int count = abonementDao.getAbonementsCountByUserId(userId);
        abonement.setTotalPrice(AbonementUtils.calculateTotalPrice(rawPrice, count));

        System.out.println("gog og og ogo CREATE ABONEMENT");
        abonementDao.create(abonement);

        List<UserExercise> userExercises = new ArrayList<>();
        exercises.forEach(exercise -> {
            try {
                UserExercise userExercise = new UserExercise();
                userExercise.setUserId(userId);
                userExercise.setExerciseId(exercise.getId());
                userExercise.setApproved(false);
                userExercise.setDone(false);
                userExercises.add(userExerciseDao.createUserExercise(userExercise));
            } catch (DaoException e) {
                e.printStackTrace();
            }
        });
        return prepareModel(name, abonement, userExercises);
    }

    private Map<String, Object> prepareModel(String name, Abonement abonement, List<UserExercise> exercises) {
        Map<String, Object> abonementModel = new HashMap<>();
        abonementModel.put("name", name);
        abonementModel.put("finalPrice", abonement.getTotalPrice());
        abonementModel.put("startDate", abonement.getStartDate());
        abonementModel.put("orderDate", abonement.getOrderDate());
        abonementModel.put("finishDate", abonement.getFinishDate());
        abonementModel.put("userExercises", exercises);
        return abonementModel;
    }

}
