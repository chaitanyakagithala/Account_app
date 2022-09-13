package com.techsophy.tsf.account.changelog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import com.techsophy.tsf.account.entity.UserFormDataDefinition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddFormSystemUserTest {
    @InjectMocks
    AddFormSystemUser addFormSystemUser;
    @Mock
    ObjectMapper mockObjectMapper;
    @Mock
    MongoTemplate template;

    Map<String,Object> map = new HashMap<>();
    @BeforeEach
    void init() {
        map.put("abc", "abc");
    }

    @Test
    void SystemUserTest() {
        Assertions.assertThrows(NullPointerException.class,()->addFormSystemUser.changeSetFormDefinition());
    }

    @Test
    void changeSetFormDefinitionTest() throws IOException, ParseException {
        UserFormDataDefinition userFormDataDefinition = new UserFormDataDefinition(BigInteger.ONE,map,BigInteger.ONE,1);
        Mockito.when(mockObjectMapper.readValue(any(InputStream.class), ArgumentMatchers.eq(UserFormDataDefinition.class))).thenReturn(userFormDataDefinition);
        addFormSystemUser.changeSetFormDefinition();
        Mockito.verify(mockObjectMapper,Mockito.times(1)).readValue(any(InputStream.class), ArgumentMatchers.eq(UserFormDataDefinition.class));
    }

}
