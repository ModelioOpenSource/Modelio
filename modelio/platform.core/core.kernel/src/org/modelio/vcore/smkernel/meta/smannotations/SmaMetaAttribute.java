/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.vcore.smkernel.meta.SmAttribute;

/**
 * Annotations for a meta attribute.<br>
 * <p>The supported fields must match the tags supported by the SemGen module for the <<Semantic>> stereotype on attributes.</p>
 * 
 * <table border='1' cellpadding="4" cellspacing="0" >
 * <tr><td>Semantic tag</td> <td>Annotation field</td> <td>SmDirective literal</td><td>Description</td><tr>
 * <tr><td>EInoExternalize</td> <td>einoexternalize</td> <td>EINOEXTERNALIZE</td><td>indicates that this attribute must not be CMS-externalized</td><tr>
 * <tr><td>fpIndexed</td> <td>fpindexed</td> <td>FPINDEXED</td><td>? to be provided ?</td><tr>
 * </table>
 * 
 * 
 * @author phv
 */
@objid ("000923a6-f99c-1f1f-85a5-001ec947cd2a")
@Deprecated
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface SmaMetaAttribute  {
    /**
     * 
     * @return the attribute name
     */
    @objid ("004beace-3f7b-1f74-8a7a-001ec947cd2a")
    String metaName();

    /**
     * 
     * @return the SmAttribute java class.
     */
    @objid ("000933a0-f99c-1f1f-85a5-001ec947cd2a")
    Class<? extends SmAttribute> smAttributeClass();

    /**
     * 
     * @return the attribute type.
     */
    @objid ("00501bb2-eb1c-1f22-8c06-001ec947cd2a")
    Class<?> type();

    /**
     * 
     * @return the attribute type.
     */
    @objid ("001d7446-7c3b-1f32-acd1-001ec947cd2a")
    boolean einoexternalize() default false;

    /**
     * 
     * @return the attribute type.
     */
    @objid ("0085a9f8-7c51-1f32-acd1-001ec947cd2a")
    boolean fpindexed() default false;

}
