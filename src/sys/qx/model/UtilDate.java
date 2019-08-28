package sys.qx.model;

public class UtilDate {

	
	private String srartDate;
	private String stropDate;
	private String zhuagtai;
	private int id;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getZhuagtai() {
		return zhuagtai;
	}
	public void setZhuagtai(String zhuagtai) {
		this.zhuagtai = zhuagtai;
	}
	public String getSrartDate() {
		return srartDate;
	}
	public void setSrartDate(String srartDate) {
		this.srartDate = srartDate;
	}
	public String getStropDate() {
		return stropDate;
	}
	public void setStropDate(String stropDate) {
		this.stropDate = stropDate;
	}
	@Override
	public String toString() {
		return "UtilDate [srartDate=" + srartDate + ", stropDate=" + stropDate + ", zhuagtai=" + zhuagtai + ", id=" + id
				+ "]";
	}
}
