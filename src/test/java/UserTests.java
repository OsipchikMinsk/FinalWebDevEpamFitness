import com.epam.osipchik.gym.dao.impl.*;
import com.epam.osipchik.gym.entity.abonement.Abonement;
import com.epam.osipchik.gym.entity.abonement.AbonementType;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.service.UserAuthService;
import com.epam.osipchik.gym.service.impl.ServiceException;
import com.epam.osipchik.gym.service.impl.UserServiceImpl;
import com.epam.osipchik.gym.service.impl.ServiceFactory;
import com.epam.osipchik.gym.utils.AbonementUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class UserTests {

        //todo singleton
   // private UserDaoImpl userDao = new UserDaoImpl();
   // private AbonementTypeDao abonementTypeDao = new AbonementTypeDaoImpl();
    //private AbonementDao abonementDao = new AbonementDaoImpl();
    private AbonementUtils abonementUtils = new AbonementUtils();
    private UserAuthService authorizationService = new UserServiceImpl();
    //UserDataValidator userDataValidator = new UserDataValidator();

    DaoFactory daoFactory = DaoFactory.getInstance();
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    @Test
    public void userDbTest() throws DaoException {
        User user = new User();
        user.setName("tesasdtName");
        user.setSurname("tesasdtSurname");
        user.setEmail("tasdest@email.com");
     if (  daoFactory.getUserDao().createUser(user)!= null) {
            System.out.println("YYra");
        }

    }
    @Test
    public void getUserByID() throws DaoException {
        User user = daoFactory.getUserDao().getUser(41);
        System.out.println(user.getId() + user.getName());
    }
    @Test
    public void getUserByEmail() throws DaoException {
        String email="osipchikartem@gmail.com";
        User user = daoFactory.getUserDao().getUserByEmail(email);
        System.out.println(user.getEmail() + " " + user.getName());

    }
    @Test
    public void deleteUser() throws DaoException {
        User user = daoFactory.getUserDao().getUser(40);
        daoFactory.getUserDao().deleteUser(user);
    }


    @Test
    public void abonementWithUserTest() throws InterruptedException, DaoException {

        User user = new User();
        user.setName("testName");
        user.setSurname("testSurname");
        user.setEmail("test@email.com");
        if (daoFactory.getUserDao().createUser(user) != null) {
            AbonementType abonementType = new AbonementType();
            abonementType.setName("testAbonType");
            abonementType.setPrice(100);
            if (daoFactory.getAbonementTypeDao().create(abonementType) != null) {
                Abonement abonement = new Abonement();
                abonement.setUserId(user.getId());
                abonement.setTypeId(abonementType.getId());
                abonement.setOrderDate(abonementUtils.getSqlDate(new Date()));
                abonement.setStartDate(abonementUtils.getSqlDate(new Date()));
                abonement.setFinishDate(abonementUtils.calculateFinishDate(new java.util.Date()));
                for (int i = 0; i < 11; i++) {
                    int abonementCount = daoFactory.getAbonementDao().getAbonementsCountByUserId(user.getId());
                    BigDecimal totalPrice = abonementUtils.calculateTotalPrice(abonementType.getPrice(), abonementCount);
                    abonement.setTotalPrice(totalPrice);
                    daoFactory.getAbonementDao().create(abonement);

                }
            }

        }





    }

    @Test
    public void userWithPasswordDbTest() throws DaoException {
        User user = new User();
        user.setName("testNameWithPass");
        user.setSurname("testSurnameWithPass");
        user.setEmail("testWithPass@email.com");
        if (daoFactory.getUserDao().createUser(user) != null) {
            String pass = "pass";
            daoFactory.getUserDao().setUserPassHash(user.getId(), pass);
        };

    }

    @Test
    public void updateUserPassTest() throws DaoException {

        daoFactory.getUserDao().setUserPassHash( 25L, "asdasdasdad");
    }

    @Test
    public void isAuthorizedTest() throws DaoException, ServiceException {
        Long userId = 25L;
        User user = daoFactory.getUserDao().getUser(userId);

        String passHash = authorizationService.convertPasswordToHash("123");
        System.out.println(passHash);
        daoFactory.getUserDao().setUserPassHash(userId, passHash);
        user = authorizationService.authorize(user, "123");
        if (user.getName() != null) {
            System.out.println("User " + user.getName() + " is authorized");
        } else {
            System.out.println("NO AUTH ");
        }
    }
    /*@Test
    public void isEmailValid(){
        String email = "osipchik@tut.by";
        System.out.println( UserDataValidator.isValidEmail(email));
    }*/

}
