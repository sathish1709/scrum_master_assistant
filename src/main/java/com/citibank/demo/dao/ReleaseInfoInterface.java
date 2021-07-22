package com.citibank.demo.dao;

import java.util.List;

import com.citibank.demo.business.ProjectInfo;
import com.citibank.demo.business.ReleaseInfo;
import com.citibank.demo.business.UserAuth;

public interface ReleaseInfoInterface {
	
	public ReleaseInfo addRelease(ReleaseInfo r);
	public String deleteRelease(String release_no);
	public ReleaseInfo getRelease(String release_no);
	public List<ReleaseInfo> allRelease(ReleaseInfo r);

}
