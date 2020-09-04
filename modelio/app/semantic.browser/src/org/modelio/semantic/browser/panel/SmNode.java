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

package org.modelio.semantic.browser.panel;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmFeature;

/**
 * Utility class for the Semantic Model Browser.</br>
 * A SmNode holds a pair composed of<ul>
 * <li>a model object of type MObject</li>
 * <li>a semantic feature of type SmFeature</li>
 * </ul>
 * 
 * 
 * @author phv
 */
@objid ("67025aae-5345-4814-93f7-6f250c3f0ef6")
public class SmNode {
    @mdl.prop
    @objid ("eceed66b-9a89-48d0-bf0b-9ebcfb997dc2")
    private final MObject obj;

    @mdl.propgetter
    public MObject getObj() {
        // Automatically generated method. Please do not modify this code.
        return this.obj;
    }

    @mdl.prop
    @objid ("7288d630-5664-4935-b15e-afb7733e1cc6")
    private final SmFeature feature;

    @mdl.propgetter
    public SmFeature getFeature() {
        // Automatically generated method. Please do not modify this code.
        return this.feature;
    }

    @objid ("3a97d441-b17d-44bd-968c-00081249f4e6")
    public SmNode(MObject mObj, MDependency mDep) {
        this.obj = mObj;
        this.feature = (SmFeature) mDep;
    }

    @objid ("221b0728-6c6d-4d5d-ac91-1717dfc4b843")
    public SmNode(MObject mObj, MAttribute mAtt) {
        this.obj = mObj;
        this.feature = (SmFeature)mAtt;
    }

    @objid ("8f3644f9-7f00-4140-b819-746a38c28735")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.feature == null ? 0 : this.feature.hashCode());
        result = prime * result + (this.obj == null ? 0 : this.obj.hashCode());
        return result;
    }

    @objid ("55fe2046-0c4b-48f6-943a-011cc1cba0ad")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final SmNode other = (SmNode) o;
        if (this.feature == null) {
            if (other.feature != null) {
                return false;
            }
        } else if (!this.feature.equals(other.feature)) {
            return false;
        }
        if (this.obj == null) {
            if (other.obj != null) {
                return false;
            }
        } else if (!this.obj.equals(other.obj)) {
            return false;
        }
        return true;
    }

    /**
     * For a SmDependency, return its contents.
     * For a SmAttribute return nothing.
     * 
     * @return the tree node children
     */
    @objid ("2f8fc558-4e88-47b2-afb7-605daf6eda20")
    public Collection<?> getContent() {
        if (getFeature() instanceof SmDependency) {
            return ((SmObjectImpl) getObj()).getDepValList((SmDependency)getFeature());
        }
        
        // For a SmAttribute return nothing
        return Collections.emptyList();
    }

    @objid ("81c0b8b9-5e42-4912-8822-f2463905944d")
    public String toString() {
        return "SmNode {" + this.feature.getName() + ":" + this.obj.getName() + "}";
    }

}
