package Informes.InformesPacientes.dtoP;

import Informes.InformesPacientes.daoPa.DaoP;

import java.io.InputStream;

public class Facade {
    DaoP daoP = new DaoP();
    public InputStream getReport( DaoP daoP ){ return daoP.getReport();}
}
