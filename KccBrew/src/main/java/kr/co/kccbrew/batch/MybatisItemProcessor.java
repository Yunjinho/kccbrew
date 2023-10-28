package kr.co.kccbrew.batch;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.asMng.service.AsMngService;
import kr.co.kccbrew.asMng.service.IAsMngService;
import kr.co.kccbrew.comm.util.DateFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
public class MybatisItemProcessor implements ItemProcessor<AsLogVo, AsMngVo>{
	private static Logger logger = LoggerFactory.getLogger(MybatisItemProcessor.class);
	
	AsMngService asMngService = new AsMngService();
	DateFormat dateFormat = new DateFormat();
	
	@Override
	public AsMngVo process(AsLogVo asLogVo) throws Exception {
		System.out.println("MybatisItemProcessor.process");
		String asStatus = asLogVo.getAsStatus();
		if (asStatus.equals("01")) {
			System.out.println("asStatus is 1");
			AsMngVo asMngVo = new AsMngVo();
			String asInfoSeq = asLogVo.getAsInfoSeq();
			asMngVo.setAsInfoSeq(asInfoSeq);
			asMngVo = asMngService.selectASList(asMngVo, 1).get(0);

			// 날짜경과확인
			Date wishingEndDate = dateFormat.stringToSqlDate(asMngVo.getWishingEndDate());
			Calendar calendar = Calendar.getInstance();
			Date currentDate = new Date(calendar.getTime().getTime());

			if (wishingEndDate.before(currentDate)) {
				asMngVo.setIsOmitted("Y");
			} else {
				asMngVo.setIsOmitted("N");
			}
			asMngVo.setOmissionCheckDttm(currentDate);
			logger.info("AS상태= " + asStatus + ", AS희망종료일= " + wishingEndDate +  ",누락여부= " + asMngVo.getIsOmitted() + ", 확인일= " +  currentDate);
			return asMngVo;
		}
		return null;
	}

}
