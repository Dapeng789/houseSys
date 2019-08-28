package sys.qx.model;

public class C03TableNewUser {

	private int id;
	//所属区域  
	private String area;
	//区域总裁  
	private String areaPresident;
  	//楼盘  
	private String building;
	//付款方式  
	private String paymentMethod;
	//房间编号  
	private String theRoomNumber;
	//苑区	
	private String park;
	//路址 
	private String url;
	//业主名称  
	private String    ownerName;
	//认购价  
	private String    subscriptionPrice;
	//应交款项  
	private String    fund ;
	//推售日期  
	private String    sellingDate ;
	//预售证日期  
	private String   prePinDate  ;
	//竣工备案日期  
	private String    closeoutDate;
	//应交日期  
	private String    deliveryDate;
	//到期应交金额  
	private String    deliveryOughtToMathod;
	//到期已交金额  
	private String    deliveryYetMathod;
	//到期欠交金额  
	private Double    deliveryDebtMathod=0.0;
	//未到期应交金额  
	private String   noDeliveryOughtToMathod ;
	//未到期已交金额  
	private String    noDeliveryYetMathod;
	//未到期欠交金额  
	private double    noDeliveryDebtMathod;
	//认购日期  
	private String    subscribeDate;
	//签约日期  
	private String    signedDate;
	//按揭银行  
	private String   bank ;
	//房间类型  
	private String   roomType ;
	//合同收楼日期  
	private String    repossessionDate;
	//收楼通知书日期  
	private String   informDate ;
	//装修情况  
	private String    decoration;
	//跟办人  
	private String   assistParent ;
	//客户联系电话  
	private String    clientPhone;
	//公积金  
	private String    reservedFunds;
	//合同路址  
	private String    contractaddress;
	//已交金额  
	private String    paidMathod;
	//已交日期  
	private String    yjDate;
	//结算价  
	private String    callPrice;
	//到期0-30天欠交  
	private String    under30;
	//到期31-90天欠交  
	private String    under30To90;
	//到期91-180天欠交  
	private String    under91To180;
	//到期181 270天欠交 
	private String   under181To270 ;
	//到期271 360天欠交 
	private String   under271To360 ;
	//到期361天以上欠交 
	private String   under361Up ;
	//预售建筑面积  
	private String    prePinArea;
	//实测建筑面积  
	private String    realityArea;
	//合同建筑面积  
	private String    contractArea;
	//客户联系地址  
	private String    clientAddress;
	//预计竣工日期  
	private String    finishDate;
	//草签日期  
	private String    cqDate;
	//欠款原因  
	private String    debtCause;
	//是否低首付  
	private String    lowPayment;
	//低首付类型  
	private String    lowPaymentType;
	//低首付金额  
	private String    lowPaymentMathod;
	//签约状态  
	private String    signedStart;
	//利润中心  
	private String    profitCentre;
	//总钱数
	private Double    allzongqianshu;
	//导入时间
	private String  importdate;
	
	private Double moneyAll;
	
	private int authority;
	
	
	
	
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public Double getMoneyAll() {
		return moneyAll;
	}
	public void setMoneyAll(Double moneyAll) {
		this.moneyAll = moneyAll;
	}
	public String getImportdate() {
		return importdate;
	}
	public void setImportdate(String importdate) {
		this.importdate = importdate;
	}
	public Double getAllzongqianshu() {
		return allzongqianshu;
	}
	public void setAllzongqianshu(Double allzongqianshu) {
		this.allzongqianshu = allzongqianshu;
	}
	public void setDeliveryDebtMathod(Double deliveryDebtMathod) {
		this.deliveryDebtMathod = deliveryDebtMathod;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPark() {
		return park;
	}
	public void setPark(String park) {
		this.park = park;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAreaPresident() {
		return areaPresident;
	}
	public void setAreaPresident(String areaPresident) {
		this.areaPresident = areaPresident;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getTheRoomNumber() {
		return theRoomNumber;
	}
	public void setTheRoomNumber(String theRoomNumber) {
		this.theRoomNumber = theRoomNumber;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getSubscriptionPrice() {
		return subscriptionPrice;
	}
	public void setSubscriptionPrice(String subscriptionPrice) {
		this.subscriptionPrice = subscriptionPrice;
	}
	public String getFund() {
		return fund;
	}
	public void setFund(String fund) {
		this.fund = fund;
	}
	public String getSellingDate() {
		return sellingDate;
	}
	public void setSellingDate(String sellingDate) {
		this.sellingDate = sellingDate;
	}
	public String getPrePinDate() {
		return prePinDate;
	}
	public void setPrePinDate(String prePinDate) {
		this.prePinDate = prePinDate;
	}
	public String getCloseoutDate() {
		return closeoutDate;
	}
	public void setCloseoutDate(String closeoutDate) {
		this.closeoutDate = closeoutDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryOughtToMathod() {
		return deliveryOughtToMathod;
	}
	public void setDeliveryOughtToMathod(String deliveryOughtToMathod) {
		this.deliveryOughtToMathod = deliveryOughtToMathod;
	}
	public String getDeliveryYetMathod() {
		return deliveryYetMathod;
	}
	public void setDeliveryYetMathod(String deliveryYetMathod) {
		this.deliveryYetMathod = deliveryYetMathod;
	}
	public double getDeliveryDebtMathod() {
		return deliveryDebtMathod;
	}
	public void setDeliveryDebtMathod(double deliveryDebtMathod) {
		this.deliveryDebtMathod = deliveryDebtMathod;
	}
	public String getNoDeliveryOughtToMathod() {
		return noDeliveryOughtToMathod;
	}
	public void setNoDeliveryOughtToMathod(String noDeliveryOughtToMathod) {
		this.noDeliveryOughtToMathod = noDeliveryOughtToMathod;
	}
	public String getNoDeliveryYetMathod() {
		return noDeliveryYetMathod;
	}
	public void setNoDeliveryYetMathod(String noDeliveryYetMathod) {
		this.noDeliveryYetMathod = noDeliveryYetMathod;
	}
	public double getNoDeliveryDebtMathod() {
		return noDeliveryDebtMathod;
	}
	public void setNoDeliveryDebtMathod(double noDeliveryDebtMathod) {
		this.noDeliveryDebtMathod = noDeliveryDebtMathod;
	}
	public String getSubscribeDate() {
		return subscribeDate;
	}
	public void setSubscribeDate(String subscribeDate) {
		this.subscribeDate = subscribeDate;
	}
	public String getSignedDate() {
		return signedDate;
	}
	public void setSignedDate(String signedDate) {
		this.signedDate = signedDate;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getRepossessionDate() {
		return repossessionDate;
	}
	public void setRepossessionDate(String repossessionDate) {
		this.repossessionDate = repossessionDate;
	}
	public String getInformDate() {
		return informDate;
	}
	public void setInformDate(String informDate) {
		this.informDate = informDate;
	}
	public String getDecoration() {
		return decoration;
	}
	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}
	public String getAssistParent() {
		return assistParent;
	}
	public void setAssistParent(String assistParent) {
		this.assistParent = assistParent;
	}
	public String getClientPhone() {
		return clientPhone;
	}
	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}
	public String getReservedFunds() {
		return reservedFunds;
	}
	public void setReservedFunds(String reservedFunds) {
		this.reservedFunds = reservedFunds;
	}
	public String getContractaddress() {
		return contractaddress;
	}
	public void setContractaddress(String contractaddress) {
		this.contractaddress = contractaddress;
	}
	public String getPaidMathod() {
		return paidMathod;
	}
	public void setPaidMathod(String paidMathod) {
		this.paidMathod = paidMathod;
	}
	public String getYjDate() {
		return yjDate;
	}
	public void setYjDate(String yjDate) {
		this.yjDate = yjDate;
	}
	public String getCallPrice() {
		return callPrice;
	}
	public void setCallPrice(String callPrice) {
		this.callPrice = callPrice;
	}
	public String getUnder30() {
		return under30;
	}
	public void setUnder30(String under30) {
		this.under30 = under30;
	}
	public String getUnder30To90() {
		return under30To90;
	}
	public void setUnder30To90(String under30To90) {
		this.under30To90 = under30To90;
	}
	public String getUnder91To180() {
		return under91To180;
	}
	public void setUnder91To180(String under91To180) {
		this.under91To180 = under91To180;
	}
	public String getUnder181To270() {
		return under181To270;
	}
	public void setUnder181To270(String under181To270) {
		this.under181To270 = under181To270;
	}
	public String getUnder271To360() {
		return under271To360;
	}
	public void setUnder271To360(String under271To360) {
		this.under271To360 = under271To360;
	}
	public String getUnder361Up() {
		return under361Up;
	}
	public void setUnder361Up(String under361Up) {
		this.under361Up = under361Up;
	}
	public String getPrePinArea() {
		return prePinArea;
	}
	public void setPrePinArea(String prePinArea) {
		this.prePinArea = prePinArea;
	}
	public String getRealityArea() {
		return realityArea;
	}
	public void setRealityArea(String realityArea) {
		this.realityArea = realityArea;
	}
	public String getContractArea() {
		return contractArea;
	}
	public void setContractArea(String contractArea) {
		this.contractArea = contractArea;
	}
	
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getCqDate() {
		return cqDate;
	}
	public void setCqDate(String cqDate) {
		this.cqDate = cqDate;
	}
	public String getDebtCause() {
		return debtCause;
	}
	public void setDebtCause(String debtCause) {
		this.debtCause = debtCause;
	}
	public String getLowPayment() {
		return lowPayment;
	}
	public void setLowPayment(String lowPayment) {
		this.lowPayment = lowPayment;
	}
	public String getLowPaymentType() {
		return lowPaymentType;
	}
	public void setLowPaymentType(String lowPaymentType) {
		this.lowPaymentType = lowPaymentType;
	}
	public String getLowPaymentMathod() {
		return lowPaymentMathod;
	}
	public void setLowPaymentMathod(String lowPaymentMathod) {
		this.lowPaymentMathod = lowPaymentMathod;
	}
	public String getSignedStart() {
		return signedStart;
	}
	public void setSignedStart(String signedStart) {
		this.signedStart = signedStart;
	}
	public String getProfitCentre() {
		return profitCentre;
	}
	public void setProfitCentre(String profitCentre) {
		this.profitCentre = profitCentre;
	}
	@Override
	public String toString() {
		return "C03TableNewUser [id=" + id + ", area=" + area + ", areaPresident=" + areaPresident + ", building="
				+ building + ", paymentMethod=" + paymentMethod + ", theRoomNumber=" + theRoomNumber + ", park=" + park
				+ ", url=" + url + ", ownerName=" + ownerName + ", subscriptionPrice=" + subscriptionPrice + ", fund="
				+ fund + ", sellingDate=" + sellingDate + ", prePinDate=" + prePinDate + ", closeoutDate="
				+ closeoutDate + ", deliveryDate=" + deliveryDate + ", deliveryOughtToMathod=" + deliveryOughtToMathod
				+ ", deliveryYetMathod=" + deliveryYetMathod + ", deliveryDebtMathod=" + deliveryDebtMathod
				+ ", noDeliveryOughtToMathod=" + noDeliveryOughtToMathod + ", noDeliveryYetMathod="
				+ noDeliveryYetMathod + ", noDeliveryDebtMathod=" + noDeliveryDebtMathod + ", subscribeDate="
				+ subscribeDate + ", signedDate=" + signedDate + ", bank=" + bank + ", roomType=" + roomType
				+ ", repossessionDate=" + repossessionDate + ", informDate=" + informDate + ", decoration=" + decoration
				+ ", assistParent=" + assistParent + ", clientPhone=" + clientPhone + ", reservedFunds=" + reservedFunds
				+ ", contractaddress=" + contractaddress + ", paidMathod=" + paidMathod + ", yjDate=" + yjDate
				+ ", callPrice=" + callPrice + ", under30=" + under30 + ", under30To90=" + under30To90
				+ ", under91To180=" + under91To180 + ", under181To270=" + under181To270 + ", under271To360="
				+ under271To360 + ", under361Up=" + under361Up + ", prePinArea=" + prePinArea + ", realityArea="
				+ realityArea + ", contractArea=" + contractArea + ", clientAddress=" + clientAddress + ", finishDate="
				+ finishDate + ", cqDate=" + cqDate + ", debtCause=" + debtCause + ", lowPayment=" + lowPayment
				+ ", lowPaymentType=" + lowPaymentType + ", lowPaymentMathod=" + lowPaymentMathod + ", signedStart="
				+ signedStart + ", profitCentre=" + profitCentre + ", allzongqianshu=" + allzongqianshu
				+ ", importdate=" + importdate + ", moneyAll=" + moneyAll + ", authority=" + authority + "]";
	}
}
