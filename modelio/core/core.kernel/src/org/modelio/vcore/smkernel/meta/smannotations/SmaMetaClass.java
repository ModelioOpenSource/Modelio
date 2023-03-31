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
package org.modelio.vcore.smkernel.meta.smannotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Annotations for a metaclass.<br>
 * <p>The supported fields must match the tags supported by the SemGen module for the &lt;&lt;Semantic>> stereotype on classes.
 * 
 * <table border='1' cellpadding="4" cellspacing="0" >
 * <tr><td>Semantic tag</td> <td>Annotation field</td> <td>SmDirective literal</td><td>Description</td><tr>
 * <tr><td>cmsnode</td> <td>cmsnode</td> <td>CMSNODE</td><td>indicates that the metaclass is a CMS node</td><tr>
 * </table>
 * 
 * 
 * @author phv
 */
@objid ("000922c0-f99c-1f1f-85a5-001ec947cd2a")
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
public @interface SmaMetaClass {
    /**
     * Indicates that the meta class represents a CMS node.
     */
    @objid ("0009259a-f99c-1f1f-85a5-001ec947cd2a")
    boolean cmsnode() default false;

    /**
     * Special behavior on import/export.
     */
    @objid ("00092770-f99c-1f1f-85a5-001ec947cd2a")
    boolean eispecial() default false;

    /**
     * All metaclass instances should be loaded.
     */
    @objid ("000928f6-f99c-1f1f-85a5-001ec947cd2a")
    boolean loadallinstance() default false;

    /**
     * No read only check.
     */
    @objid ("00093094-f99c-1f1f-85a5-001ec947cd2a")
    boolean noreadonly() default false;

    /**
     * The interface implemented by all elements of this metaclass.
     * @return the java interface
     */
    @objid ("00514064-eb1c-1f22-8c06-001ec947cd2a")
    Class<? extends MObject> mmClass();

    /**
     * The instance factory.
     */
    @objid ("001c12cc-fd1b-1f27-a7da-001ec947cd2a")
    Class<? extends ISmObjectFactory> factory();

}
