package kr.co.kccbrew.batch;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import kr.co.kccbrew.asMng.dao.IAsMngRepository;
import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.asMng.service.AsMngService;
import kr.co.kccbrew.asMng.service.IAsMngService;
import kr.co.kccbrew.comm.util.DateFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
public class MybatisItemProcessor implements ItemProcessor<AsLogVo, AsMngVo>{
	private static Logger logger = LoggerFactory.getLogger(MybatisItemProcessor.class);
	
	@Override
	public AsMngVo process(AsLogVo asLogVo) throws Exception {
		String asStatus = asLogVo.getAsStatus();
		if (asStatus.equals("01")) {
			AsMngVo asMngVo = new AsMngVo();
			String asInfoSeq = asLogVo.getAsInfoSeq();
			asMngVo.setAsInfoSeq(asInfoSeq);

			// 날짜경과확인
			if(asLogVo.getWishingEndDate() != null) {
			Date wishingEndDate = asLogVo.getWishingEndDate();
			Calendar calendar = Calendar.getInstance();
			Date currentDate = new Date(calendar.getTime().getTime());

			if (wishingEndDate.before(currentDate)) {
				asMngVo.setIsOmitted("Y");
			} else {
				asMngVo.setIsOmitted("N");
			}
			asMngVo.setOmissionCheckDttm(currentDate);
			logger.info("as_info_seq: " + asLogVo.getAsInfoSeq() +", AS상태= " + asStatus + ", AS희망종료일= " + wishingEndDate + ", 확인일= " +  currentDate + " ==> 누락여부: " + asMngVo.getIsOmitted());
			return asMngVo;
			}
			return null;
		}
		
		return null;
	}

}
