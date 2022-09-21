package com.spring_boot_final.project.service.admin;

import com.spring_boot_final.project.dao.admin.IAdminExhbnDAO;
import com.spring_boot_final.project.dao.admin.IAdminNoteDAO;
import com.spring_boot_final.project.model.ExhbnVO;
import com.spring_boot_final.project.model.NoteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminNoteService {

    @Autowired
    @Qualifier("IAdminNoteDAO")
    IAdminNoteDAO dao;

    public ArrayList<NoteVO> NoteSelectAll(){
        return dao.NoteSelectAll();
    }

    public boolean InsertNotice(NoteVO vo){

        try {
            dao.InsertNotice(vo);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;

    }

    public boolean UpdateNote(NoteVO vo){

        try {
            dao.UpdateNote(vo);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;

    }


}
