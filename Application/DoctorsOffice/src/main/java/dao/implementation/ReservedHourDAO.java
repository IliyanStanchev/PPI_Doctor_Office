package dao.implementation;

import dao.BaseDAO;
import entities.ReservedHour;

public class ReservedHourDAO extends BaseDAO< ReservedHour > {

    public ReservedHourDAO(){

        super.setClass( ReservedHour.class );
    }
}
