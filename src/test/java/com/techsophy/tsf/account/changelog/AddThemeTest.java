package com.techsophy.tsf.account.changelog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import com.techsophy.tsf.account.entity.ThemesDefinition;
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

import static com.techsophy.tsf.account.constants.UserFormDataConstants.ANYSTRING;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddThemeTest {
    @InjectMocks
    AddTheme addTheme;
    @Mock
    MongoTemplate template;
    @Mock
    ObjectMapper mockObjectMapper;

//    @Test
//    void SystemUserTest() throws IOException, ParseException {
//        Assertions.assertThrows(NullPointerException.class,()->addTheme.changeSetFormDefinition());
//    }

//    @Test
//    void changeSetFormDefinitionTest() throws IOException, ParseException {
//        ThemesDefinition themesDefinition = new ThemesDefinition(BigInteger.ONE,ANYSTRING,ANYSTRING);
//        Mockito.when(mockObjectMapper.readValue(any(InputStream.class), ArgumentMatchers.eq(ThemesDefinition.class))).thenReturn(themesDefinition);
//        addTheme.changeSetFormDefinition();
//        Mockito.verify(mockObjectMapper,Mockito.times(1)).readValue(any(InputStream.class), ArgumentMatchers.eq(ThemesDefinition.class));
//    }
}
