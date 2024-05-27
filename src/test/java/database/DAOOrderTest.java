package database;

import com.example.technologydevicemanagement.model.Device;
import com.example.technologydevicemanagement.model.Order;
import com.example.technologydevicemanagement.util.DBUtil;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DAOOrderTest {
    DAOOrder orderDao = new DAOOrder();
    String testOrderId;



    @AfterAll
    void tearDown() {
        if (testOrderId != null) {
            orderDao.delete(testOrderId);
        }
    }

    @Test
    void insert() {
        testOrderId = orderDao.insert();
        assertNotNull(testOrderId );

        Order fetchedOrder = orderDao.getById(testOrderId);
        assertNotNull(fetchedOrder);
        assertEquals(testOrderId, fetchedOrder.getIdOrder());
    }

    @Test
    void update() {
        String orderId = orderDao.insert();
        Order order = orderDao.getById(orderId);
        assertNotNull(order);

        Date newDate = Date.valueOf(LocalDate.now().minusDays(1));
        order.setInvoiceDate(newDate);
        orderDao.update(order);

        Order updatedOrder = orderDao.getById(orderId);
        assertEquals(newDate, updatedOrder.getInvoiceDate());

        orderDao.delete(orderId);
    }

    @Test
    void delete() {
        String orderId = orderDao.insert();
        orderDao.delete(orderId);

        Order order = orderDao.getById(orderId);
        assertNull(order);
    }

    @Test
    void getById() {
        String orderId = orderDao.insert();
        Order order = orderDao.getById(orderId);
        assertNotNull(order);
        assertEquals(orderId, order.getIdOrder());

        orderDao.delete(orderId);
    }

    @Test
    void getAll() {
        assertNotNull(orderDao.getAll());
       System.out.println(orderDao.getAll());
    }

    @Test
    void getTIByDate() {
        String orderId = orderDao.insert();
        Date today = Date.valueOf(LocalDate.now());

        double ti = orderDao.getTIByDate(today);
        assertTrue(ti >= 0);

        orderDao.delete(orderId);
    }

    @Test
    void getTIByMonth() {
        String orderId = orderDao.insert();
        Date today = Date.valueOf(LocalDate.now());

        double ti = orderDao.getTIByMonth(today);
        assertTrue(ti >= 0);

        orderDao.delete(orderId);
    }

    @Test
    void getTIByYear() {
        String orderId = orderDao.insert();
        Date today = Date.valueOf(LocalDate.now());

        double ti = orderDao.getTIByYear(today);
        assertTrue(ti >= 0);

        orderDao.delete(orderId);
    }

    @Test
    void getTI() {
        String orderId = orderDao.insert();

        double ti = orderDao.getTI();
        assertTrue(ti >= 0);

        orderDao.delete(orderId);
    }


}
