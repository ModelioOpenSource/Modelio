/* 
 * Copyright 2013-2018 Modeliosoft
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
package org.modelio.metamodel.uml.statik;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;

/**
 * NaryLinkEnd v0.0.9054
 * 
 * 
 * Nouvelle Class :
 * Extrémité de lien en contact avec une instance. Participe à la définition d'une occurence d'association.
 * Appartient à une Instance, est associé à un Link et représente une AssociationEnd.
 * 
 * Une NaryLinkEnd est reliée à une Instance uniquement.
 */
@objid ("00749ece-17e8-10a1-88a0-001ec947cd2a")
public interface NaryLinkEnd extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("9da08015-3c56-4e3c-8b50-803da2f6c8a2")
    public static final String MNAME = "NaryLinkEnd";

    /**
     * The metaclass qualified name.
     */
    @objid ("1cb77133-5fc0-49e0-937f-cf96e15a4ef8")
    public static final String MQNAME = "Standard.NaryLinkEnd";

    /**
     * Getter for attribute 'NaryLinkEnd.IsOrdered'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("dd7d909d-4160-412f-8538-caf775660782")
    boolean isIsOrdered();

    /**
     * Setter for attribute 'NaryLinkEnd.IsOrdered'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1cbde774-9c6b-4d6f-81d4-39f217f3e154")
    void setIsOrdered(boolean value);

    /**
     * Getter for attribute 'NaryLinkEnd.IsUnique'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("da6b1fad-604c-4030-876a-cd8eab233edf")
    boolean isIsUnique();

    /**
     * Setter for attribute 'NaryLinkEnd.IsUnique'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c63c9441-07d0-48d2-85b2-5bef3fe48de0")
    void setIsUnique(boolean value);

    /**
     * Getter for attribute 'NaryLinkEnd.MultiplicityMax'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("480ea666-3d24-4ccd-bf67-31b0b27320a8")
    String getMultiplicityMax();

    /**
     * Setter for attribute 'NaryLinkEnd.MultiplicityMax'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("90d3be70-dd94-441a-b859-4ed6bdd366cc")
    void setMultiplicityMax(String value);

    /**
     * Getter for attribute 'NaryLinkEnd.MultiplicityMin'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("02194651-90c5-4b48-9b4e-e57f0ae8e21d")
    String getMultiplicityMin();

    /**
     * Setter for attribute 'NaryLinkEnd.MultiplicityMin'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c411cc96-af03-463f-85dc-7ec59cedd4bc")
    void setMultiplicityMin(String value);

    /**
     * Getter for relation 'NaryLinkEnd->Source'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("89337cd5-17ba-4493-bbe4-eee4e4c23591")
    Instance getSource();

    /**
     * Setter for relation 'NaryLinkEnd->Source'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e9f4b093-d757-4875-b4cf-22ddd5c14f4b")
    void setSource(Instance value);

    /**
     * Getter for relation 'NaryLinkEnd->NaryLink'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f0fa8be4-9ef2-49cb-b18b-776c7302de36")
    NaryLink getNaryLink();

    /**
     * Setter for relation 'NaryLinkEnd->NaryLink'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d3bca77c-158c-4a40-8684-abdc4a729427")
    void setNaryLink(NaryLink value);

    /**
     * Getter for relation 'NaryLinkEnd->Consumer'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a27c0259-cd31-40ed-9f22-4db165e1c096")
    RequiredInterface getConsumer();

    /**
     * Setter for relation 'NaryLinkEnd->Consumer'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("90c3acbc-9f3a-4832-8e01-9f0ab78471c9")
    void setConsumer(RequiredInterface value);

    /**
     * Getter for relation 'NaryLinkEnd->Provider'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("289cd56d-bb06-41ba-ab26-11d16916593d")
    ProvidedInterface getProvider();

    /**
     * Setter for relation 'NaryLinkEnd->Provider'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8f8f24ed-0fa5-45e3-89cc-21738321380c")
    void setProvider(ProvidedInterface value);

}
