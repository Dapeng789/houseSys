package sys.qx.controller;

import java.io.File;



import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import sys.qx.dao.C03TableUserDaoMapper;
import sys.qx.dao.C15TableBeanDaoMapper;
import sys.qx.dao.HKBuFenDaoMapper;
import sys.qx.dao.HKSuoYouDaoMapper;
import sys.qx.model.C03TableUser;
import sys.qx.model.C15TableBean;
import sys.qx.model.HKBuFen;
import sys.qx.model.HKSuoYou;
import sys.qx.model.UtilDate;

//客户回款数据Controller

@Controller
@RequestMapping("/c15HuiKuanTableController")
public class C15HuiKuanTableController {
	
	
	@Autowired
	private  C15TableBeanDaoMapper  c15TableBeanDaoMapper;
	 
	//定义C03表对象
	@Autowired
	private  C03TableUserDaoMapper c03TableUserDaoMapper;
	
	@Autowired
	private  HKSuoYouDaoMapper  hKSuoYouDaoMapper;

	@Autowired
	private  HKBuFenDaoMapper hKBuFenDaoMapper;
	/**
	 *    
	   		(页面：导入C15 excel  文件)    点击导入按钮后 后台执行以下功能：

             1： -----》首先将 （已还款客户）部分还款客户表，C15先清空，确保每次在表中查看到的都是本次C15表中的数据。 
			
			 2： -----》后台读取C15表中的每一条数据，将每一条数据都加上时间戳。
			
			
			 3： -----》后台读取C15表中的每一条数据，去和C03表中的房间代码进行关联。
			      
			3.1： -----》关联上的，表示为已回款客户，并计算已欠款金额，如				欠款金额和放款金额一致，表示此客户已放款，将此条				数据保存到已放款客户中，再将此条数据从C03表中删				除。
			
			
				3.2 ：-----》如果欠款金额  大于放款金额，表示只放一部分款，将				此条数据保存到“部分还款客户表中”，再将C03表中				的放款金额更新，公式为(C03欠款总金额 -  C15放款				金额 = 剩余欠款)，将剩余欠款更新到C03表中。
			     
			
			        3.3：------》没有关联上的，表示不是自己的客户，作为无效客户数				据，(因对业务没有任何价值)直接删除。
			
			(返回页面)    4：------》已上计算完成后，后台查询最近5周的客户，并计算这五周回款客户的总金额，再将计算出来的总金额，以及客户数据  展示到页面上。
			
			(页面查询：)	5：------》提供按时间查询指定范围内的 回款数据，
			                      系统接收到时间值 ，根据值去数据库中查询指定时间段的回款数据，并计算出这个时间段的总金额，返回到页面，供用户查看 和导出。
			
			可在“按时间查询已回款数据” 功能中查看指定时间范围内的数据，有Excel数据导出功能。
			系统默认展示 5周 的回款数据。
			
			可在 “已还部分欠款客户表”中查看本次的 已还部分欠款的客户，本功能只保留本次的
			C15客户。
	 */
	
	
	/**
	 * updeta C15excel table  method
	 * @throws IOException 
	 */
	@RequestMapping("/putC15TableData.do")
	@ResponseBody
	public  String  putC15TableData(@RequestParam MultipartFile file) throws IOException {
		
		System.out.println("Hello   Word----------------");
/*		//获得文件名
    	String fileName = file.getOriginalFilename();
		
		
		//String result = request.getParameter("formdata");
		
		System.out.println("已经执行后台方法！！！！！！！！");
		boolean FLAG;// 身份状态

		List<C15TableBean> list = new ArrayList<C15TableBean>();
		HSSFWorkbook workbook = null;
		 
		try {
			//获取excel文件的io流
			InputStream is = file.getInputStream();
			//根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
			if(fileName.endsWith("xls")){
				//2003
				workbook = new HSSFWorkbook(is);
			}else if(fileName.endsWith(xlsx)){
				//2007
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
 
		
		
		// 把MultipartFile转化为File
		CommonsMultipartFile cmf = (CommonsMultipartFile) file;
		DiskFileItem dfi = (DiskFileItem) cmf.getFileItem();
		File fo = dfi.getStoreLocation();

		System.out.println(fo);
		// 创建Excel，读取文件内容
		workbook = new HSSFWorkbook(FileUtils.openInputStream(fo));
		//workbook = new XSSFWorkbook(FileUtils.openInputStream(fo));
		
		
		// 获取第一个工作表
				String fileName1 = dfi.getName();
				String [] excelFileName = fileName1.split("\\.");
				workbook.
				XSSFSheet sheet = workbook.getSheetAt(0);

				// 获取sheet中第一行行号
				int firstRowNum = sheet.getFirstRowNum();
				System.out.println(firstRowNum);
				// 获取sheet中最后一行行号
				int lastRowNum = sheet.getLastRowNum();
				System.out.println(lastRowNum +"   最后一行");
				
        		try {
        			// 循环插入数据
        			for (int i = firstRowNum + 1; i <= lastRowNum; i++) {

        				XSSFRow row = sheet.getRow(i);

        				C15TableBean c15ExcelTableBean = new C15TableBean();

        				
        				 * users.setClientid(clientid); users.setAdddate(date);
        				 * users.setStatus(true);//默认为启用状态
        				 
        				
        				XSSFCell xuhao = row.getCell(0);
        				
        				if(xuhao == null) {
            				c15ExcelTableBean.setXuhao(null);
        				}else {
        					xuhao.setCellType(Cell.CELL_TYPE_STRING);
            				c15ExcelTableBean.setXuhao((xuhao.getStringCellValue()));
        				}
*/        				
		
		//获得文件名
    	String fileName = file.getOriginalFilename();
		
		//String result = request.getParameter("formdata");
		
		System.out.println("已经执行后台方法！！！！！！！！");
		boolean FLAG;// 身份状态

		List<C15TableBean> list = new ArrayList<C15TableBean>();
		Workbook workbook = null;
		
		// 把MultipartFile转化为File
		CommonsMultipartFile cmf = (CommonsMultipartFile) file;
		DiskFileItem dfi = (DiskFileItem) cmf.getFileItem();
		File fo1 = dfi.getStoreLocation();

		System.out.println(fo1);
		// 创建Excel，读取文件内容
		workbook = new HSSFWorkbook(FileUtils.openInputStream(fo1));
		if(workbook != null){
    			//获得当前sheet工作表
        		Sheet sheet = workbook.getSheetAt(0);
        		 
        		//获得当前sheet的开始行
        		int firstRowNum  = sheet.getFirstRowNum();
        		//获得当前sheet的结束行
        		int lastRowNum = sheet.getLastRowNum();
        		System.out.println("-------lastRowNum----------->  "+lastRowNum);
        		//循环除了第一行的所有行
        		System.out.println(lastRowNum +"   最后一行");
        		
        		try {
        			// 循环插入数据
        			for (int i = firstRowNum + 1; i <= lastRowNum; i++) {

            			Row row = sheet.getRow(i);

        				C15TableBean c15ExcelTableBean = new C15TableBean();

        				/*
        				 * users.setClientid(clientid); users.setAdddate(date);
        				 * users.setStatus(true);//默认为启用状态
        				 */
        				
        				Cell xuhao = row.getCell(0);
        				
        				
        				if(xuhao == null) {
            				c15ExcelTableBean.setXuhao(null);
        				}else {
        					xuhao.setCellType(Cell.CELL_TYPE_STRING);
            				c15ExcelTableBean.setXuhao((xuhao.getStringCellValue()));
        				}
        				

		
        				
        				Cell quyu  = row.getCell(1);
        				if(quyu == null) {
        					c15ExcelTableBean.setQuyu(null);
        				}else {
        					quyu.setCellType(Cell.CELL_TYPE_STRING);
            				c15ExcelTableBean.setQuyu((quyu.getStringCellValue()));
        				}
        				
        				
        				Cell shengfen  = row.getCell(2);
        				if(shengfen == null) {
        					c15ExcelTableBean.setShengfen(null);
        				}else {
        					shengfen.setCellType(Cell.CELL_TYPE_STRING);
            				c15ExcelTableBean.setShengfen((shengfen.getStringCellValue()));
        				}
        				
        				
        				
        				Cell objectName  = row.getCell(3);
        				if(objectName == null) {
        					c15ExcelTableBean.setObjectName(null);
        				}else {
        				objectName.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setObjectName((objectName.getStringCellValue()));
        				}
        				
        				Cell fangjiandaima = row.getCell(4); 
        				if(fangjiandaima == null) {
        					c15ExcelTableBean.setFangjiandaima(null);
        				}else {
        				fangjiandaima.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setFangjiandaima((fangjiandaima.getStringCellValue()));
        				}
        				
        				Cell  addressUrl  = row.getCell(5);
        				if(addressUrl == null) {
        					c15ExcelTableBean.setAddressUrl(null);
        				}else {
        				addressUrl.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setAddressUrl(addressUrl.getStringCellValue());
        				}
        				
        				Cell  zongjia  = row.getCell(6);
        				if(zongjia == null) {
        					c15ExcelTableBean.setZongjia(null);
        				}else {
        				zongjia.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setZongjia((zongjia.getStringCellValue()));
        				}
        				
        				Cell  yingqianyueriqi  = row.getCell(7);
        				if(yingqianyueriqi == null) {
        					c15ExcelTableBean.setYingqianyueriqi(null);
        				}else {
        				yingqianyueriqi.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setYingqianyueriqi((yingqianyueriqi.getStringCellValue()));
        				}
        				
        				Cell qianyueriqi  = row.getCell(8);
        				if(qianyueriqi == null) {
        					c15ExcelTableBean.setQianyueriqi(null);
        				}else {
        				qianyueriqi.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setQianyueriqi((qianyueriqi.getStringCellValue()));
        				}
        				
        				Cell  fukuanfangshi  = row.getCell(9);
        				if(fukuanfangshi == null) {
        					c15ExcelTableBean.setFukuanfangshi(null);
        				}else {
        				fukuanfangshi.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setFukuanfangshi((fukuanfangshi.getStringCellValue()));
        				}
        				
        				Cell anjieyinhang  = row.getCell(10);
        				if(anjieyinhang == null) {
        					c15ExcelTableBean.setAnjieyinhang(null);
        				}else {
        				anjieyinhang.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setAnjieyinhang((anjieyinhang.getStringCellValue()));
        				}
        				
        				Cell  yinhangfenlei  = row.getCell(11);
        				if(yinhangfenlei == null) {
        					c15ExcelTableBean.setYinhangfenlei(null);
        				}else {
        				yinhangfenlei.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setYinhangfenlei((yinhangfenlei.getStringCellValue()));
        				}
        				
        				
        				//银行细分
        				Cell  yinhangxifen  = row.getCell(12);
        				if(yinhangxifen == null) {
        					c15ExcelTableBean.setYinhangxifen(null);
        				}else {
        					yinhangxifen.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setYinhangxifen((yinhangxifen.getStringCellValue()));
        				}
        				
        				
        				
        				Cell caoqianriqi  = row.getCell(13);
        				if(caoqianriqi == null) {
            				c15ExcelTableBean.setCaoqianriqi(null);
        				}else {
        					caoqianriqi.setCellType(Cell.CELL_TYPE_STRING);
            				c15ExcelTableBean.setCaoqianriqi((caoqianriqi.getStringCellValue()));
        				}
        				
        				
        				
        				Cell zhengshiqianyueriqi  = row.getCell(14);
        				if(zhengshiqianyueriqi == null) {
        					c15ExcelTableBean.setZhengshiqianyueriqi(null);
        				}else {
        				zhengshiqianyueriqi.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setZhengshiqianyueriqi((zhengshiqianyueriqi.getStringCellValue()));
        				}
        				
        				Cell  anjiejine  = row.getCell(15);
        				if(anjiejine == null) {
        					c15ExcelTableBean.setAnjiejine(null);
        				}else {
        				anjiejine.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setAnjiejine((anjiejine.getStringCellValue()));
        				}
        				
        				Cell  fangkanriqi  = row.getCell(16);
        				if(fangkanriqi == null) {
        					c15ExcelTableBean.setFangkanriqi(null);
        				}else {
        				fangkanriqi.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setFangkanriqi((fangkanriqi.getStringCellValue()));
        				}
        				
        				Cell  kehumingcheng  = row.getCell(17);
        				if(kehumingcheng == null) {
        					c15ExcelTableBean.setKehumingcheng(null);
        				}else {
        				kehumingcheng.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setKehumingcheng((kehumingcheng.getStringCellValue()));
        				}
        				
        				Cell  yewuyuan  = row.getCell(18);
        				if(yewuyuan == null) {
        					c15ExcelTableBean.setYewuyuan(null);
        				}else {
        				yewuyuan.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setYewuyuan((yewuyuan.getStringCellValue()));
        				}
        				
        				Cell  	dainajinjine = row.getCell(19);
        				if(dainajinjine == null) {
        					c15ExcelTableBean.setDainajinjine(null);
        				}else {
        				dainajinjine.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setDainajinjine((dainajinjine.getStringCellValue()));
        				}
        				
        				Cell  	anjieleixing = row.getCell(20);
        				if(anjieleixing == null) {
        					c15ExcelTableBean.setAnjieleixing(null);
        				}else {
        				anjieleixing.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setAnjieleixing((anjieleixing.getStringCellValue()));
        				}
        				
        				Cell  	fangkuanjine = row.getCell(21);
        				if(fangkuanjine == null) {
        					c15ExcelTableBean.setFangkuanjine(null);
        				}else {
        				fangkuanjine.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setFangkuanjine((fangkuanjine.getStringCellValue()));
        				}
        				
        				Cell   gongjijinjine = row.getCell(22);
        				if(gongjijinjine == null) {
        					c15ExcelTableBean.setGongjijinjine(null);
        				}else {
        				gongjijinjine.setCellType(Cell.CELL_TYPE_STRING);
        				c15ExcelTableBean.setGongjijinjine((gongjijinjine.getStringCellValue()));
        				}
        				
        				
        				//new一个当前时间
        				SimpleDateFormat  date = new  SimpleDateFormat("yyyy-MM-dd");
        				String newDate = date.format(new Date());
        				c15ExcelTableBean.setLurudate(newDate);
        				
        				//标识
        				c15ExcelTableBean.setAuthority(UtilsUser.getBiaoshi());
        				
        				
        				list.add(c15ExcelTableBean);
        			}
        			
        			//先将C15表清空
        			System.out.println("UtilsUser.getBiaoshi()"+UtilsUser.getBiaoshi());
        			c15TableBeanDaoMapper.qingKongC15(UtilsUser.getBiaoshi());
        			System.out.println("C15表清空");
        			
        			//将已还款部分表清空
        			c15TableBeanDaoMapper.qingkongHuanKuan();
        			System.out.println("还款部分表清空");

        			
        			//将已还款表清空
        			c15TableBeanDaoMapper.qingKongBufuHuanKuan();
        			System.out.println("还款表清空");

        			
        			
        			//插入本次C15表中的数据到数据库
        			/* System.out.println(list); */
        			System.out.println("List的长度是："+list.size());
        			c15TableBeanDaoMapper.saveC15TableExcelData(list);
        			System.out.println("插入本次C15表中的数据到数据库");
        			//将list清空
        			list = null;
        			
        			
        			
        			

        			
        			//读取后台C15中的每一条数据，和C03表中的数据关联（房间代码） ，如有就查询出 房间代码，金额 字段，计算放款金额是否和	欠款金额一致，
        			//一致  表示此用户银行已经全部放款，就把此条用户保存到 已放款客户表中，再将此条数据从C03表中删除，
        			//欠款金额 大于放款金额  表示银行只放部分贷款，（将已放款金额更新到C03表中），并将此条数据保存到  部分还款客户中。
        			//没关联上的，表示不是自己的客户，直接删除。
        			
        			
        			 // 3： -----》后台读取C15表中的每一条数据，去和C03表中的房间代码进行关联。
        			
        			
        			//读取C15中的所有数据  去和C03表中的数据进行关联 
        			List<C15TableBean> c15BeanList = c15TableBeanDaoMapper.getC15TableExcelData();
				/* System.out.println("c15BeanList 的长度是：-------->"+c15BeanList.size()); */
        			
        			for(int i=0;i<c15BeanList.size();i++){
        				C15TableBean c15Bean = c15BeanList.get(i);
        				C03TableUser c03TableUser1 = new C03TableUser();
        				//C15房间代码
        				String fjdm = c15Bean.getFangjiandaima();
        				System.out.println("房间代码是："+fjdm);
        				//去C03表中查询  看是否有此条用户
        				c03TableUser1.setTheRoomNumber(c15Bean.getFangjiandaima());
        				c03TableUser1.setAuthority(UtilsUser.getBiaoshi());
        				
        				List<C03TableUser> c03User = c03TableUserDaoMapper.getC03User(c03TableUser1);
        				
        				//查询出来的  表示是已回款数据，
            			if(c03User.size()>0) {
            				
            				for(int a=0;a<c03User.size();a++){
            					System.out.println("有的编码--------》："+fjdm);
            					C03TableUser c03TableUser = c03User.get(a);
            					//获取当前C03表中的 欠款数据
            					double monetAll = c03TableUser.getMoneyAll();
            					
	            				//获取当前C15表中的放款数据，
							/* System.out.println("放款数据---》"+c15Bean.getFangkuanjine()); */
            					double fangkanJinE = Double.parseDouble(c15Bean.getFangkuanjine());
            					//匹配欠款是否和放款金额一致  一致银行一放全部款，
            					
            					List<C15TableBean> c15Beanlist = new ArrayList<C15TableBean>();
            					
            					if(monetAll >= fangkanJinE) {
            						//保存到已放款表中，再把C15表中的数据删除   c15Beanlist
            						
            						c15Beanlist.add(c15Bean);
            						hKSuoYouDaoMapper.insertHkUser(c15Beanlist);
            						//吧C15当天数据删除
            						c15TableBeanDaoMapper.deletefjbhUser(c03TableUser1);
            					}else if(monetAll > fangkanJinE) {
            						c15Beanlist.add(c15Bean);
            						//如果欠款金额  大于放款金额，表示只放一部分款，将此条数据保存到“部分还款客户表中”，再将C03表中的放款金额更新，公式为(C03欠款总金额 -  C15放款				金额 = 剩余欠款)，将剩余欠款更新到C03表中。
            						hKBuFenDaoMapper.insertBFHKUser(c15Beanlist);
            						//计算剩余还款数
            						double syqiankuanshu = (monetAll-fangkanJinE);
            						c03TableUser.setMoneyAll(syqiankuanshu);
            						//更新到C03表中  
            						c03TableUserDaoMapper.updateQKZJE(c03TableUser);
            					}
            					//保存到已放款客户表中，再将此条数据从C03表中删除。
            				}
            			}else {
            			//没查询出来的  表示不是自己的客户  直接将C15中的数据删除
            				System.out.println("没有的编码--------》："+fjdm);
            				c15TableBeanDaoMapper.deletefjbhUser(c03TableUser1);
            			}
        		    }
        		} catch (Exception e) {
        			e.printStackTrace();
        		} finally {
        			workbook = null;
        		}
			}else {
				String data ="shibai";
				return data;
		    }
		String data ="chenggong";
		return data;
	}
	
	
	/**
	 *  日期格式化方法
	 * @param cell
	 * @return
	 */
	public static String buildDate(XSSFCell cell) {
        String result = new String();
        	 switch (cell.getCellType()) {
             case XSSFCell.CELL_TYPE_NUMERIC:
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 double value = cell.getNumericCellValue();
                 Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                 result = sdf.format(date);
             }
        return result;
}
	
	
	
	
	//默认查询5周回款数据方法
	@RequestMapping("/zhouHuiKuanData")
	public  String   get5ZhouHuiKuanData(ModelMap model,HttpServletRequest request) throws ParseException {
		//Select Distinct * from (Select * from hkbufen where lurudate>='2019-04-21'  Union Select * from hksuoyou where lurudate >='2019-04-21')  tab;
		UtilDate utilDate = this.getDate();
		System.out.println(utilDate);
		//获取全 和部分回款数据  只取这五洲的
		List allUserDataList = hKSuoYouDaoMapper.getUserAllData(this.getDate());
		
		double moneyAll = 0.0;
		
		for( int i = 0 ; i < allUserDataList.size() ; i++) { 
			HKSuoYou hKSuoYou = (HKSuoYou)allUserDataList.get(i);
			//计算总金额
			moneyAll += Double.parseDouble(hKSuoYou.getFangkuanjine());
		}
		//吧数据展示到页面上，
		model.addAttribute("list",allUserDataList);
		//吧总金额展示到页面上
		model.addAttribute("moneyAll",moneyAll);
		return "c15excelTableDataView";
	}
	
	
	//定义默认查询时间方法
	public  UtilDate   getDate() throws ParseException {
		UtilDate utilDate = new UtilDate();
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
		String maxDateStr = sdf.format(new Date());
		String minDateStr = "";
		Calendar calc =Calendar.getInstance();
		calc.setTime(sdf.parse(maxDateStr));
		//35 / 7 = 5周
		calc.add(calc.DATE, -35);
		Date minDate = calc.getTime();
		minDateStr = sdf.format(minDate);
		System.out.println("minDateStr:"+minDateStr); 
		utilDate.setSrartDate(minDateStr);
		//当前时间
		SimpleDateFormat  date = new  SimpleDateFormat("yyyy-MM-dd");
		utilDate.setStropDate(date.format(new Date()));
		
		utilDate.setId(UtilsUser.getBiaoshi());
		
		return utilDate;
	}
	
	
	
	
	//按照指定时间查询
		@RequestMapping("/DateSelectDate")
		public  String   DateSelectDate(ModelMap model,HttpServletRequest request) throws ParseException {
			UtilDate utilDate = new UtilDate();
			//获取开始时间
			String startdate = request.getParameter("startdate").replace("/","-");
			utilDate.setSrartDate(startdate);
			
			//判断有没有结束时间，有结束时间就是用结束时间查询值，没有则使用当前时间查询值
			if(request.getParameter("storpdate") != "") {
				//获取结束时间
				String storpdate = request.getParameter("storpdate").replace("/","-");
				utilDate.setStropDate(storpdate);
			//没有值，则获取当前时间
			}else {
				 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				 utilDate.setStropDate(df.format(new Date()));
			}
			utilDate.setId(UtilsUser.getBiaoshi());
			
			//获取全 和部分回款数据  只取这五洲的
			List allUserDataList = hKSuoYouDaoMapper.getUserAllData(utilDate);
			double moneyAll = 0.0;
			for( int i = 0 ; i < allUserDataList.size() ; i++) { 
				HKSuoYou hKSuoYou = (HKSuoYou)allUserDataList.get(i);
				//计算总金额
				moneyAll += Double.parseDouble(hKSuoYou.getFangkuanjine());
			}
			//吧数据展示到页面上，
			model.addAttribute("list",allUserDataList);
			//吧总金额展示到页面上
			model.addAttribute("moneyAll",moneyAll);
			return "c15excelTableDataViewDate";
		}
		
		
		//查看部分还款客户
		@RequestMapping("/getBuFenHuanKuanKuanHu")
		public String  getBuFenHuanKuanKuanHu(ModelMap model,HttpServletRequest request) {
			List listBuFenAll = hKBuFenDaoMapper.getBuFenAllUser(UtilsUser.getBiaoshi());
			double moneyAll = 0.0;
			for( int i = 0 ; i < listBuFenAll.size() ; i++) { 
				HKBuFen hKBuFen = (HKBuFen)listBuFenAll.get(i);
				//计算总金额
				moneyAll += Double.parseDouble(hKBuFen.getFangkuanjine());
			}
			model.addAttribute("list",listBuFenAll);
			model.addAttribute("moneyAll",moneyAll);
			return "c15excelTableDataView";
		}

}
