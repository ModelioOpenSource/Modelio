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

package org.modelio.vcore.smkernel.mapi;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.DeadObjectException;

/**
 * Model object reference.
 * <p>
 * Allows to reference a model object that may or not be known. The reference is made of the object metaclass name, the object
 * identifier and optionally the object name.
 */
@objid ("7852f43e-13ae-11e2-8f8e-001ec947ccaf")
public class MRef implements Serializable {
    /**
     * Metaclass name.
     */
    @objid ("dcadb035-13af-11e2-8f8e-001ec947ccaf")
    public String mc;

    /**
     * Optional element name.
     */
    @objid ("dcb01263-13af-11e2-8f8e-001ec947ccaf")
    public String name;

    @objid ("710f777c-298c-11e2-8adc-001ec947ccaf")
    private static final long serialVersionUID = 1L;

    /**
     * Element UUID.
     */
    @objid ("d33f10cc-03a2-4ea9-90a0-00e27b67766f")
    public String uuid;

    /**
     * MRef string format:
     * 
     * 'nnnn' {xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx} mmmmm
     * 
     * 
     * 'nnnn' = name,  {xxx...xx} = uuid,  mmmm = metaclass
     */
    @objid ("79a0f2d3-200a-4bfb-8095-16620edd6336")
    private static final Pattern pattern = Pattern.compile("(?:'(.*)')?\\s?\\{(.*)\\} (.*)");

    /**
     * Creates a reference for the given element.
     * 
     * @param element The element to reference.
     */
    @objid ("dcadb00c-13af-11e2-8f8e-001ec947ccaf")
    public MRef(MObject element) {
        this.mc = element.getMClass().getQualifiedName();
        this.uuid = element.getUuid();
        this.name = loadName(element);
    }

    /**
     * Creates a reference.
     * 
     * @param mc The metaclass name.
     * @param tuuid The UUID
     */
    @objid ("dcadb010-13af-11e2-8f8e-001ec947ccaf")
    public MRef(String mc, String tuuid) {
        this.mc = mc;
        this.uuid = tuuid;
        this.name = "";
    }

    /**
     * Creates a reference.
     * 
     * @param mc The metaclass name.
     * @param tuuid The UUID
     * @param name an object name, may be <code>null</code>.
     */
    @objid ("dcadb015-13af-11e2-8f8e-001ec947ccaf")
    public MRef(String mc, String tuuid, String name) {
        this.mc = mc;
        this.uuid = tuuid;
        this.name = name;
    }

    /**
     * Construct a MRef instance from a String whose format is the MRef.toString() format so that:<br>
     * given <i>mref</i> a MRef, <code>new MRef(mref.toString()).equals(mref)</code> is guaranteed to be <code>true</code>.
     * 
     * @param s a string
     */
    @objid ("21ba15fc-c3cd-4f9f-a436-e5ba1e769c3f")
    public MRef(String s) {
        final Matcher m = pattern.matcher(s);
        
        if (m.matches()) {
            final MatchResult r = m.toMatchResult();
            if (r.groupCount() == 3) {
        
                final String nameGroup = r.group(1);
                this.name = (nameGroup == null || nameGroup.isEmpty() ? null : nameGroup);
                this.uuid = (r.group(2));
                this.mc = r.group(3);
            } else {
                throw new IllegalArgumentException("Invalid MRef string: " + s);
            }
        
        } else {
            throw new IllegalArgumentException("Invalid MRef string: " + s);
        }
    }

    @objid ("dcadb021-13af-11e2-8f8e-001ec947ccaf")
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (obj.getClass() != getClass()) {
            return false;
        }
        
        final MRef other = (MRef) obj;
        
        final boolean mcEquals;
        int thisIndex = this.mc.indexOf(".");
        int otherIndex = other.mc.indexOf(".");
        if ((thisIndex == -1 && otherIndex == -1) || (thisIndex != -1 && otherIndex != -1)) {
            // Boths metaclasses are 'simple' or 'qualified'
            mcEquals = this.mc.equals(other.mc);
        } else {
            // Check 'simple' metaclasses for both objects
            String thisSimpleMc = thisIndex > 0 ? this.mc.substring(thisIndex + 1) : this.mc;
            String otherSimpleMc = otherIndex > 0 ? other.mc.substring(otherIndex + 1) : other.mc;
            mcEquals = thisSimpleMc.equals(otherSimpleMc);
        }
        return mcEquals && this.uuid.equals(other.uuid);
    }

    @objid ("dcadb027-13af-11e2-8f8e-001ec947ccaf")
    @Override
    public int hashCode() {
        int index = this.mc.indexOf(".");
        // FIXME always use a metaclass hashCode computed from a 'qualified' name, but it requires a lot of changes.
        int mcHashCode = index > 0 ? this.mc.substring(index + 1).hashCode() : this.mc.hashCode();
        return mcHashCode ^ this.uuid.hashCode();
    }

    /**
     * Test method
     * 
     * @param args command line arguments
     */
    @objid ("21533303-0310-4e06-a1fb-2155b29b8684")
    public static void main(String[] args) {
        MRef ref = new MRef("'Validation de l'EG' {c14066e5-015a-45af-8b4e-1aa27aadbac8} Standard.BpmnBehavior");
        assert(Objects.equals(ref.mc,"Standard.BpmnBehavior"));
        assert(Objects.equals(ref.name,"Validation de l'EG"));
        assert(Objects.equals(ref.uuid,"c14066e5-015a-45af-8b4e-1aa27aadbac8"));
        
        ref = new MRef("'' {c14066e5-015a-45af-8b4e-1aa27aadbac8} Standard.BpmnBehavior");
        assert(Objects.equals(ref.mc,"Standard.BpmnBehavior"));
        assert(Objects.equals(ref.name, null));
        assert(Objects.equals(ref.uuid,"c14066e5-015a-45af-8b4e-1aa27aadbac8"));
        
        ref = new MRef(" {c14066e5-015a-45af-8b4e-1aa27aadbac8} Standard.BpmnBehavior");
        assert(Objects.equals(ref.mc,"Standard.BpmnBehavior"));
        assert(Objects.equals(ref.name, null));
        assert(Objects.equals(ref.uuid,"c14066e5-015a-45af-8b4e-1aa27aadbac8"));
        
        ref = new MRef("{c14066e5-015a-45af-8b4e-1aa27aadbac8} Standard.BpmnBehavior");
        assert(Objects.equals(ref.mc,"Standard.BpmnBehavior"));
        assert(Objects.equals(ref.name, null));
        assert(Objects.equals(ref.uuid,"c14066e5-015a-45af-8b4e-1aa27aadbac8"));
        
        ref = new MRef("'aaa aa' {c14066e5-015a-45af-8b4e-1aa27aadbac8} Standard.BpmnBehavior");
        assert(Objects.equals(ref.mc,"Standard.BpmnBehavior"));
        assert(Objects.equals(ref.name, "aaa aa"));
        assert(Objects.equals(ref.uuid,"c14066e5-015a-45af-8b4e-1aa27aadbac8"));
        
        ref = new MRef("'Validation de l'EG d'abord' {c14066e5-015a-45af-8b4e-1aa27aadbac8} Standard.BpmnBehavior");
        assert(Objects.equals(ref.mc,"Standard.BpmnBehavior"));
        assert(Objects.equals(ref.name,"Validation de l'EG d'abord"));
        assert(Objects.equals(ref.uuid,"c14066e5-015a-45af-8b4e-1aa27aadbac8"));
    }

    /**
     * Produces a string representing this MRef object.
     * 
     * 'nnnn' {xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx} mmmmm
     * 
     * 
     * 'nnnn' = name {xxx...xx} = uuid mmmm = metaclass
     */
    @objid ("dcadb02c-13af-11e2-8f8e-001ec947ccaf")
    @Override
    public String toString() {
        final StringBuilder s = new StringBuilder(80);
        if (this.name != null) {
            s.append("'");
            s.append(this.name);
            s.append("' ");
        }
        s.append("{");
        s.append(this.uuid);
        s.append("} ");
        s.append(this.mc);
        return s.toString();
    }

    /**
     * Creates a reference for the given element without loading the element name.
     * <p>
     * Can be used to avoid loading a not yet loaded {@link MObject} or an object whose loading may fail with exception.
     * 
     * @param element The element to reference.
     */
    @objid ("3f9ae11d-7337-41cd-ba3d-548ca64b1063")
    public static final MRef withoutName(MObject element) {
        return new MRef(element.getMClass().getQualifiedName(), element.getUuid());
    }

    @objid ("6b81ba12-931f-4cb8-8737-3d53e9b9bc42")
    private static final String loadName(MObject o) {
        try {
            return o.getName();
        } catch (DeadObjectException e) {
            return e.toString();
        } catch (RuntimeException e) {
            return e.toString();
        }
    }

}
