package dao.implementation;

import dao.BaseDAO;
import entities.Examination;

public class ExaminationDAO extends BaseDAO< Examination > {


    public ExaminationDAO(){

        super.setClass( Examination.class );
    }
}
