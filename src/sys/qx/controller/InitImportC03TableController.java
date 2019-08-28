package sys.qx.controller;


import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import sys.qx.dao.C03TableNewUserDaoMapper;
import sys.qx.dao.C03TableUserDaoMapper;
import sys.qx.dao.C03WuXiaoUserDaoMapper;
import sys.qx.dao.JDCQCaoQianUserDaoMapper;
import sys.qx.dao.JDCQNoFuKuanUserDaoMapper;
import sys.qx.model.C03TableUser;
import sys.qx.model.HKSuoYou;
import sys.qx.model.UtilDate;
 

//初始导入，循环购买Controller
@Controller
@RequestMapping("/initImportC03TableController")
public class InitImportC03TableController {
	
	@Autowired
	private C03TableNewUserDaoMapper c03TableNewUserDaoMapper;
	
	@Autowired
	private C03TableUserDaoMapper c03TableUserDaoMapper;
	
	@Autowired
	private C03WuXiaoUserDaoMapper c03WuXiaoUserDaoMapper;
	
	@Autowired
	private JDCQCaoQianUserDaoMapper jDCQCaoQianUserDaoMapper;
	
	@Autowired
	private  JDCQNoFuKuanUserDaoMapper jDCQNoFuKuanUserDaoMapper;
	
	
	/**
	 * 规则：每一个功能   都做成一个单独的方法，通过调用来实现功能
	 * 
	 */
	//1 导入C03 Excel文件   按照获取的Excel文件中的  “房间号码” 去数据库中匹配用户方法，没有就调用公共 新增方法
	
	@RequestMapping("importC03ExcelTable")
	public  void  importC03ExcelTable(@RequestParam MultipartFile file) {
		
	}
	
	
	
	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * 
	 * 
	 * (页面：导入C03excel   文件)    点击导入按钮后 后台执行以下功能：
			
			1：----》后台取每一条客户数据 在每条客户数据上 加上导入时间戳，再去数据库查询：
			
			1.1：----》如查询到，表示已有客户，只更新客户签约状态，修改为10%，不再进行保存。
			
			1.2：----》如果没有查询到，表示是新增客户，增加到C03表中。
			
			1.3：----》将另一张newUser表中的数据清空，再将新客户保存到newUser表中，清空表是为了只保存本次的新增客户数据，保存原因是检查这次新增加了多少客户。
			

	 */
	@ResponseBody
	@RequestMapping("/importUsers.do")
	//public String importUsers(@RequestParam MultipartFile file, Integer clientid, HttpServletRequest request)
	public String importUsers(@RequestParam MultipartFile file,ModelMap model,HttpServletRequest request)throws IOException {
		
		System.out.println("已经执行后台方法！！！！！！！！");
		boolean FLAG;// 身份状态

		List list = new ArrayList();
		XSSFWorkbook workbook = null;

		// 把MultipartFile转化为File
		CommonsMultipartFile cmf = (CommonsMultipartFile) file;
		DiskFileItem dfi = (DiskFileItem) cmf.getFileItem();
		File fo = dfi.getStoreLocation();

		// 创建Excel，读取文件内容
		workbook = new XSSFWorkbook(FileUtils.openInputStream(fo));

		// 获取第一个工作表
		String fileName = dfi.getName();
		String [] excelFileName = fileName.split("\\.");
		XSSFSheet sheet = workbook.getSheetAt(0);

		// 获取sheet中第一行行号
		int firstRowNum = sheet.getFirstRowNum();
		System.out.println(firstRowNum);
		// 获取sheet中最后一行行号
		int lastRowNum = sheet.getLastRowNum();
		System.out.println(lastRowNum +"   最后一行");
		try {
			// 循环插入数据
			for (int i = firstRowNum + 1; i <lastRowNum; i++) {

				if(i<3) {
					continue;
				}
				XSSFRow row = sheet.getRow(i);

				C03TableUser excelData = new C03TableUser();

				/*
				 * users.setClientid(clientid); users.setAdddate(date);
				 * users.setStatus(true);//默认为启用状态
				 */

				XSSFCell area = row.getCell(0);// 所属区域
				area.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setArea((area.getStringCellValue()));
				//excelData.setArea((new String(area.getStringCellValue().getBytes(), "UTF-8")));
			   // System.out.println("-------"+new String(area.getStringCellValue().getBytes("ISO-8859-1"),"utf-8"));
			    
			    
			    
			    System.out.println("-----csdfsdfsd--"+area.getStringCellValue());
			   
				XSSFCell areaPresident = row.getCell(1);// 区域总裁
				areaPresident.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setAreaPresident((areaPresident.getStringCellValue()));

				XSSFCell building = row.getCell(2);// 楼盘
				building.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setBuilding((building.getStringCellValue()));

				XSSFCell paymentMethod = row.getCell(3);// 付款方式
				paymentMethod.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setPaymentMethod((paymentMethod.getStringCellValue()));

				XSSFCell theRoomNumber = row.getCell(4);// 房间编号
				theRoomNumber.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setTheRoomNumber((theRoomNumber.getStringCellValue()));
				System.out.println(theRoomNumber.getStringCellValue()+"----------- 房间编号-----");

				XSSFCell park = row.getCell(5);// 苑区
				park.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setPark((park.getStringCellValue()));

				XSSFCell url = row.getCell(6);// 路址
				url.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setUrl((url.getStringCellValue()));

				XSSFCell ownerName = row.getCell(7);// 业主名称
				ownerName.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setOwnerName((ownerName.getStringCellValue()));

				
				
				
				// 认购价
				
				
				XSSFCell subscriptionPrice = row.getCell(8);// 认购价
				subscriptionPrice.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setSubscriptionPrice(subscriptionPrice.getStringCellValue());
				
				System.out.println(subscriptionPrice.getStringCellValue());
				
				
				
				
				
				

				// 应交款项
				// 应交款项

				XSSFCell fund = row.getCell(9);// 房间编号
				fund.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setFund((fund.getStringCellValue()));

				
				// 推售日期
				XSSFCell sellingDate = row.getCell(10);
				String sellingDate1 = buildDate(sellingDate);
				//System.out.println(date);
				//sellingDate.setCellType(Cell.CELL_TYPE_STRING);
				//System.out.println(sellingDate.getStringCellValue());
				excelData.setSellingDate(sellingDate1);
				
				
				
				
				// 预售证日期
				XSSFCell prePinDate = row.getCell(11);
				String prePinDate1 = buildDate(prePinDate);
				System.out.println(prePinDate1);
				//prePinDate.setCellType(Cell.CELL_TYPE_STRING);
				//System.out.println(prePinDate.getStringCellValue());
				excelData.setPrePinDate(prePinDate1);

				
				// 竣工备案日期
				XSSFCell closeoutDate = row.getCell(12);
				String closeoutDate1 = buildDate(closeoutDate);
				System.out.println(closeoutDate1);
				
				//closeoutDate.setCellType(Cell.CELL_TYPE_STRING);
				//System.out.println(closeoutDate.getStringCellValue());
				excelData.setCloseoutDate(closeoutDate1);

				// 应交日期
				XSSFCell deliveryDate = row.getCell(13);
				String deliveryDate1 = buildDate(deliveryDate);
				System.out.println(deliveryDate1);
				
				//deliveryDate.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setDeliveryDate(deliveryDate1);

				// 到期应交金额
				XSSFCell deliveryOughtToMathod = row.getCell(14);
				deliveryOughtToMathod.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setDeliveryOughtToMathod((deliveryOughtToMathod.getStringCellValue()));

				// 到期已交金额
				XSSFCell deliveryYetMathod = row.getCell(15);
				deliveryYetMathod.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setDeliveryYetMathod((deliveryYetMathod.getStringCellValue()));

				
				
				
				
				// 到期欠交金额
				XSSFCell deliveryDebtMathod = row.getCell(16);
				deliveryDebtMathod.setCellType(Cell.CELL_TYPE_STRING);
				
				//到期欠交金额
				double daoQiMoney = Double.parseDouble(deliveryDebtMathod.getStringCellValue());
				
				/*excelData.setDeliveryDebtMathod(Double.parseDouble(deliveryDebtMathod.getStringCellValue()));*/
				excelData.setDeliveryDebtMathod(daoQiMoney);
				
				
				
				
				

				// 未到期应交金额
				XSSFCell noDeliveryOughtToMathod = row.getCell(17);
				noDeliveryOughtToMathod.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setNoDeliveryOughtToMathod((noDeliveryOughtToMathod.getStringCellValue()));

				// 未到期已交金额
				XSSFCell noDeliveryYetMathod = row.getCell(18);
				noDeliveryYetMathod.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setNoDeliveryYetMathod((noDeliveryYetMathod.getStringCellValue()));

				
				
				//daoQiMoney+weidaoqiMoney;
				
				// 未到期欠交金额
				XSSFCell noDeliveryDebtMathod = row.getCell(19);
				noDeliveryDebtMathod.setCellType(Cell.CELL_TYPE_STRING);
				double weidaoqiMoney = Double.parseDouble(noDeliveryDebtMathod.getStringCellValue());
				/*excelData.setNoDeliveryDebtMathod(Double.parseDouble(noDeliveryDebtMathod.getStringCellValue()));*/
				excelData.setNoDeliveryDebtMathod(weidaoqiMoney);

				
				
				
				
				// 认购日期
				XSSFCell subscribeDate = row.getCell(20);
				String subscribeDate1 = buildDate(subscribeDate);
				System.out.println(subscribeDate1);
				
				//subscribeDate.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setSubscribeDate(subscribeDate1);

				// 签约日期
				XSSFCell signedDate = row.getCell(21);
				String signedDate1 = buildDate(signedDate);
				System.out.println(signedDate1);
				
				//signedDate.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setSignedDate(signedDate1);

				// 按揭银行
				XSSFCell bank = row.getCell(22);
				bank.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setBank((bank.getStringCellValue()));

				// 房间类型

				XSSFCell roomType = row.getCell(23);
				roomType.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setRoomType((roomType.getStringCellValue()));

				// 合同收楼日期

				XSSFCell repossessionDate = row.getCell(24);
				String repossessionDate1 = buildDate(repossessionDate);
				System.out.println(repossessionDate1);
				
				//repossessionDate.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setRepossessionDate(repossessionDate1);

				// 收楼通知书日期
				XSSFCell informDate = row.getCell(25);
				String informDate1 = buildDate(informDate);
				System.out.println(informDate1);
				
				//informDate.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setInformDate(informDate1);

				// 装修情况
				XSSFCell decoration = row.getCell(26);
				decoration.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setDecoration((decoration.getStringCellValue()));

				// 跟办人
				XSSFCell assistParent = row.getCell(27);
				assistParent.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setAssistParent((assistParent.getStringCellValue()));

				// 客户联系电话
				XSSFCell clientPhone = row.getCell(28);
				clientPhone.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setClientPhone((clientPhone.getStringCellValue()));

				// 公积金
				XSSFCell reservedFunds = row.getCell(29);
				reservedFunds.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setReservedFunds((reservedFunds.getStringCellValue()));

				// 合同路址
				XSSFCell contractaddress = row.getCell(30);
				contractaddress.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setContractaddress((contractaddress.getStringCellValue()));

				// 已交金额
				XSSFCell paidMathod = row.getCell(31);
				paidMathod.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setPaidMathod((paidMathod.getStringCellValue()));

				// 已交日期
				XSSFCell yjDate = row.getCell(32);
				String yjDate1 = buildDate(yjDate);
				System.out.println(yjDate1);
				
				//yjDate.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setYjDate(yjDate1);

				// 结算价
				XSSFCell callPrice = row.getCell(33);
				callPrice.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setCallPrice((callPrice.getStringCellValue()));

				// 到期0-30天欠交
				XSSFCell under30 = row.getCell(34);
				under30.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setUnder30((under30.getStringCellValue()));

				// 到期31-90天欠交
				XSSFCell under30To90 = row.getCell(35);
				under30To90.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setUnder30To90((under30To90.getStringCellValue()));

				// 到期91-180天欠交
				XSSFCell under91To180 = row.getCell(36);
				under91To180.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setUnder91To180((under91To180.getStringCellValue()));

				// 到期181 270天欠交
				XSSFCell under181To270 = row.getCell(37);
				under181To270.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setUnder181To270((under181To270.getStringCellValue()));

				// 到期271 360天欠交
				XSSFCell under271To360 = row.getCell(38);
				under271To360.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setUnder271To360((under271To360.getStringCellValue()));

				// 到期361天以上欠交
				XSSFCell under361Up = row.getCell(39);
				under361Up.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setUnder361Up((under361Up.getStringCellValue()));

				// 预售建筑面积
				XSSFCell prePinArea = row.getCell(40);
				prePinArea.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setPrePinArea((prePinArea.getStringCellValue()));

				// 实测建筑面积
				XSSFCell realityArea = row.getCell(41);
				realityArea.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setRealityArea((realityArea.getStringCellValue()));

				// 合同建筑面积
				XSSFCell contractArea = row.getCell(42);
				contractArea.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setContractArea((contractArea.getStringCellValue()));

				// 客户联系地址
				XSSFCell clientAddress = row.getCell(43);
				clientAddress.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setClientAddress((clientAddress.getStringCellValue()));

				// 预计竣工日期
				XSSFCell finishDate = row.getCell(44);
				String finishDate1 = buildDate(finishDate);
				System.out.println(finishDate1);
				
				//finishDate.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setFinishDate(finishDate1);

				// 草签日期
				XSSFCell cqDate = row.getCell(45);
				String cqDate1 = buildDate(cqDate);
				System.out.println(cqDate1);
				
				//cqDate.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setCqDate(cqDate1);

				// 欠款原因
				XSSFCell debtCause = row.getCell(46);
				debtCause.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setDebtCause((debtCause.getStringCellValue()));

				// 是否低首付
				XSSFCell lowPayment = row.getCell(47);
				lowPayment.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setLowPayment((lowPayment.getStringCellValue()));

				// 低首付类型

				XSSFCell lowPaymentType = row.getCell(48);
				lowPaymentType.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setLowPaymentType((lowPaymentType.getStringCellValue()));

				// 低首付金额
				XSSFCell lowPaymentMathod = row.getCell(49);
				lowPaymentMathod.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setLowPaymentMathod((lowPaymentMathod.getStringCellValue()));

				// 签约状态
				XSSFCell signedStart = row.getCell(50);
				signedStart.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setSignedStart((signedStart.getStringCellValue()));

				// 利润中心
				XSSFCell profitCentre = row.getCell(51);
				profitCentre.setCellType(Cell.CELL_TYPE_STRING);
				excelData.setProfitCentre((profitCentre.getStringCellValue()));
				
				
				
				//new一个当前时间
				SimpleDateFormat  date = new  SimpleDateFormat("yyyy-MM-dd");
				excelData.setImportDate(date.format(new Date()));
				System.out.println(excelData.getImportdate());
				
				
				//计算欠款总金额
				//daoQiMoney+weidaoqiMoney;
				excelData.setMoneyAll(daoQiMoney+weidaoqiMoney);
				
				//项目标识
				excelData.setAuthority(UtilsUser.getBiaoshi());
				System.out.println(UtilsUser.getBiaoshi());
				
				
				System.out.println("第 "+i+" 次循环" + excelData);
				//查询看是否由此用户
				List  listC03User = this.getC03User(excelData);
				//不等于 null 表示有此用户，
				if(listC03User.size() > 0) {
					//有就修改状态  只更新客户签约状态，修改为10%，不再进行保存。
					this.updateC03User(excelData);
				}else {
					//excelData.setId(UUID.randomUUID().toString().trim().replace("-", ""));
					// 存储到list集合中了
					//存到集合中的都是新用户
					list.add(excelData);
					//System.out.println(i+"   ---------------------------------");
				}
			}
			//首先将新客户表清空
			c03TableNewUserDaoMapper.deleteTable(UtilsUser.getBiaoshi());
			//增加到新用户中
			System.out.println("已经执行插入到新的客户表中");
			System.out.println(list);
			//看这次有没有新客户，有插入  没有不插入
			if(list.size() > 0) {
				c03TableNewUserDaoMapper.addUser(list);
			}
			//再保存到C03表中
			if(list.size() > 0) {
				c03TableUserDaoMapper.saveDrugGetData(list);
			}
			
			
			
			
			//调用  按揭金额
			this.tuChuYinHangAnJieUser();
			//到期筛选
			this.qianjiao180User();
			//低首付客户删除
			this.dushoufuUserTiChu();
			//
			this.qianKuanZongJieE();
			
			
			
			//获取C03表中的所有数据，到页面查看
			List C03UserList =c03TableUserDaoMapper.getALData(UtilsUser.getBiaoshi());
			model.addAttribute("list",C03UserList);
			
		} catch (Exception e) {
			e.printStackTrace();
			//导入失败
			String data = "successful1";
			return data; 
		} finally {
			workbook = null;
		}
		String data = "successful";
		return data;  
	} 
	

	
	
	@RequestMapping("/getC03TableAll")
	public  String  getC03TableAll(ModelMap model,HttpServletRequest request) {
		List list = c03TableUserDaoMapper.getDrugGetData1(UtilsUser.getBiaoshi());
		System.out.println(list);
		model.addAttribute("list",list);
	return "qiankuanUserTable";
	}   
	
	
	
	@RequestMapping("/getExcelData")
	public  String  getExcelData(ModelMap model,HttpServletRequest request) {
		List list = c03TableUserDaoMapper.getDrugGetData1(UtilsUser.getBiaoshi());
		System.out.println(list);
		model.addAttribute("list",list);
	return "5c03youxiaokehu";
	}   
	
	
	@RequestMapping("/getWuxiaoUser")
	public  String  getWuxiaoUser(ModelMap model,HttpServletRequest request) {
		List list = c03WuXiaoUserDaoMapper.getAllUser(UtilsUser.getBiaoshi());
		System.out.println(list);
		model.addAttribute("list",list);
	return "5c03wuxiaoshujuTable";
	} 
	
	
	@RequestMapping("/deleteQianKuanTable")
	public  String  deleteQianKuanTable(ModelMap model,HttpServletRequest request) {
		
		String str = request.getParameter("allData");
		System.out.println("已经执行我！！！！");
		System.out.println(str);
		//获取数据  存到list中
		//先把本条数据获取过来，存放到无效表中  再进行删除
		//sql 怎样实现把查出来的数据存到另一张表中，再删除
		//获取前端多个id 存放到集合中
		//再进行循环，根据ID获取每一条数据，插入到无效表中，再将C03表中本条数据删除
		System.out.println("");
		/*List list = c03TableNewUserDaoMapper.getAllNewUser();
		System.out.println(list);
		model.addAttribute("list",list);*/
	return "getAllNewUser";
	} 
	
	
	
	/**
	 * 测试
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteQianKuanTable1")
	public  String  deleteQianKuanTable1(ModelMap model,HttpServletRequest request) {
		
		String str = request.getParameter("allData");
		System.out.println("已经执行我！！！！");
		System.out.println(str);
		//获取数据  存到list中
		//先把本条数据获取过来，存放到无效表中  再进行删除
		//sql 怎样实现把查出来的数据存到另一张表中，再删除
		//获取前端多个id 存放到集合中
		//再进行循环，根据ID获取每一条数据，插入到无效表中，再将C03表中本条数据删除
		System.out.println("");
		/*List list = c03TableNewUserDaoMapper.getAllNewUser();
		System.out.println(list);
		model.addAttribute("list",list);*/
	return "getAllNewUser";
	} 
	
	
	//删除欠款客户
	@RequestMapping("/getAllNewUser")
	public  String  getAllNewUser(ModelMap model,HttpServletRequest request) {
		List list = c03TableNewUserDaoMapper.getAllNewUser(UtilsUser.getBiaoshi());
		System.out.println(list);
		model.addAttribute("list",list);
	return "getAllNewUser";
	} 
	
	
	
	//查询C03表中看是否有此条数据
	public  List  getC03User(C03TableUser excelData) {
		//根据编号查询
		String bianhao = excelData.getTheRoomNumber();
		return  c03TableUserDaoMapper.getC03User(excelData);
	}
	
	
	
	//根据房间编号作为条件更新
	public void  updateC03User(C03TableUser excelData) {
		excelData.setSignedStart("10%");
		c03TableUserDaoMapper.updateC03User(excelData);
	}
	
	
	
	// 2----》筛选 c03表中的  应交款项 列，只保留为“银行按揭”的客户，非 银行按揭 ，保存到“初始无效客户表中”，并将C03表中 非 银行按揭 的客户删除。

		public  synchronized  void  tuChuYinHangAnJieUser() {
			try {
				C03TableUser c03TableUser = new C03TableUser();
				c03TableUser.setFund("银行按揭");
				c03TableUser.setAuthority(UtilsUser.getBiaoshi());
				//调用Service   Dao  通过String类型的数�?  在数据库中查�?  应交款项�?  查出 银行按揭的客户，保存到list中，
				
				List list = c03TableUserDaoMapper.getDrugGetData(c03TableUser);
				//调用无效客户方法  吧无效客户保存进去�??
				//不为空事插入
				List listBean = new ArrayList();
				if(list.size() > 0) {
					//循环list
					for(int x = 0;x<list.size();x++) {
						C03TableUser c03TableUserBean = (C03TableUser)list.get(x);
						c03TableUserBean.setCause("非银行按揭按揭客户");
						listBean.add(c03TableUserBean);
					}
					this.setC03Data(listBean);
				}
				//再�?�过sql语句 在C03表中 �? 应交款项�?   �? 银行按揭的客户删除掉
				c03TableUserDaoMapper.deleteC03TavleData(c03TableUser);
				//返回1 表示调用成功
				System.out.println("调用成功");
				
			}catch(Exception e) {
			}
		}  
		
		
		//向C03无效客户表中插入数据，共用方�?
		public synchronized void setC03Data(List list) {
			c03WuXiaoUserDaoMapper.putOneAndAllC03NoUserData(list);
		}
		
		

		
		
		
		//3： ----》到期筛选：需要根据：AL  , AM , AN , 这三行 ，到期180天以上的， 只要有不为0 的客户，就保存到“初始无效客户表中”，并将 AL , AM , AN为非0的客户，进行删除。
		//�?�?  �?个判�?  AL under181To270  ,  AM  under271To360  , AN  under361Up
		public synchronized  void  qianjiao180User() {
			try {
				//首先获取 AL  保存
				List listAL = c03TableUserDaoMapper.getALData(UtilsUser.getBiaoshi());
				List listALBean = new ArrayList();  //
				if(listAL.size() != 0) {
					for(int x = 0;x<listAL.size();x++) {
						C03TableUser c03TableUser = (C03TableUser)listAL.get(x);
						c03TableUser.setCause("到期筛选无效客户");
						listALBean.add(c03TableUser);
					}
					//增加到C03表中
					this.setC03Data(listALBean);
					//再删�?
					c03TableUserDaoMapper.deleteALData(UtilsUser.getBiaoshi());
				}
				//在获�?  AM  保存
				List listAM = c03TableUserDaoMapper.getAMData(UtilsUser.getBiaoshi());
				List  listAMBean = new ArrayList();
				if(listAM.size() != 0) {
					for(int x = 0;x<listAM.size();x++) {
						C03TableUser c03TableUser = (C03TableUser)listAM.get(x);
						c03TableUser.setCause("到期筛选无效客户");
						listAMBean.add(c03TableUser);
					}
					this.setC03Data(listAMBean);
					//再删�?
					c03TableUserDaoMapper.deleteAMData(UtilsUser.getBiaoshi());
				}
				//在获�? AN  保存
				List listAN = c03TableUserDaoMapper.getANData(UtilsUser.getBiaoshi());
				List  listANBean = new ArrayList();
				if(listAN.size() != 0) {
					for(int x = 0;x<listAN.size();x++) {
						C03TableUser c03TableUser = (C03TableUser)listAN.get(x);
						c03TableUser.setCause("到期筛选无效客户");
						listANBean.add(c03TableUser);
					}
					this.setC03Data(listANBean);
					//再删�?
					c03TableUserDaoMapper.deleteANData(UtilsUser.getBiaoshi());
				}
			}catch(Exception e) {
			}
		}
		
		
		
		//4： ----》低首付客户剔除(具体剔除细节已沟通，因业务描述较多，不再详细描述)
		public  synchronized  void   dushoufuUserTiChu() {
			
			//获取C03表中的所有数�?
			List listUserAll = c03TableUserDaoMapper.getDrugGetData1(UtilsUser.getBiaoshi());
			for( int i = 0 ; i < listUserAll.size() ; i++) {//内部不锁定，效率�?高，但在多线程要考虑并发操作的问题�??
				C03TableUser lackOfDetailBean = (C03TableUser)listUserAll.get(i);
				
				int c03id = lackOfDetailBean.getId();
				
				Double weiDaoqiQianFuNew = 0.0;
				if(new Double(lackOfDetailBean.getNoDeliveryDebtMathod()) != null) {
					weiDaoqiQianFuNew = lackOfDetailBean.getNoDeliveryDebtMathod();
				}
				
				Double daoqiqianfuNew = 0.0;
				if(lackOfDetailBean.getDeliveryDebtMathod() != 0.0) {
					 daoqiqianfuNew = lackOfDetailBean.getDeliveryDebtMathod();
				}
				
				double rengoujiaNew = 0.0;
				String rengoujia="";
				if(lackOfDetailBean.getSubscriptionPrice() != null) {
					rengoujia = lackOfDetailBean.getSubscriptionPrice();
					rengoujiaNew = Double.valueOf(rengoujia.toString());
				}
				
				 
				//获取房间类型
				String fangjianType = lackOfDetailBean.getRoomType();
				System.out.println("    ------------------  "+fangjianType);
				
				if(fangjianType != null) {
					if(fangjianType.equals("洋房") || fangjianType.equals("双拼") || fangjianType.equals("别墅") || fangjianType.equals("联排") ) {
						//计算未到期欠�?+到期欠付
						double countJia = (weiDaoqiQianFuNew+daoqiqianfuNew);
						double newMoney = (countJia/rengoujiaNew);
						System.out.println("除认购价后的价格："+newMoney);
						System.out.println("认购价格："+rengoujiaNew);
						
						
						//获取百分比结�?
						String resultZhi = this.myPercent(newMoney,rengoujiaNew);
						//截取第一个�??  如果是�??-”数，证明不是百分之30以后的�?�，直接插入到无效用户数据表中，在根据获取的id删除C03中的本条数据
						String oneAddress = resultZhi.substring(0,1);
						List list = new ArrayList();
						List newList = new ArrayList();
						if(oneAddress.equals("-")){     
							
							list.add(lackOfDetailBean);
							for(int x = 0;x<list.size();x++) {
								C03TableUser c03TableUser = (C03TableUser)list.get(x);
								c03TableUser.setCause("低首付客户");
								newList.add(c03TableUser);
								System.out.println(c03TableUser.getCause());
							}
							//调用插入方法
							this.setC03Data(newList);
							list = null;
							newList = null;
							//删除
							c03TableUserDaoMapper.deleteByUserId(c03id);
						}else {
							double baifenbi = Double.valueOf(resultZhi.replace("%",""));
							if(baifenbi < 70) {
								//大于  等于 �?么都不做
							}else {
								//调用插入方法
								list.add(lackOfDetailBean);
								for(int x = 0;x<list.size();x++) {
									C03TableUser c03TableUser = (C03TableUser)list.get(x);
									c03TableUser.setCause("低首付客户");
									newList.add(c03TableUser);
								}
								this.setC03Data(newList);
								list = null;
								newList = null;
								//不大�? 直接剔除
								c03TableUserDaoMapper.deleteByUserId(c03id);
							}
						}
						//如果是数字，则将�?%”号去掉，转换成double类型  并判断是否大�?30%   不大�?  直接插入到无效用户数据表中，在根据获取的id删除C03中的本条数据，超�?30%则保�?
					}else {
						//计算未到期欠�?+到期欠付
						double countJia = (weiDaoqiQianFuNew+daoqiqianfuNew);
						double newMoney = (countJia/rengoujiaNew);
						System.out.println("除认购价后的价格："+newMoney);
						System.out.println("认购价格："+rengoujiaNew);
						//获取百分比结�?
						String resultZhi = this.myPercent(newMoney,rengoujiaNew);
						//截取第一个�??  如果是�??-”数，证明不是百分之30以后的�?�，直接插入到无效用户数据表中，在根据获取的id删除C03中的本条数据
						String oneAddress = resultZhi.substring(0);
						List list = new ArrayList();
						List newList = new ArrayList();
						if(oneAddress == "-") {
							list.add(lackOfDetailBean);
							for(int x = 0;x<list.size();x++) {
								C03TableUser c03TableUser = (C03TableUser)list.get(x);
								c03TableUser.setCause("低首付客户");
								newList.add(c03TableUser);
							}
							//调用插入方法
							this.setC03Data(newList);
							list = null;
							newList = null;
							//删除
							c03TableUserDaoMapper.deleteByUserId(c03id);
						}else {
							double baifenbi = Double.valueOf(resultZhi.replace("%",""));
							
							//其余的首�?  必须大于50%
							if(baifenbi < 50) {
								//大于  等于 �?么都不做
							}else {
								//调用插入方法
								list.add(lackOfDetailBean);
								for(int x = 0;x<list.size();x++) {
									C03TableUser c03TableUser = (C03TableUser)list.get(x);
									c03TableUser.setCause("低首付客户");
									newList.add(c03TableUser);
								}
								this.setC03Data(newList);
								list = null;
								newList = null;
								//不大�? 直接剔除
								c03TableUserDaoMapper.deleteByUserId(c03id);
							}
						}
						//如果是数字，则将�?%”号去掉，转换成double类型  并判断是否大�?30%   不大�?  直接插入到无效用户数据表中，在根据获取的id删除C03中的本条数据，超�?30%则保�?
					}
				}
			}
		}
		
		
		
		
		//4： ----》低首付客户剔除(具体剔除细节已沟通，因业务描述较多，不再详细描述)
		//欠款总金额
		public synchronized  void  qianKuanZongJieE() {
			//获取指定字段的数�?
			
			List bufenDatalist = c03TableUserDaoMapper.getbufenUserData(UtilsUser.getBiaoshi());
			
			for( int i = 0 ; i < bufenDatalist.size() ; i++) {//内部不锁定，效率�?高，但在多线程要考虑并发操作的问题�??
				C03TableUser bufenDataBean = (C03TableUser)bufenDatalist.get(i);
				//new�?个新的对象插�?
				C03TableUser lackOfDetailObj = new C03TableUser();
				lackOfDetailObj.setId(bufenDataBean.getId());
				double daoqiqian = bufenDataBean.getDeliveryDebtMathod();
				double weidaoqiqian = bufenDataBean.getNoDeliveryDebtMathod();
				//计算总金�?
				lackOfDetailObj.setAllzongqianshu(daoqiqian+weidaoqiqian);
				//根据id将这个�?�金额插入进�?
				c03TableUserDaoMapper.updateZongJinE(lackOfDetailObj);
			}
			
			//再判断住房是否大于非住房总金�?   大于  没事  不大�?  判断超出住房总金�? 多少
			C03TableUser zhufang = c03TableUserDaoMapper.zhufangAllJinE(UtilsUser.getBiaoshi());
			C03TableUser nozhufang = c03TableUserDaoMapper.nozhufangAllJinE(UtilsUser.getBiaoshi());
			if(zhufang !=null && nozhufang != null) {
				//判断住宅总金�?  是否大于非住宅用户�?�金�?  
				if(zhufang.getAllzongqianshu() > nozhufang.getAllzongqianshu()) {
					//NO
					//如果住宅客户总金�?  比非住宅用户的�?�金额高 ，则不做任何判断�?
				}else {
					double duoyuMoney = (nozhufang.getAllzongqianshu() - zhufang.getAllzongqianshu());
					//每次倒叙  获取�?条数�?  获取完成后， 从duoyuMoney身上减去 这个�?  直到这个�? 减不�?时，结束循环
					for(;true;) {
						//获取倒叙数据
						C03TableUser zuixiaojine = c03TableUserDaoMapper.daoxuData(UtilsUser.getBiaoshi());
						double minMoney = zuixiaojine.getAllzongqianshu();
						
						//大于0  继续�?
						if(duoyuMoney - minMoney >=0) {
							duoyuMoney -= minMoney;
							//获取id地址
							int id = zuixiaojine.getId();
							this.putNoUserData(id);
							//根据id吧这�?条客户删�?
							c03TableUserDaoMapper.deleteByIdUser(id);
						}else {
							//�?出当前循�?
							break;
						}
					}
				}
			}
		}
		
		//插入�?条无效用户数�? 公共方法
		public synchronized void  putNoUserData(int id) {
			c03WuXiaoUserDaoMapper.putNoUserData(id);
		}
		
		
		
		
		//5：----》签约状态用户剔除(具体剔除细节已沟通，因业务描述较多，不再详细描述)。
		public  void   ifQianYueZhuangTai() {
			//获取正签状�?�的客户   获取  id   T未到期欠�? +  Q到期欠付 
			C03TableUser zhengQianUser = c03TableUserDaoMapper.getZhengQianUser();
				//获取 正签 总金�?
			double zhengqianAllMoney = zhengQianUser.getAllzongqianshu();
			//获取草签状�?�的客户   获取  id   T未到期欠�? +  Q到期欠付   
			C03TableUser caoQianUser = c03TableUserDaoMapper.getCaoQianUser();
			//获取 草签总金�?
			double caoQianAllMoney = caoQianUser.getAllzongqianshu();
			
			//如果正签的�?�金�?  比草签的总金额大   �?么都不判�?
			if(zhengqianAllMoney > caoQianAllMoney) {
				
			}else {
				//如果草签的�?�金�?  比正签的总金额大     获取大多少， 再数据库从小到大排序  删除�?
				double duochuMoney = (caoQianAllMoney-zhengqianAllMoney);
				
				for(;true;) {
					//获取倒叙数据
					C03TableUser zuixiaojine = c03TableUserDaoMapper.daoxuData(UtilsUser.getBiaoshi());
					double minMoney = zuixiaojine.getAllzongqianshu();
					
					//大于0  继续�?
					if(duochuMoney - minMoney >=0) {
						duochuMoney -= minMoney;
						//获取ip地址
						int id = zuixiaojine.getId();
						//增加到无效表
						this.putNoUserData(id);
						//根据id吧这�?条客户删�?
						c03TableUserDaoMapper.deleteByIdUser();
					}else {
						//�?出当前循�?
						break;
					}
				}
			}
		}
		
	/**
	 *  
			
			
			
			
			       
			
			
			(返回页面：)  6：----》查找所有的C03表中客户数据，返回给客户， 用户可在页面
			                     中浏览客户数据，也可通过多选框删除 “欠款原因的客户”，
			 也可以导出excel文件。
			
			
			6.1： ----》 如是点击删除按钮 点击后，后台首先将客户数据保存到“初始无效客户表中”，再从“C03表”中将此部分客户数据删除。
			
			(返回页面：)	        6.2： ----》 删除后，再次返回有效客户数据中。
			     
			执行完成以上流程后，可在“初始无效客户表中”查看到初始筛选不合格的客户数据。
			提供按时间查询指定无效数据页面，
			提供Excel数据导出功能，
			可将查看到的数据导出到本地。 		
			
			可在页面 “本次新增客户”中 查看到本次新增的客户，可将新增客户通过excel下载保存到本地。
			
			
			到此，初始筛选客户，已经完成筛选。   
	 */
	
	
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
	
	
	//计算百分比公共方�?
	public synchronized String myPercent(double y, double z) {
        String baifenbi = "";// 接受百分比的�?
        double baiy = y;
        double baiz = z;
        double fen = baiy / baiz;
        // NumberFormat nf = NumberFormat.getPercentInstance(); 注释掉的也是�?种方�?
        // nf.setMinimumFractionDigits( 2 ); 保留到小数点后几�?
        DecimalFormat df1 = new DecimalFormat("##.00%"); // ##.00%
        // baifenbi=nf.format(fen);
        baifenbi = df1.format(fen);
        System.out.println(baifenbi);
        return baifenbi;
    }
	
	
	//获取超期草签客户表
	@RequestMapping("/getAllUser")
	public String  getAllUser(ModelMap model,HttpServletRequest request) {
		int in = new Integer(UtilsUser.getBiaoshi());
		System.out.println(in);
		List list = jDCQCaoQianUserDaoMapper.getAllUser(new Integer(UtilsUser.getBiaoshi()));
		model.addAttribute("list",list);
		return "chaoQiCaoQianKeHuBiao";
	}
	
	
	//获取超期草签客户表
		@RequestMapping("/getChaoQiNoFuKuanUser")
		public String  getChaoQiNoFuKuanUser(ModelMap model,HttpServletRequest request) {
			List list = jDCQNoFuKuanUserDaoMapper.geyUserAll();
			model.addAttribute("list",list);
			return "NofuKuanUser";
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
			return utilDate;
		}
		
		
		
		//按时间查看初始无效客户数据
			@RequestMapping("/chushiWuXiao")
			public  String   chushiWuXiao(ModelMap model,HttpServletRequest request) throws ParseException {
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
				List allUserDataList = c03WuXiaoUserDaoMapper.getDateUser(utilDate);
				/*double moneyAll = 0.0;
				for( int i = 0 ; i < allUserDataList.size() ; i++) { 
					HKSuoYou hKSuoYou = (HKSuoYou)allUserDataList.get(i);
					//计算总金额
					moneyAll += Double.parseDouble(hKSuoYou.getFangkuanjine());
				}*/
				//吧数据展示到页面上，
				model.addAttribute("list",allUserDataList);
				//吧总金额展示到页面上
				/*model.addAttribute("moneyAll",moneyAll);*/
				return "chuShiWuxiaoUser";
			}
}
