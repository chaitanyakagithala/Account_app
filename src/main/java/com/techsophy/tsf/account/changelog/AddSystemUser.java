package com.techsophy.tsf.account.changelog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import com.techsophy.tsf.account.entity.UserDefinition;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.io.IOException;
import java.io.InputStream;
import static com.techsophy.tsf.account.constants.AccountConstants.*;
import static com.techsophy.tsf.account.constants.ErrorConstants.EXCEUTION_IS_FAILED;

@ChangeUnit(id = ADD_SYSTEM_USER, order = ORDER_2, systemVersion = SYSTEM_VERSION_1)
@AllArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class AddSystemUser {
    private  final MongoTemplate template;
    private final ObjectMapper objectMapper;
    public static  int count =0;
    @Execution
    public void changeSetFormDefinition() throws IOException, ParseException {
        String  pathUser =TP_SYSTEM_USER_JSON;
        InputStream inputStreamTest=new ClassPathResource(pathUser).getInputStream();
        UserDefinition userDefinition = objectMapper.readValue(inputStreamTest,UserDefinition.class);
        String id = String.valueOf(userDefinition.getId());
        Query query = new Query();
        query.addCriteria(Criteria.where(UNDERSCORE_ID).is(id));
        if(template.find(query,UserDefinition.class).size()==0) {
            template.save(userDefinition, TP_USER_COLLECTION);
        }
    }
    @RollbackExecution
    public void rollback()
    {
        log.info(EXCEUTION_IS_FAILED);
    }
}
