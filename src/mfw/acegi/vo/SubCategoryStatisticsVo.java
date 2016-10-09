package mfw.acegi.vo;


public class SubCategoryStatisticsVo
{
	private int count;
	private int totalCount;
	private String descr;
	private int subCategoryId;

	
	public String getDescr()
	{
		return this.descr;
		}

	public void setDescr(String descr) {
		this.descr = descr;
		}

	public int getSubCategoryId() {
		return this.subCategoryId;
		}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
		}

	public int getCount() {
		return this.count;
		}

	public void setCount(int count) {
		this.count = count;
		}

	public int getTotalCount() {
		return this.totalCount;
		}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.vo.SubCategoryStatisticsVo JD-Core Version: 0.6.2
 */