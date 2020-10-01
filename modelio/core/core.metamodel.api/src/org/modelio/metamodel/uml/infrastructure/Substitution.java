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
package org.modelio.metamodel.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.Classifier;

/**
 * Substitution v0.0.9054
 * 
 * 
 * <p>A <em>Substitution</em> is a relationship between two <em>Classifiers</em>, which signifies that the substituting <em>Classifier</em> complies with the contract specified by the contract <em>Classifier</em>. This implies that <em>Instances</em> of the substituting <em>Classifier</em> are runtime substitutable where instances of the contract <em>Classifier</em> are expected.</p><p>The <em>Substitution</em> relationship denotes runtime substitutability that is not based on specialization. <em>Substitution</em>, unlike <em>specialization</em>, does not imply inheritance of structure, but only compliance of publicly available contracts. A <em>Substitution</em> like relationship is instrumental to specify runtime substitutability for domains that do not support specialization such as certain component technologies. It requires that</p>
 * 
 * <ol>
 * 	<li><em>Interfaces</em> implemented by the contract <em>Classifier</em> are also implemented by the substituting Classifier, or else the substituting Classifier implements a more specialized <em>Interface</em> type.</li>
 * 	<li>that&nbsp;any <em>Port</em> owned by the contract <em>Classifier</em> has a matching <em>Port</em> (see <em>Ports</em>) owned by the substituting <em>Classifier</em>.</li>
 * </ol>
 * 
 * <p>In Modelio, contrary to UML 2.0, <em>Substitution</em> derives from <em>ModelElement</em>. A <em>Substitution</em> is owned by its substituting <em>Classifier</em>.</p>
 */
@objid ("008dd736-c4be-1fd8-97fe-001ec947cd2a")
public interface Substitution extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("46176ea2-f865-4bb3-8858-600f9303cd0e")
    public static final String MNAME = "Substitution";

    /**
     * The metaclass qualified name.
     */
    @objid ("44666946-9aea-4ee0-a9b3-e72e3ad0e9e4")
    public static final String MQNAME = "Standard.Substitution";

    /**
     * Getter for relation 'Substitution->Contract'
     * 
     * Metamodel description:
     * <i>Designates the Classifier that is substituted.</i>
     */
    @objid ("e2ef3880-f80f-4b92-a4b8-7bd1274eabf0")
    Classifier getContract();

    /**
     * Setter for relation 'Substitution->Contract'
     * 
     * Metamodel description:
     * <i>Designates the Classifier that is substituted.</i>
     */
    @objid ("e1fa8f87-5981-471a-ab44-53b657282b8b")
    void setContract(Classifier value);

    /**
     * Getter for relation 'Substitution->SubstitutingClassifier'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("96283b7b-3de0-4d89-bcf3-de145be4b2e3")
    Classifier getSubstitutingClassifier();

    /**
     * Setter for relation 'Substitution->SubstitutingClassifier'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("854e30f4-c51e-46ac-ae34-685ba8e40a75")
    void setSubstitutingClassifier(Classifier value);

}
