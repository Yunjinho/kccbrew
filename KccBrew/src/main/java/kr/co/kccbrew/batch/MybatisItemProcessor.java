package kr.co.kccbrew.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.asMng.service.IAsMngService;

@Component("mybatisItemProcessor")
public class MybatisItemProcessor implements ItemProcessor<AsLogVo, AsMngVo>{
    private static Logger logger = LoggerFactory.getLogger(MybatisItemProcessor.class);
    
    @Autowired
    private IAsMngService asMngService;
    
    @Override
    public AsMngVo process(AsLogVo asLogVo) throws Exception {
    	String asStatus = asLogVo.getAsStatus();
    	if (asStatus.equals("1")) {
    		
    	}
    	

        logger.info("변경 대상 가수 정보: " +   ", 변경 후: " );

        return null;
    }

}
