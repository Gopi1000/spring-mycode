package com.scube.mycognitiv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.scube.mycognitiv.bo.CompanyRegistrationBO;
import com.scube.mycognitiv.utils.DatabaseConnectionFactory;

public class CompanyDaoImpl implements CompanyDao {

	private static final Logger LOGGER = Logger.getLogger(CompanyDaoImpl.class);

	@Override
	public CompanyRegistrationBO registration(CompanyRegistrationBO companyReg) {
		Connection con = DatabaseConnectionFactory.createConnection();
		try {
			if(null!=companyReg) {
				if (!companyReg.getCompanyName().isEmpty() && !companyReg.getCompanyEmail().isEmpty()
						&&companyReg.getCompanyContactNo()!=0&&!companyReg.getCompanyLocation().isEmpty()
						&&!companyReg.getCompanyWebsite().isEmpty()&& !companyReg.getPassword().isEmpty()) {
					Statement st = con.createStatement();
					String sql = "INSERT INTO company(companyName, companyEmail, companyContactNo, companyLocation, companyWebsite,isDelete) values ('"
							+ companyReg.getCompanyName()
							+ "','"
							+ companyReg.getCompanyEmail()
							+ "','"
							+ companyReg.getCompanyContactNo()
							+ "','"
							+ companyReg.getCompanyLocation()
							+ "','"
							+companyReg.getCompanyWebsite()
							+ "','"
							+0
							+ "')";
					//System.out.println(sql);
					int i = st.executeUpdate(sql);
					if (i != 0) {
						companyReg.setId(i);
						companyReg.setUserType("Company");
						companyReg.setCompanyName(companyReg.getCompanyName());
						companyReg.setResponse("Company registration Successfully");
					}
				} else {
					companyReg.setErrorMessage("Company registration failed!please contact Admin");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		return companyReg;
	}

	@Override
	public CompanyRegistrationBO authendicate(CompanyRegistrationBO regist) {
		Connection con = DatabaseConnectionFactory.createConnection();
		ResultSet set = null;
		try {
			if(null!=regist && null!=regist.getCompanyEmail() && null!=regist.getPassword() 
					  && !regist.getCompanyEmail().isEmpty()&& !regist.getPassword().isEmpty()) {
				Statement st = con.createStatement();
				String sql = "Select * from  user where email='"
						+ regist.getCompanyEmail() + "' and password='" + regist.getPassword()
						+ "' and isDelete='0' ";
				//System.out.println(sql);
				set = st.executeQuery(sql);
				while (set.next()) {
					regist.setCompanyRegId(set.getInt(1));
					regist.setUserType(set.getString(6));
					regist.setCompanyName(set.getString(2));
					regist.setCompanyEmail(set.getString(4));
					regist.setResponse("Login successfuly");
				}
			}else {
				regist.setResponse("Company Login Failed, Invalid Cridentials");
			}


		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}

			return null;
		}
		return regist;
	}

	@Override
	public CompanyRegistrationBO getCompanydetails(String companyEmail) {
		CompanyRegistrationBO bo = new CompanyRegistrationBO();

		ResultSet set = null;
		Connection con = DatabaseConnectionFactory.createConnection();
		try {
			Statement st = con.createStatement();

			String sql = "Select * from  company where companyEmail='" + companyEmail + "'";
			set = st.executeQuery(sql);

			while (set.next()) {

				CompanyRegistrationBO userBO = new CompanyRegistrationBO();
				userBO.setCompanyRegId(set.getInt(1));
				userBO.setCompanyName(set.getString(2));
				userBO.setCompanyEmail(set.getString(3));
				userBO.setCompanyContactNo(set.getLong(4));
				userBO.setCompanyLocation(set.getString(5));
				userBO.setCompanyWebsite(set.getString(6));

				bo = userBO;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}
		}
		return bo;
	}

	@Override
	public int getCompanyCount(CompanyRegistrationBO companyBO) throws Exception {

		int companyCount=0;
		Connection con = DatabaseConnectionFactory.createConnection();
		try {
			Statement stmtRef=con.createStatement();
			String query="select count(user_id)from company where isDelete='false'";
			ResultSet rsRef=stmtRef.executeQuery(query);
			while(rsRef.next()) {
				companyCount=rsRef.getInt(1);
			}
			if(companyCount>0) {
				return companyCount;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return companyCount;
	}

	@Override
	public CompanyRegistrationBO retrieveAllCompanies(CompanyRegistrationBO companyBO) throws Exception {

		//CompanyRegistrationBO bo = new CompanyRegistrationBO();
		int count = 0;
		ResultSet set = null;
		String sql=null;
		String paginationQuery=null;
		List<CompanyRegistrationBO> companyList = new ArrayList<CompanyRegistrationBO>();
		Connection con = DatabaseConnectionFactory.createConnection();
		try {
			Statement st = con.createStatement();
			if(null!=companyBO) {
				sql = "Select * from  company where isDelete='false' ";
				if(companyBO.getRecordsPerPage()>0) {
					paginationQuery="and user_id ORDER BY user_id LIMIT "+companyBO.getRecordsPerPage()+" OFFSET "+companyBO.getRecordIndex()+"";
					sql=sql+paginationQuery;
				}
			}
			//System.out.println(sql);
			set = st.executeQuery(sql);
			while (set.next()) {
				count++;
				CompanyRegistrationBO companyBo = new CompanyRegistrationBO();
				companyBo.setCompanyRegId(set.getInt(1));
				companyBo.setCompanyName(set.getString(2));
				companyBo.setCompanyEmail(set.getString(3));
				companyBo.setCompanyContactNo(set.getLong(4));
				companyBo.setCompanyLocation(set.getString(5));
				companyBo.setCompanyWebsite(set.getString(6));
				companyBo.setIsDelete(set.getBoolean(7));
				companyBo.setSerialNo(count);
				companyList.add(companyBo);

			}
			companyBO.setCompanyList(companyList);
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}
		}
		return companyBO;
	}

	@Override
	public CompanyRegistrationBO adminDeleteCompany(CompanyRegistrationBO companyBO) throws Exception {

		int updateCount=0;
		try {
			Connection con = DatabaseConnectionFactory.createConnection();
			if(null!=companyBO && companyBO.getCompanyRegId()>0){
				PreparedStatement updatePs=con.prepareStatement("update company set isDelete=? where user_id=? ");
				updatePs.setBoolean(1, companyBO.getIsDelete());
				updatePs.setInt(2, companyBO.getCompanyRegId());
				updateCount=updatePs.executeUpdate();
				if(updateCount>0) {
					PreparedStatement ps=con.prepareStatement("update user set isDelete=? where companyEmail=? ");
					ps.setBoolean(1, companyBO.getIsDelete());
					ps.setString(2, companyBO.getCompanyEmail());
					updateCount=updatePs.executeUpdate();
				}
			}		
			if(updateCount>0) {
				companyBO.setErrorMessage("Company Deleted Successfully!!");
			} else {
				companyBO.setErrorMessage("Company Deleted Failed!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return companyBO;
	}

}
