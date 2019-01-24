package com.example.demo.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.FcsAcctMemo;

@Mapper
public interface FcsAcctMemoDao {

	 @Select("SELECT * FROM WISE.FCS_ACCT_MEMO WHERE MEMBER_CODE = #{memberCode}")
	 @ResultMap("memoResMap")
	 FcsAcctMemo findByMemberCode(@Param("memberCode") String memberCode);

	 @Insert("INSERT INTO WISE.FCS_ACCT_MEMO(ID,MEMBER_CODE,MEMO,TYPE,OP_USER_CODE,OP_USER_NAME,CRT_TIME,UPD_TIME) VALUES(#{id}, #{memberCode},#{memo},#{type},#{opUserCOde},#{opUserName},#{crtTime},#{updTime})")
	 int insert(@Param("id") String name, @Param("memberCode") String memberCode,@Param("memo") String memo,@Param("type") String type,@Param("opUserCOde") String opUserCOde,@Param("opUserName") String opUserName,
			 @Param("crtTime") Date crtTime,@Param("updTime") Date updTime);
}
