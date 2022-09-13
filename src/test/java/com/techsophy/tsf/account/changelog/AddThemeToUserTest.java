package com.techsophy.tsf.account.changelog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import com.techsophy.tsf.account.entity.UserPreferencesDefinition;
import org.bson.types.Binary;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddThemeToUserTest {

    @Mock
    ObjectMapper objectMapper;
    @Mock
    MongoTemplate mongoTemplate;

    @InjectMocks
    AddThemeToUser addThemeToUser;

    @Test
    void changeSetFormDefinitionTest() throws IOException, ParseException {
        UserPreferencesDefinition userPreferencesDefinition = new UserPreferencesDefinition(BigInteger.ONE,BigInteger.ONE,BigInteger.ONE,new Binary(new byte[2]));
        Mockito.when(objectMapper.readValue(any(InputStream.class), ArgumentMatchers.eq(UserPreferencesDefinition.class))).thenReturn(userPreferencesDefinition);
        addThemeToUser.changeSetFormDefinition();
        Mockito.verify(objectMapper,Mockito.times(1)).readValue(any(InputStream.class), ArgumentMatchers.eq(UserPreferencesDefinition.class));
    }
}
