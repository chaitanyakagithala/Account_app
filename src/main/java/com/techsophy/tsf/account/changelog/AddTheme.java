package com.techsophy.tsf.account.changelog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import com.techsophy.tsf.account.entity.ThemesDefinition;
import com.techsophy.tsf.account.entity.UserPreferencesDefinition;
import com.techsophy.tsf.account.repository.UserPreferencesDefinitionRepository;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import static com.techsophy.tsf.account.constants.AccountConstants.*;
import static com.techsophy.tsf.account.constants.ErrorConstants.EXCEUTION_IS_FAILED;

@ChangeUnit(id = ADD_THEME, order = ORDER_3, systemVersion = SYSTEM_VERSION_1)
@AllArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class AddTheme {
    private  final MongoTemplate template;
    private final ObjectMapper objectMapper;
    private final UserPreferencesDefinitionRepository userPreferencesDefinitionRepository;

    @Execution
    public void changeSetFormDefinition() throws IOException, ParseException {
        String pathTheme = TP_USER_THEME_JSON;
        String pathThemeUser = TP_USER_THEME_JSON;
        InputStream inputStreamTest = new ClassPathResource(pathTheme).getInputStream();
        ThemesDefinition themesDefinition = objectMapper.readValue(inputStreamTest, ThemesDefinition.class);
        InputStream inputStreamTest1 = new ClassPathResource(pathThemeUser).getInputStream();
        UserPreferencesDefinition userPreferencesDefinition = objectMapper.readValue(inputStreamTest1, UserPreferencesDefinition.class);
        long count = template.getCollection(TP_THEME_COLLECTION).countDocuments();
        boolean status = userPreferencesDefinitionRepository.existsByUserId(BigInteger.valueOf(Long.parseLong("821024382412341232")));
        if(count==0&&status==true) {
            template.save(themesDefinition, TP_THEME_COLLECTION);
            template.save(userPreferencesDefinition,TP_USER_PREFERENCE_COLLECTION);
        }
     }
    @RollbackExecution
    public void rollback()
    {
        log.info(EXCEUTION_IS_FAILED);
    }

}
