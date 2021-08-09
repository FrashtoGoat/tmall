package com.xiaoluban.demo;

import com.xiaoluban.demo.mapstruct.Person;
import com.xiaoluban.demo.mapstruct.PersonConverter;
import com.xiaoluban.demo.mapstruct.PersonDTO;
import com.xiaoluban.demo.mapstruct.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: TXB
 * @Date: 2021/4/30 16:43
 * @Description: *
 */

public class MapStructTest {

    @Test
    public void test() {
        Person person = new Person(1L,"zhige","zhige.me@gmail.com",new Date(),new User(1));
        PersonDTO personDTO = PersonConverter.INSTANCE.domain2dto(person);
        System.out.println(personDTO);

//            List<Person> people = new ArrayList<>();
//            people.add(person);
//            List<PersonDTO> personDTOs = PersonConverter.INSTANCE.domain2dto(people);
    }
}
