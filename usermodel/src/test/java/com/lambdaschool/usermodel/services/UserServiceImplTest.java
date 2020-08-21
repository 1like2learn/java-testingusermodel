package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.UserModelApplication;
import com.lambdaschool.usermodel.models.Role;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import com.lambdaschool.usermodel.models.Useremail;
import com.lambdaschool.usermodel.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserModelApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

//        List<User> myList = userService.findAll();
//        for (User u : myList){
//            System.out.println(u.getUserid() + " " + u.getUsername());
//        }
//        4 admin
//        7 cinnamon
//        11 barnbarn
//        13 puttat
//        14 misskitty
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void a_findUserById() {

        assertEquals("admin", userService.findUserById(4).getUsername());
    }

    @Test
    public void b_findByNameContaining() {

        assertEquals(1, userService.findByNameContaining("ad").size());
    }

    @Test
    public void c_findAll() {

        assertEquals(5, userService.findAll().size());
    }

    @Test
    public void x_delete() {

        userService.delete(13);
        assertEquals(5, userService.findAll().size());
    }

    @Test
    public void d_findByName() {

        assertEquals("admin", userService.findByName("admin").getUsername());
    }

    @Test
    public void e_save() {

        Role r2 = new Role("user");
        r2.setRoleid(2);

        User u2 = new User("tiger", "ILuvMath!", "tiger@school.lambda");
        u2.getRoles().add(new UserRoles(u2, r2));
        u2.getUseremails()
            .add(new Useremail(u2, "tiger@tiger.local"));
        User savedUser = userService.save(u2);
        assertEquals("tiger", savedUser.getUsername());
        assertEquals("tiger@school.lambda", savedUser.getPrimaryemail());
        assertEquals(1, savedUser.getRoles().size());
        assertEquals(1, savedUser.getUseremails().size());
    }

    @Transactional
    @Test
    public void f_update() {

        Role r2 = new Role("user");
        r2.setRoleid(2);

        User u2 = new User("tiger", "ILuvMath!", "tiger@school.lambda");
        u2.getRoles().add(new UserRoles(u2, r2));
        u2.getUseremails()
            .add(new Useremail(u2, "tiger@tiger.local"));
        User updatedUser = userService.update(u2, 15);
        assertEquals("tiger", updatedUser.getUsername());
        assertEquals("tiger@school.lambda", updatedUser.getPrimaryemail());
        assertEquals(1, updatedUser.getRoles().size());
        assertEquals(1, updatedUser.getUseremails().size());
    }

    @Test
    public void z_deleteAll() {
        userService.deleteAll();
        assertEquals(0, userService.findAll().size());
    }
}