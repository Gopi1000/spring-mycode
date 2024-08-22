package com.scube.springcrm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scube.springcrm.vo.ProductVO;


@Repository
public class ProductDaoImpl implements ProductDao {
	
    @Autowired
	SessionFactory sessionfactory;
	
	public String insertprodectdetails(ProductVO prodectvo) {
		
	  try {
		    Session session= sessionfactory.getCurrentSession();
	        int id=(int) session.save(prodectvo);
	  }
		catch (Exception e) {				
	    System.out.println(e);			
	    return null;			
	  }			
	    return "created";
	  }

	
	
	public List<ProductVO> getprodectDetails() {
		
	List<ProductVO> productvo=new ArrayList<>();
		
	    try {
	    	 Session session= sessionfactory.getCurrentSession();
	    	 Criteria criteria = session.createCriteria(ProductVO.class);
	    	 productvo=criteria.list();
	    	 if (null!=productvo&&!productvo.isEmpty()&&productvo.size()>0) { 
	      return productvo;
	    }
	  }
          catch (Exception e) {
          System.out.println(e);
          return null;
		}
	      return null;
		}
	
	

    public ProductVO getParticularValue(int ids) {
    	
    	ProductVO productvo=new ProductVO();
    	
		try{
			
			Session session=sessionfactory.getCurrentSession();
			Criteria criteria=session.createCriteria(ProductVO.class);
			criteria.add(Restrictions.eq("productId", ids));
			productvo=(ProductVO)criteria.uniqueResult();
		}
            catch (Exception e) {
			System.out.println(e);
			return null;
		}
		    return productvo;
		}	


    
	public String updateProduct(ProductVO bo) {
		
        try {
			
			Session session=sessionfactory.getCurrentSession();
			session.update(bo);
		}
		    catch (Exception e) {
     		System.out.println(e);
		}
            return null;
	    }
	


	public String deleteProduct(ProductVO van) {
		
		List<ProductVO> productvo=new ArrayList<>();
		try {
			
			Session session=sessionfactory.getCurrentSession();
			session.delete(van);
			if (null!=productvo&&!productvo.isEmpty()&&productvo.size()>0) { 
				
			}
			return "deleted";
		}
            catch (Exception e) {
			System.out.println(e);			
		}
		    return null;
		
	}
}









