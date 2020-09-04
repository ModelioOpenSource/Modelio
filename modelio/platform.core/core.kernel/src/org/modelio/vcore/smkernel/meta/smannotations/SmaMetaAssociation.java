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

package org.modelio.vcore.smkernel.meta.smannotations;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Annotations for a meta association (aka Dependency).<br>
 * <p>The supported fields must match the tags supported by the SemGen module for the <<Semantic>> stereotype on association ends.</p>
 * 
 * <table border='1' cellpadding="4" cellspacing="0" >
 * <tr><td>Semantic tag</td> <td>Annotation field</td> <td>SmDirective literal</td><td>Description</td><tr>
 * 
 * <tr><td>nopartOf</td> <td>nopartof</td> <td> ? </td><td>? to be provided ?</td><tr>
 * <tr><td>partOf</td> <td>partof</td> <td> ? </td><td>? to be provided ?</td><tr>
 * <tr><td>component</td> <td>component</td> <td> ? </td><td>? to be provided ?</td><tr>
 * <tr><td>isToDelete</td> <td>istodelete</td> <td> ? </td><td>? to be provided ?</td><tr>
 * <tr><td>dynamic</td> <td>dynamic</td> <td> ? </td><td>? to be provided ?</td><tr>
 * <tr><td>symetric</td> <td>symetric</td> <td> ? </td><td>? to be provided ?</td><tr>
 * <tr><td>nosymetric</td> <td>nosymetric</td> <td> ? </td><td>? to be provided ?</td><tr>
 * <tr><td>depToUnload</td> <td>deptounload</td> <td> ? </td><td>? to be provided ?</td><tr>
 * <tr><td>modifyWithDep</td> <td>modifywithdep</td> <td> ? </td><td>? to be provided ?</td><tr>
 * <tr><td>transient</td> <td>istransient</td> <td> ? </td><td>? to be provided ?</td><tr>
 * <tr><td>EIaskForExternalize</td> <td>eiaskforexternalize</td> <td> ? </td><td>? to be provided ?</td><tr>
 * <tr><td>EIcomponentLike</td> <td>eicomponentlike</td> <td> ? </td><td>? to be provided ?</td><tr>
 * <tr><td>EInoExternalize</td> <td>einoexternalize</td> <td> ? </td><td>? to be provided ?</td><tr>
 * <tr><td>EIpartOfLike</td> <td>eipartoflike</td> <td> ? </td><td>? to be provided ?</td><tr>
 * 
 * </table>
 * 
 * @author phv
 */
@objid ("00092428-f99c-1f1f-85a5-001ec947cd2a")
@Deprecated
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface SmaMetaAssociation  {
    /**
     * 
     * @return the role name
     */
    @objid ("001f90be-3f68-1f74-8a7a-001ec947cd2a")
    String metaName();

    /**
     * 
     * @return the minimum cardinality
     */
    @objid ("00093512-f99c-1f1f-85a5-001ec947cd2a")
    int min();

    /**
     * 
     * @return the maximum cardinality, -1 for no limit.
     */
    @objid ("000935d0-f99c-1f1f-85a5-001ec947cd2a")
    int max();

    /**
     * 
     * @return the SmDependency java class.
     */
    @objid ("00093698-f99c-1f1f-85a5-001ec947cd2a")
    Class<? extends SmDependency> smAssociationClass();

    /**
     * Metamodel type of the dependency.
     * <p>
     * Specify the metamodel interface class.
     * @return Metamodel type of the dependency.
     */
    @objid ("00509862-eb1c-1f22-8c06-001ec947cd2a")
    Class<? extends SmObjectData> typeDataClass();

    /**
     * 
     * @return true for a composition dependency
     */
    @objid ("002a6eb2-7f00-1f32-acd1-001ec947cd2a")
    boolean component() default false;

    /**
     * 
     * @return true for a shared composition dependency
     */
    @objid ("002ac8ee-7f00-1f32-acd1-001ec947cd2a")
    boolean sharedComponent() default false;

    /**
     * 
     * @return true for a "partof" dependency
     */
    @objid ("002a65e8-7f00-1f32-acd1-001ec947cd2a")
    boolean partof() default false;

    /**
     * 
     * @return true to cascade deletion to the target
     */
    @objid ("002a779a-7f00-1f32-acd1-001ec947cd2a")
    boolean istodelete() default false;

    /**
     * Tells whether it is advisable to avoid storing this dependency on saving.
     * <p>
     * Used for some opposite dependencies that can old huge number of elements.
     * @return true to tell storage to not save the dependency.
     */
    @objid ("002a80c8-7f00-1f32-acd1-001ec947cd2a")
    boolean performanceRisk() default false;

}
