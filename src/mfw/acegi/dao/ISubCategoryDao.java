package mfw.acegi.dao;

import java.util.List;
import mfw.acegi.dao.util.QueryPage;
import mfw.acegi.pojo.SubCategory;
import mfw.acegi.vo.SubCategoryStatisticsVo;

public abstract interface ISubCategoryDao {
	public abstract SubCategory getById(Integer paramInteger);

	public abstract List getAll();

	public abstract void delete(SubCategory paramSubCategory);

	public abstract void save(SubCategory paramSubCategory);

	public abstract List getByPage(QueryPage paramQueryPage);

	public abstract List<SubCategoryStatisticsVo> getStatList(int paramInt);
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.ISubCategoryDao JD-Core Version: 0.6.2
 */