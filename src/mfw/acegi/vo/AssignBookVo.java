package mfw.acegi.vo;


import mfw.acegi.pojo.Book;


public class AssignBookVo
implements Comparable
{
	private Book book;
	private Integer count;
	private boolean select = false;

	
	public AssignBookVo(Book book, Integer count)
	{
		this.book = book;
		this.count = count;
		}

	
	public Book getBook()
	{
		return this.book;
		}

	public void setBook(Book book) {
		this.book = book;
		}

	public Integer getCount() {
		return Integer.valueOf(this.count.intValue() - 1);
		}

	public void setCount(Integer count) {
		this.count = count;
		}

	public boolean isSelect() {
		return this.select;
		}

	public void setSelect(boolean select) {
		this.select = select;
		}

	
	@Override
	public int compareTo(Object o)
	{
		if (this.select)
			return -1;
		if (((AssignBookVo) o).select) {
			return 1;
			}
		
		return getCount().compareTo(((AssignBookVo) o).getCount());
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.vo.AssignBookVo JD-Core Version: 0.6.2
 */