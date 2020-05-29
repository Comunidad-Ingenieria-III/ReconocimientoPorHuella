package Informes.dtoP;

import Informes.daoPa.DaoP;

import java.io.InputStream;

public class Facade {
    DaoP daoP = new DaoP();
    public InputStream getReport( DaoP daoP ){ return daoP.getReport();}
}
