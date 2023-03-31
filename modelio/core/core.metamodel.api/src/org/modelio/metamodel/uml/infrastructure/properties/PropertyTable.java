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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
 * 
 * 
 * 
 */
@objid ("00688c1a-ec87-1098-b22e-001ec947cd2a")
public interface PropertyTable extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("a791122f-ea89-43e8-b461-b191f35f9a70")
    public static final String MNAME = "PropertyTable";

    /**
     * The metaclass qualified name.
     */
    @objid ("db3bbc31-bbfa-48db-a312-56e2238b0609")
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
     * @throws IllegalStateException if the 'Content' attribute has an illegal format.
     * This can only happen if {@link #setContent(String)} is called directly.
     */
    @objid ("5652fdb3-2826-11e2-bf07-001ec947ccaf")
    Properties toProperties() throws IllegalStateException;

    /**
     * Getter for attribute 'PropertyTable.name'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("42a371d5-c71a-4952-9e8c-62fc8d697923")
    String getName();

    /**
     * Setter for attribute 'PropertyTable.name'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("64cfa3b6-69e8-4f3b-95c7-7e5f8ed1a30b")
    void setName(String value);

    /**
     * Getter for attribute 'PropertyTable.content'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("bb942164-5769-4e91-8d66-6b171599b8ba")
    String getContent();

    /**
     * Setter for attribute 'PropertyTable.content'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("0a291134-2ff3-4ea3-bdb7-a17c4522d52f")
    void setContent(String value);

    /**
     * Getter for relation 'PropertyTable->OwnerValDef'
     * 
     * Metamodel description:
     * <i>Matrix value definition owning the property table.</i>
     * 
     */
    @objid ("1cdffada-8db9-420e-b02f-b9e32e0c3d00")
    MatrixValueDefinition getOwnerValDef();

    /**
     * Setter for relation 'PropertyTable->OwnerValDef'
     * 
     * Metamodel description:
     * <i>Matrix value definition owning the property table.</i>
     * 
     */
    @objid ("5a725eb2-929b-4040-b570-20f3e3f7e827")
    void setOwnerValDef(MatrixValueDefinition value);

    /**
     * Getter for relation 'PropertyTable->OwnerQuery'
     * 
     * Metamodel description:
     * <i>Query owner</i>
     * 
     */
    @objid ("548bfd84-7aa4-4489-8474-f532b3facdc2")
    QueryDefinition getOwnerQuery();

    /**
     * Setter for relation 'PropertyTable->OwnerQuery'
     * 
     * Metamodel description:
     * <i>Query owner</i>
     * 
     */
    @objid ("f3700b9c-0494-4c0b-97e6-7cc42dd7865e")
    void setOwnerQuery(QueryDefinition value);

    /**
     * Getter for relation 'PropertyTable->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("f79d0ea5-91f5-401b-b81f-e505b62b916a")
    ModelElement getOwner();

    /**
     * Setter for relation 'PropertyTable->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("0194ecb3-0722-4753-a3b5-ee1354c0344f")
    void setOwner(ModelElement value);
}

