package onlineStuManagerSystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import com.sun.tools.apt.Main;
import com.web.biz.TeacherBiz;
import com.web.biz.impl.TeacherBizImpl;
import com.web.dao.TeacherDao;
import com.web.dao.impl.TeacherDaoImpl;
import com.web.entity.Teacher;
import com.web.util.PageUtil;
import com.web.util.StringUtil;

public class test {
	@Test
	public void testInt() {
		
		String str="1.0";
		Integer i=Integer.parseInt(str.substring(0, str.indexOf(".")));
		System.out.println(i);
	}
	

}
