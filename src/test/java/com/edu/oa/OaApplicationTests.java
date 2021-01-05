package com.edu.oa;

import com.edu.oa.mdo.CourseDo;
import com.edu.oa.mdo.LeaveInfoDo;
import com.edu.oa.mdo.TemplateInfo;
import com.edu.oa.util.CommonInfo;
import com.edu.oa.util.SwapAreaUtils;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class OaApplicationTests {
	@Resource
	private SqlSessionTemplate sessionTemplate;
	@Resource
	private CourseDo mdo;

	@Test
	void contextLoads() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		java.util.Date date = new java.util.Date();
		System.out.println(simpleDateFormat.format(date));
		java.sql.Date dateSql = new java.sql.Date(date.getTime());
		System.out.println(simpleDateFormat.format(dateSql));
	}
	@Test
	void testMdo(){
		//CourseDo mdo = new CourseDo();
		mdo.setStudentNo("202000100101");
		List<CourseDo> list = mdo.getCourseDoByStudentNo();
		System.out.println(list.get(0).getCourseName() + "-" + list.get(0).getTeacherName());
	}
	@Test
	public void test1(){
		System.out.println(sessionTemplate);
	}
	@Test
	public void test2(){
		ExpressionParser parser = new SpelExpressionParser();//创建解析器
		Expression expression = parser.parseExpression("(1 <= new Integer(days)) and (new Integer(days) < 5)");
		LeaveInfoDo mdo = new LeaveInfoDo();
		mdo.setDays("2");
//		new Integer("");
		boolean o = expression.getValue(mdo,boolean.class);
		System.out.println(o);
	}
	@Test
	public void test3(){
		CommonInfo commonInfo = SwapAreaUtils.getCommonInfo();
		TemplateInfo templateInfo = new TemplateInfo();
		templateInfo.setTplNo("1");
		commonInfo.setTemplateInfo(templateInfo);

		CommonInfo commonInfo1 = SwapAreaUtils.getCommonInfo();
		System.out.println(commonInfo1.getTemplateInfo().getTplNo());
	}
	@Test
	public void test4(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String s = format.format(new Date());
		System.out.println(s);
	}
	@Test
	public void test5(){
		String defaultSequence = "00000";
		String sequence = "12";
		defaultSequence = defaultSequence.substring(0 ,5 - sequence.length());
		String concat = defaultSequence.concat(sequence);
		System.out.println(defaultSequence);
		System.out.println(concat);
	}
	@Test
	public void test6(){
		String a = "00000100";
		String b = "00001010";
		System.out.println(a.indexOf("1"));
		System.out.println(b.indexOf("1"));
	}

}
