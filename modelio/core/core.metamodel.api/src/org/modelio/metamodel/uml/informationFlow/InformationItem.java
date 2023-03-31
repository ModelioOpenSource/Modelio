/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/

package org.modelio.metamodel.uml.informationFlow;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.statik.Classifier;

/**
 * InformationItem v0.0.9054
 * 
 * 
 * An information item is an abstraction of all kinds of information that can be exchanged between objects. It is a kind of classifier intended for representing information in a very abstract way, one which cannot be instantiated.
 * 
 * One purpose of information items is to be able to define preliminary models, before having made detailed modeling decisions on types or structures. Another purpose of information items and information flows is to abstract complex models by a less precise but more general representation of the information exchanged between entities of a system.
 * 
 * The Classifiers that can realize an information item can only be of the following kind: Class, Interface, InformationItem, Signal, Component.
 * 
 * 
 */
@objid ("0063e890-c4bf-1fd8-97fe-001ec947cd2a")
public interface InformationItem extends Classifier {
    /**
     * The metaclass simple name.
     */
    @objid ("06e846d1-64d2-4425-8aa8-61ec33b7a13b")
    public static final String MNAME = "InformationItem";

    /**
     * The metaclass qualified name.
     */
    @objid ("654f3992-d0b7-4c03-acfc-cf370869e04b")
    public static final String MQNAME = "Standard.InformationItem";

    /**
     * Getter for relation 'InformationItem->Represented'
     * 
     * Metamodel description:
     * <i>Determines the classifiers that will specify the structure and nature of the information. 
     * An information item represents all its represented classifiers.</i>
     * 
     */
    @objid ("e8f924ab-25de-4f4e-9645-1df60bea749e")
    EList<Classifier> getRepresented();

    /**
     * Filtered Getter for relation 'InformationItem->Represented'
     * 
     * Metamodel description:
     * <i>Determines the classifiers that will specify the structure and nature of the information. 
     * An information item represents all its represented classifiers.</i>
     * 
     */
    @objid ("e4075e97-0dee-4be3-94e7-03248cf68b9f")
    <T extends Classifier> List<T> getRepresented(java.lang.Class<T> filterClass);
}

