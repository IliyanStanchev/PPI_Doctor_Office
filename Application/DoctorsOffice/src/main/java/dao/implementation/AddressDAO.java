package dao.implementation;

import dao.BaseDAO;
import entities.Address;

public class AddressDAO extends BaseDAO< Address > {

    public AddressDAO(){

        super.setClass( Address.class );
    }
}
