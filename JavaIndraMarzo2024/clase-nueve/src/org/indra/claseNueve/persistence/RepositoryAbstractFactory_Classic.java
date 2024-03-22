package org.indra.claseNueve.persistence;

import org.indra.claseNueve.models.*;

//Patron Abstract Factory
//Crea familia de objetos relacionados delegando a las subclases que objectos crear
public interface RepositoryAbstractFactory_Classic {
      Repository<Car> createCarRepository();
      Repository<Movil> createMovilRepository();
      //...
}
