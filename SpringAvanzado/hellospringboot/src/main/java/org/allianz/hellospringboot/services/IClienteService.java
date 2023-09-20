package org.allianz.hellospringboot.services;

import java.util.*;
import org.allianz.hellospringboot.models.*;

public interface IClienteService {
    public List<Cliente> getAll();
    public void add(Cliente cliente); 
    public Cliente getById(int id);
    public void delete(int id);
}
