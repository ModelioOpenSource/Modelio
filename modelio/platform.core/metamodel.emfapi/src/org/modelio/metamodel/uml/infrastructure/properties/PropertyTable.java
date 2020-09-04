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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.uml.infrastructure.properties;

import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;

/**
 * PropertyTable v0.0.9054
 * 
 * 
 * <p>A property table has a name and a stores a key/value map.</p>
 */
@objid ("00688c1a-ec87-1098-b22e-001ec947cd2a")
public interface PropertyTable extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("2782fbfb-6d04-47f2-bebd-bad4be22a641")
    public static final String MNAME = "PropertyTable";

    /**
     * The metaclass qualified name.
     */
    @objid ("76b48b41-e534-49b1-b9d3-8dc6053faf85")
    public static final String MQNAME = "Infrastructure.PropertyTable";

    /**
     * Get a property value.
     * @param key a property
     * @return its value or <code>null</code>.
     */
    @objid ("5652fdad-2826-11e2-bf07-001ec947ccaf")
    String getProperty(String key);

    /**
     * Set the property table content.
     * @param newContent the new table content.
     */
    @objid ("12071b01-282d-11e2-bf07-001ec947ccaf")
    void setContent(Properties newContent);

    /**
     * Set a property value.
     * @param key a property.
     * @param value its value.
     */
    @objid ("5652fdb0-2826-11e2-bf07-001ec947ccaf")
    void setProperty(String key, String value);

    /**
     * Get a copy of the property table content.
     * <p>
     * Modifying the returned Properties will not have any effect on
     * the PropertyTable.
     * @return a copy of the properties.
     * @throws java.lang.IllegalStateException if the 'Content' attribute has an illegal format.
     * This can only happen if {@link #setContent(String)} is called directly.
     */
    @objid ("5652fdb3-2826-11e2-bf07-001ec947ccaf")
    Properties toProperties() throws IllegalStateException;

    /**
     * Getter for attribute 'PropertyTable.name'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c30e4126-74ec-4b98-b317-473b1885f085")
    String getName();

    /**
     * Setter for attribute 'PropertyTable.name'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("707bf860-8bd6-4cbd-8f27-9e2c23d4f4ca")
    void setName(String value);

    /**
     * Getter for attribute 'PropertyTable.content'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("acdcbf92-8723-4d3b-9a89-06283ae2b1a6")
    String getContent();

    /**
     * Setter for attribute 'PropertyTable.content'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b54f246b-51ca-4fa2-b1c1-04f41567367c")
    void setContent(String value);

    /**
     * Getter for relation 'PropertyTable->OwnerValDef'
     * 
     * Metamodel description:
     * <i>Matrix value definition owning the property table.</i>
     */
    @objid ("87af1ec7-5be7-45f8-bb82-e0c6e2567366")
    MatrixValueDefinition getOwnerValDef();

    /**
     * Setter for relation 'PropertyTable->OwnerValDef'
     * 
     * Metamodel description:
     * <i>Matrix value definition owning the property table.</i>
     */
    @objid ("40809e8f-0535-4f04-9c35-9671773efe47")
    void setOwnerValDef(MatrixValueDefinition value);

    /**
     * Getter for relation 'PropertyTable->OwnerQuery'
     * 
     * Metamodel description:
     * <i>Query owner</i>
     */
    @objid ("16586938-8228-40d6-a8c5-232680d21b11")
    QueryDefinition getOwnerQuery();

    /**
     * Setter for relation 'PropertyTable->OwnerQuery'
     * 
     * Metamodel description:
     * <i>Query owner</i>
     */
    @objid ("6eaf0af1-eb24-4cec-832e-099672a9e894")
    void setOwnerQuery(QueryDefinition value);

    /**
     * Getter for relation 'PropertyTable->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("67a74692-df4b-481d-b198-ace502a02364")
    ModelElement getOwner();

    /**
     * Setter for relation 'PropertyTable->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e62d6bae-3b85-4f6c-9bd1-aeb4236ad1d5")
    void setOwner(ModelElement value);

}
