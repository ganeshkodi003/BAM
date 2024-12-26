package com.bornfire.services;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bornfire.entities.Audit_Mas_Repo;
import com.bornfire.entities.Audit_Master;

@Service
public class AuditService {
	
	@Autowired
	Audit_Mas_Repo auditRepository;
	
	@Autowired
	SessionFactory sessionFactory;

    public void logAudit(String action,String remarks, String userId, String username) throws HibernateException {
    	Audit_Master audit = new Audit_Master();
    	Session hs = sessionFactory.getCurrentSession();
        BigDecimal auditSrlNo = (BigDecimal) hs.createNativeQuery("SELECT AUD_SRL_NO.NEXTVAL FROM DUAL").getSingleResult();
        audit.setAudit_srl_num(auditSrlNo); // Set the srl_no manually for ApiResponse_Entity
        audit.setAudit_date(new Date());
        audit.setAudit_user_id(userId);
        audit.setAudit_user_name("Audit User");
        audit.setAction(action);
        audit.setEntry_user(userId);
        audit.setEntry_time(new Date());
        audit.setAction(action);
        audit.setRemarks(remarks);
        // Save the audit log entry
        auditRepository.save(audit);

        System.out.println("Audit log saved for action: " + action );
    }
}