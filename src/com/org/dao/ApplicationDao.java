package com.org.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import com.org.beans.ApplicationBean;
import com.org.beans.JobReqBean;

/**
 * @author I332022
 *
 */
public interface ApplicationDao {
    
    /**
     * Inserts the candidate details into the database table
     * @param applicationBean
     * @return
     * @throws SQLException
     * returns true or false depending on whether insert is successful or not
     */
    public boolean addCandidateApplication(ApplicationBean applicationBean) throws SQLException; //throws SQLException, NullPointerException;
    
    /**
     * Checks if the user has applied to the same job requisition  
     * @param job_req_id
     * @param candidate_id
     * @return true if application with the given job_req exists
     */
    public boolean isApplicationExists(Integer job_req_id, Integer candidate_id) throws SQLException;
    
    /**Retrieves the resume from the candidate's application 
     * @param application_id
     * @return inputstream
     * @throws SQLException 
     * @throws IOException 
     */
    public InputStream getResume(Integer application_id) throws SQLException, IOException;    
    /**
     * Retrieves the job applications which the candidate has applied to
     * @param candidate_id
     * @return
     */
    public List<JobReqBean> getAppliedJobReq(Integer candidate_id) throws SQLException;
}
