/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.adonin.mEEssenger.entitiesbeans.relation;

import java.util.List;
import javax.ejb.Local;
import ua.bionic.adonin.mEEssenger.entities.Relation;

/**
 *
 * @author dimon
 */
@Local
public interface RelationFacadeLocal {

    void create(Relation relation);

    void edit(Relation relation);

    void remove(Relation relation);

    Relation find(Object id);
    
    Relation findRelation(int fromId, int toId);

    List<Relation> findAll();

    List<Relation> findRange(int[] range);

    int count();
    
}
