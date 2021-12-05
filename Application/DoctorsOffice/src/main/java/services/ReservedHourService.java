package services;

import dao.implementation.ReservedHourDAO;
import entities.ReservedHour;

public class ReservedHourService {

    private final ReservedHourDAO reservedHourDAO = new ReservedHourDAO();

    public boolean saveReservedHour( ReservedHour reservedHour ){

        if( reservedHourDAO.saveOrUpdate( reservedHour ) == null)
            return false;

        return true;
    }
}
