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

package org.modelio.vcore.smkernel.meta.descriptor;

import java.io.Serializable;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Metaclass reference.
 * <p>
 * Contains a metaclass name and its metamodel fragment name.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("a662396e-03f3-44a4-8fb3-57f91ae6acc1")
public class MClassRef implements Serializable {
    @mdl.prop
    @objid ("bbe7b040-741a-4041-a537-674317e59fbc")
    private String className;

    @mdl.propgetter
    public String getClassName() {
        // Automatically generated method. Please do not modify this code.
        return this.className;
    }

    @mdl.propsetter
    public void setClassName(String value) {
        // Automatically generated method. Please do not modify this code.
        this.className = value;
    }

    @mdl.prop
    @objid ("9bf77ac5-3de5-4762-81b4-b56065a60216")
    private String fragmentName;

    @mdl.propgetter
    public String getFragmentName() {
        // Automatically generated method. Please do not modify this code.
        return this.fragmentName;
    }

    @mdl.propsetter
    public void setFragmentName(String value) {
        // Automatically generated method. Please do not modify this code.
        this.fragmentName = value;
    }

    @objid ("fe15df26-e123-4d44-9e37-7d603bcb3c51")
    private static final long serialVersionUID = 1L;

    /**
     * @param mmFragName the metamodel fragment name
     * @param mClassName the metaclass name
     */
    @objid ("1d39f7d4-fad0-4bb1-82af-5bd5c34e587b")
    public MClassRef(String mmFragName, String mClassName) {
        this.fragmentName = mmFragName;
        this.className = mClassName;
    }

    @objid ("5257286f-db27-4268-b055-e530fcba3362")
    public String getQualifiedName() {
        if (this.fragmentName == null || "Fake".equals(this.fragmentName)) {
            // Return only the short name
            // Note: "Fake" was until 3.7 the only fake metamodel fragment, which gives unusable information.
            return getClassName();
        }
        return getFragmentName() + "." + getClassName();
    }

    @objid ("1481776f-e8d8-4031-8416-389d8ca3ae6e")
    public MClassRef() {
        // noop
    }

    @objid ("30b60562-c3a6-4b3d-8194-862a89b48cc9")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.className == null) ? 0 : this.className.hashCode());
        result = prime * result + ((this.fragmentName == null) ? 0 : this.fragmentName.hashCode());
        return result;
    }

    @objid ("439bdf57-32c5-41c7-8601-ad336fb194af")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MClassRef other = (MClassRef) obj;
        if (this.className == null) {
            if (other.className != null) {
                return false;
            }
        } else if (!this.className.equals(other.className)) {
            return false;
        }
        if (this.fragmentName == null) {
            if (other.fragmentName != null) {
                return false;
            }
        } else if (!this.fragmentName.equals(other.fragmentName)) {
            return false;
        }
        return true;
    }

    @objid ("6ad1bc4f-742b-4592-b012-1c8a01b86876")
    @Override
    public String toString() {
        return String.format("%s['%s'.'%s']", getClass().getSimpleName(), this.fragmentName, this.className);
    }

    /**
     * Create a MClassRef from a qualified metaclass name.
     * @param qualifiedName a qualified metaclass name.
     * @return a metaclass reference.
     */
    @objid ("219887e4-9021-43c7-88b7-f8f3427dfe0e")
    public static MClassRef fromQualifiedName(String qualifiedName) {
        int idx = qualifiedName.lastIndexOf(MClass.QUALIFIER_SEP);
        if (idx == -1) {
            // name not qualified
            Log.trace(new IllegalArgumentException(qualifiedName));
            
            return new MClassRef(
                    null, 
                    qualifiedName);
        } else {
            return new MClassRef(
                    qualifiedName.substring(0, idx), 
                    qualifiedName.substring(idx+1));
        }
    }

}
